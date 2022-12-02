package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	public State save (State state) {
		return stateRepository.save(state);
	}
	
	public List<State> list () {
		return stateRepository.findAll();
	}
	
	public State update (Long id, State state) {
		Optional<State> stateObj = stateRepository.findById(id);
		
		if(stateObj.isEmpty()) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de estado com código %d", stateObj));
		}
		
		BeanUtils.copyProperties(state, stateObj.get(), "id");	
		
		return stateRepository.save(stateObj.get());
	}
	
	public void remove (Long id) {
		try {
			
			stateRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Não existe um cadastro de estado em código %d", id));	
		}
	}
	
}
