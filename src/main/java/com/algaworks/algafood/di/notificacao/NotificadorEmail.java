package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;
	
	@Override
	public void notificar(Cliente cliente, String messagem) {
		System.out.println("Host: " + properties.getHostServidor());
		System.out.println("Port: " + properties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s - %s",
				cliente.getNome(), cliente.getEmail(), messagem);
	}
}
