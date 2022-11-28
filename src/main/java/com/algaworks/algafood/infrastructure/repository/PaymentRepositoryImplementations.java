package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Payment;
import com.algaworks.algafood.domain.repository.PaymentRepository;

@Component
public class PaymentRepositoryImplementations implements PaymentRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Payment> findAll() {
		return manager.createQuery("from FormaPagamento" , Payment.class).getResultList();
	}
	
	@Override
	public Payment findById(Long id) {
		return manager.find(Payment.class, id);
	}
	
	@Transactional
	@Override
	public Payment save(Payment payment) {
		return manager.merge(payment);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Payment payment = findById(id);
		
		if(payment == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(payment);
	}

}
