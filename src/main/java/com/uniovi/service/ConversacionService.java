package com.uniovi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversacion;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversacionRepository;

@Service
public class ConversacionService {
	
	@Autowired
	private ConversacionRepository conversacionRepository;
	
	 public List<Conversacion> getConversaciones() {
			return conversacionRepository.findAll();}
	 
	 public Conversacion getConversacion(Sale sale,User buyer) {
		 return conversacionRepository.findBySaleAndOwnerAndBuyer(sale.getOwner().getId(),buyer.getId(), sale.getId());
		 
		 
	 }

	public Conversacion crearConversacion(Sale sale, User owner, User user) {
		Conversacion conversacion = new Conversacion();
		conversacion.setSale(sale);
		conversacion.setOwner(owner);
		conversacion.setBuyer(user);
		conversacionRepository.save(conversacion);
		return conversacion;
	}

}
