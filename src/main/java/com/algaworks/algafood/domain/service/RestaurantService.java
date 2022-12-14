package com.algaworks.algafood.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Gastronomy;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.GastronomyRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository repositoryRestaurant;
	
	@Autowired
	private GastronomyRepository repositoryGastronomy;
	
	public Restaurant save (Restaurant restaurant) {
		Long gastronomyId = restaurant.getGastronomy().getId();
		Optional<Gastronomy> gastronomy = repositoryGastronomy.findById(gastronomyId);
		
		if(gastronomy.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", gastronomyId));
		}
		
		restaurant.setGastronomy(gastronomy.get());
		
		return repositoryRestaurant.save(restaurant);
	}
	
	public List<Restaurant> list () {
		return repositoryRestaurant.findAll();
	}
	
	public Restaurant find (Long id) {
		Optional<Restaurant> restaurant = repositoryRestaurant.findById(id);
		
		if(restaurant.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", restaurant));
		}
		
		return restaurant.get();
	}
	
	public Restaurant update (Long id, Restaurant restaurant) {
		Optional<Restaurant> restaurantObj = repositoryRestaurant.findById(id);
		
		if(restaurantObj.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", restaurantObj));
		}
		
		BeanUtils.copyProperties(restaurant, restaurantObj.get(), "id");	
		
		return repositoryRestaurant.save(restaurantObj.get());
	}
	
	public void remove (Long id) {
		try {
			
			repositoryRestaurant.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"Não existe um cadastro de cozinha em código %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"Cozinha de código %d não pode ser removida, pois está em uso.", id));
		}
	}
	
	public Restaurant updatePartial(Long id, Map<String, Object> field) {
		Optional<Restaurant> restaurantCurrent = repositoryRestaurant.findById(id);
		
		if(restaurantCurrent == null) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", restaurantCurrent));
		}
		
		merge(field, restaurantCurrent.get());
		
		return update(id, restaurantCurrent.get());
		
	}

	private void merge(Map<String, Object> dateOrigin, Restaurant restaurantDestiny) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant restaurantOrigin = objectMapper.convertValue(dateOrigin, Restaurant.class);
		
		System.out.println(restaurantOrigin); 
		
		dateOrigin.forEach((nameProperty, valueProperty) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
					
			System.out.println(nameProperty + " " + valueProperty + " " + newValue);
			
			ReflectionUtils.setField(field, restaurantDestiny, valueProperty);
		});
	}
	
}
