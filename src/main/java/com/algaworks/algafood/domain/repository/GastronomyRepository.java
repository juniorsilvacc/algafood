package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Gastronomy;

public interface GastronomyRepository {
	
	List<Gastronomy> findAll();
	Gastronomy findById(Long id);
	Gastronomy save(Gastronomy gastronomy);
	void remove(Long id);

}
