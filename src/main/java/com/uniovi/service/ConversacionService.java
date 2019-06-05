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
		 return conversacionRepository.findBySaleAndOwnerAndBuyer(buyer, sale.getOwner(), sale);
		 
		 
	 }

}
