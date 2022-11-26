package com.algaworks.algafood.api.controllers;

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

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CadastroCozinhaService cadastroService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		Cozinha novaCozinha = cadastroService.salvar(cozinha);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novaCozinha);
	}
	
	@GetMapping
	public List<Cozinha> listar(){
		return repository.listar();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha = repository.buscar(id);
		
		if (cozinha == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = repository.buscar(id);
		
		if (cozinhaAtual == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		cozinhaAtual.setNome(cozinha.getNome());
		//Igual a cozinhaAtual.name = name;
		
		repository.salvar(cozinhaAtual);
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		try {
			
			cadastroService.excluir(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
		}
		
	}
	
}
