package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Mensaje;

public interface MensajesRepository extends JpaRepository<Mensaje, Long> {

	@Query("SELECT r FROM Mensaje r " + "WHERE r.conversacion.id=?1 ")
	List<Mensaje> getMensajesConversacion(Long conversacionId);

}
