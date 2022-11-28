package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Payment;

public interface PaymentRepository {

	List<Payment> findAll();
	Payment findById(Long id);
	Payment save(Payment Payment);
	void remove(Long id);
	
}
