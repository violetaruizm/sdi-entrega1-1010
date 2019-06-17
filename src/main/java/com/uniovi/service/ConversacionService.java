package com.uniovi.service;

import java.util.List;
import java.util.Optional;

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
		conversacion.setValid(true);
		return conversacionRepository.save(conversacion);
	}
	
	public List<Conversacion> encontrarConversacionesUsuario(User user){
		
		return conversacionRepository.getConversaciones(user.getEmail());
	}
	
	public Conversacion getConversacionId(Long id) {
		Optional<Conversacion> optional = conversacionRepository.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}
    	
    	return null;
	}
	
    public boolean deleteConversacion(Long id) {
	Conversacion conversacion = conversacionRepository.getOne(id);
	conversacion.setValid(false);
	
	conversacionRepository.save(conversacion);
	return true;
    }

}
