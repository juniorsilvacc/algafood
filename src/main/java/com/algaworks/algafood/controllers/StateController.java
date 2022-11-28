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
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.service.StateService;

@RestController
@RequestMapping(value = "/estados")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<?> create(@RequestBody State state) {
		try {
			
			State newState = stateService.save(state);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newState);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/")
	public List<State> list() {
		return stateService.list();
	}
	
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody State state) {
		try {
			
			State stateCurrent = stateService.update(id, state);
			
			return ResponseEntity.status(HttpStatus.OK).body(stateCurrent);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			
			stateService.remove(id);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
		}
	}
}
