package com.algaworks.algafood.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestaurantController {
	
	@Autowired
	private RestaurantService createRestaurantService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
		try {
			
			Restaurant newRestaurant = createRestaurantService.save(restaurant);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "/")
	public List<Restaurant> list() {
		return createRestaurantService.list();
	}
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<?> find(@PathVariable Long id) {		
		try {
			
			Restaurant restaurant = createRestaurantService.find(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(restaurant);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		try {
			
			Restaurant restaurantCurrent = createRestaurantService.update(id, restaurant);
			
			return ResponseEntity.status(HttpStatus.OK).body(restaurantCurrent);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			
			createRestaurantService.remove(id);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
		}
	}
	
	@PatchMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> field) {
		try {
			
			Restaurant restaurantUpdate = createRestaurantService.updatePartial(id, field);
			
			return ResponseEntity.status(HttpStatus.OK).body(restaurantUpdate);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
}
