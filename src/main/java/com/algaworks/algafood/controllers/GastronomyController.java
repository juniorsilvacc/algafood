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
import com.algaworks.algafood.domain.model.Gastronomy;
import com.algaworks.algafood.domain.service.GastronomyService;

@RestController
@RequestMapping(value = "/cozinhas")
public class GastronomyController {
		
	@Autowired
	private GastronomyService gastronomyService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<?> create(@RequestBody Gastronomy gastronomy) {
		try {
			
			Gastronomy newGastronomy = gastronomyService.save(gastronomy);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newGastronomy);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "/")
	public List<Gastronomy> list(){
		return gastronomyService.list();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {		
		try {
			
			Gastronomy gastronomy = gastronomyService.find(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(gastronomy);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Gastronomy gastronomy) {
		try {
			
			Gastronomy gastronomyCurrent = gastronomyService.update(id, gastronomy);
			
			return ResponseEntity.status(HttpStatus.OK).body(gastronomyCurrent);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Gastronomy> remove(@PathVariable Long id) {
		try {
			
			gastronomyService.remove(id);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
		}
		
	}
	
}
