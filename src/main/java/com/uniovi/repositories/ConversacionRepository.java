package com.uniovi.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Conversacion;


public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {
	
	 @Query("SELECT r FROM Conversacion r " + "WHERE r.owner.id=?1 " + "AND r.buyer.id=?2 AND r.sale.id=?3")
	 Conversacion findBySaleAndOwnerAndBuyer(Long ownerId,Long buyerId, Long saleId);

}
