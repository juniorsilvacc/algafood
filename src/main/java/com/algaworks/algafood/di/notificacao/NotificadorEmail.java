package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Profile("prod")
@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
	
	public NotificadorEmail() {
		System.out.println("NotificadorEmail REAL");
	}
	
	@Override
	public void notificar(Cliente cliente, String messagem) {
		System.out.printf("Notificando %s através do e-mail %s - %s",
				cliente.getNome(), cliente.getEmail(), messagem);
	}
}
