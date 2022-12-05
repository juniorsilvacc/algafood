package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurant> find(String name, BigDecimal initialRate, BigDecimal finalRate) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(name)) {
			predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		}
		
		if(initialRate != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("rate"), initialRate));
		}
		
		if(finalRate != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("rate"), finalRate));
		}
		
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurant> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
}
