package com.algaworks.algafood.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.service.CityService;

@RestController
@RequestMapping(value = "/cidades")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<?> create(@RequestBody City city) {
		try {
			
			City newCity = cityService.save(city);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newCity);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "/")
	public List<City> list(){
		return cityService.list();
	}
		
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
		try {
			
			City cityCurrent = cityService.update(id, city);
			
			return ResponseEntity.status(HttpStatus.OK).body(cityCurrent);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<City> remove(@PathVariable Long id) {
		try {
			
			cityService.remove(id);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
		}
		
	}

}
