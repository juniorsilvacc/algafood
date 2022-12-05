package com.algaworks.algafood.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;

@RestController
@RequestMapping(value = "/teste")
public class TestController implements RestaurantRepositoryQueries{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@GetMapping(value = "/restaurantes/por-nome-e-frete")
	public List<Restaurant> find(String name, BigDecimal initialRate, BigDecimal finalRate) {
		return restaurantRepository.find(name, initialRate, finalRate);
	}

}
