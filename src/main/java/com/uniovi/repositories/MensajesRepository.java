package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniovi.entities.Conversacion;
import com.uniovi.entities.Mensaje;
import com.uniovi.entities.Sale;

public interface MensajesRepository extends JpaRepository<Mensaje, Long>{
	
	 List<Sale> findByConversacion(Conversacion conversacion);

}
