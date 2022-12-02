package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Gastronomy;
import com.algaworks.algafood.domain.repository.GastronomyRepository;

@Service
public class GastronomyService {
	
	@Autowired
	private GastronomyRepository gastronomyRepository;
	
	public Gastronomy save (Gastronomy gastronomy) {
		return gastronomyRepository.save(gastronomy);
	}
	
	public List<Gastronomy> list () {
		return gastronomyRepository.findAll();
	}
	
	public Gastronomy find (Long id) {
		Optional<Gastronomy> gastronomy = gastronomyRepository.findById(id);
		
		if(gastronomy.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de cozinha com código %d", gastronomy));
		}
		
		return gastronomy.get();
	}
	
	public Gastronomy update (Long id, Gastronomy gastronomy) {
		Optional<Gastronomy> gastronomyObj = gastronomyRepository.findById(id);
		
		if(gastronomyObj.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de restaurante com código %d", gastronomyObj));
		}
		
		BeanUtils.copyProperties(gastronomy, gastronomyObj.get(), "id");	
		
		return gastronomyRepository.save(gastronomyObj.get());
	}
	
	public void remove (Long id) {
		try {
			
			gastronomyRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"Não existe um cadastro de cozinha em código %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"Cozinha de código %d não pode ser removida, pois está em uso.", id));
		}
		
	}

}
