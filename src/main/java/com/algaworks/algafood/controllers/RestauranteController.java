package com.algaworks.algafood.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repositoryRestaurante;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			
			Restaurante novoRestaurante = cadastroRestauranteService.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public List<Restaurante> listar() {
		return repositoryRestaurante.listar();
	}
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante resturante = repositoryRestaurante.buscar(id);
		
		if (resturante == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resturante);
	}
	
}
