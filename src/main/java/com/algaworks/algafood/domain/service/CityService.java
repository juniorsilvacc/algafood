package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public City save (City city) {
		Long cityId = city.getState().getId();
		Optional<State> state = stateRepository.findById(cityId);
		
		if(state.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de cidade com código %d", cityId));
		}
		
		city.setState(state.get());
		
		return cityRepository.save(city);
	}
	
	public List<City> list () {
		return cityRepository.findAll();
	}
	
	public City update (Long id, City city) {
		Optional<City> cityObj = cityRepository.findById(id);
		
		if(cityObj.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de cidade com código %d", cityObj));
		}
		
		BeanUtils.copyProperties(city, cityObj.get(), "id");	
		
		return cityRepository.save(cityObj.get());
	}
	
	public void remove (Long id) {
		try {
			
			cityRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Não existe um cadastro de cidade em código %d", id));	
		}
	}
	
	
}
