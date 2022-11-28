package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Gastronomy;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.GastronomyRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository repositoryRestaurant;
	
	@Autowired
	private GastronomyRepository repositoryGastronomy;
	
	public Restaurant save(Restaurant restaurant) {
		Long gastronomyId = restaurant.getGastronomy().getId();
		Gastronomy gastronomy = repositoryGastronomy.findById(gastronomyId);
		
		if(gastronomy == null) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", gastronomyId));
		}
		
		restaurant.setGastronomy(gastronomy);
		
		return repositoryRestaurant.save(restaurant);
	}
	
	public List<Restaurant> list() {
		return repositoryRestaurant.findAll();
	}
	
	public Restaurant find(Long id) {
		Restaurant restaurant = repositoryRestaurant.findById(id);
		
		if(restaurant == null) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", restaurant));
		}
		
		return restaurant;
	}
	
	public Restaurant update(Long id, Restaurant restaurant) {
		Restaurant restaurantObj = repositoryRestaurant.findById(id);
		
		if(restaurantObj == null) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", restaurantObj));
		}
		
		BeanUtils.copyProperties(restaurant, restaurantObj, "id");	
		
		return repositoryRestaurant.save(restaurantObj);
	}
	
	public void remove(Long id) {
		try {
			
			repositoryRestaurant.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"Não existe um cadastro de cozinha em código %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"Cozinha de código %d não pode ser removida, pois está em uso.", id));
		}
	}
	
}