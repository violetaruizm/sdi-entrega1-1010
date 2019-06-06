package com.uniovi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversacion;
import com.uniovi.entities.Mensaje;
import com.uniovi.entities.User;
import com.uniovi.repositories.MensajesRepository;

@Service
public class MensajesService  {
	
	@Autowired
	private MensajesRepository mensajesRepository;
	
	public List<Mensaje> getMensajesConversacion(Conversacion conversacion){
		List<Mensaje> mensajes = mensajesRepository.getMensajesConversacion(conversacion.getId());
		return mensajes;
	}

	public void nuevoMensaje(Mensaje mensaje,Conversacion conversacion,User emisor) {
		mensaje.setFecha(new Date());
		mensaje.setEmisor(emisor.getEmail());
		mensaje.setConversacion(conversacion);
		mensajesRepository.save(mensaje);
		
	}

}
