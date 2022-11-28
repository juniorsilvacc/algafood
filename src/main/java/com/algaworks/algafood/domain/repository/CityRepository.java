package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.City;

public interface CityRepository {
	
	List<City> findAll();
	City findById(Long id);
	City save(City city);
	void remove(Long id);

}