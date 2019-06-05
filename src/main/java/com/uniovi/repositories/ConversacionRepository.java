package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniovi.entities.Conversacion;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;


public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {
	
	 Conversacion findBySaleAndOwnerAndBuyer(User buyer,User owner, Sale sale);

}
