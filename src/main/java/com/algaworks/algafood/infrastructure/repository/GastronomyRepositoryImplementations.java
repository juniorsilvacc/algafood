package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Gastronomy;
import com.algaworks.algafood.domain.repository.GastronomyRepository;

@Component
public class GastronomyRepositoryImplementations implements GastronomyRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Gastronomy> findAll() {
		return manager.createQuery("from Gastronomy" , Gastronomy.class).getResultList();
	}
	
	@Override
	public Gastronomy findById(Long id) {
		return manager.find(Gastronomy.class, id);
	}
	
	@Transactional
	@Override
	public Gastronomy save(Gastronomy gastronomy) {
		return manager.merge(gastronomy);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Gastronomy cozinha = findById(id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinha);
	}

}
