package com.algaworks.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String messagem) {
		System.out.printf("Notificando %s por SMS através do telefone %s - %s",
				cliente.getNome(), cliente.getTelefone(), messagem);
	}
}
