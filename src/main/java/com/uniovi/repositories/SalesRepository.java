package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;

public interface SalesRepository extends JpaRepository<Sale, Long> {
	
	 List<Sale> findByBuyerAndValid(User buyer,boolean valid);
	 List<Sale> findByOwnerAndValid(User owner,boolean valid);

}
