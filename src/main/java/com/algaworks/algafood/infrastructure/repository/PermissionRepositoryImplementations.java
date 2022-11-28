package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImplementations implements PermissionRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> findAll() {
		return manager.createQuery("from Permissao" , Permission.class).getResultList();
	}
	
	@Override
	public Permission findById(Long id) {
		return manager.find(Permission.class, id);
	}
	
	@Transactional
	@Override
	public Permission save(Permission permission) {
		return manager.merge(permission);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Permission permission = findById(id);
		
		if(permission == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(permission);
	}

}
