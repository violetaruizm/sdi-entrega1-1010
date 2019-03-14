package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;

public interface SalesRepository extends JpaRepository<Sale, Long> {
	
	 List<Sale> findByBuyerAndValid(User buyer,boolean valid);
	 List<Sale> findByOwnerAndValid(User owner,boolean valid);
	 

	 /*@Query("SELECT r FROM Sale r WHERE (LOWER(r.title) LIKE LOWER(?1) AND "
	 		+ "r.owner.email<>(?2)")
			 List<Sale> searchByTitle(String seachtext,String email);
	
	 @Query("SELECT r FROM Sale r WHERE r.owner.email<>(?1)")
				 List<Sale> allSalesDifferentOwner(String email);*/

			


}
