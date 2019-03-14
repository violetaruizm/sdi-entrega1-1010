package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SalesRepository extends JpaRepository<Sale, Long> {

	List<Sale> findByBuyerAndValid(User buyer, boolean valid);

	List<Sale> findByOwnerAndValid(User owner, boolean valid);

	@Query("SELECT r FROM Sale r WHERE (LOWER(r.title) LIKE LOWER(?1)) AND "
			+ "r.owner.id <> (?2) AND r.status <> 'OUT'")
	Page<Sale> searchByTitle(String seachtext, Long email,Pageable pageable);

	@Query("SELECT r FROM Sale r " + "WHERE r.owner.id!=?1 "
			+ "AND r.status!='OUT'")
	Page<Sale> findByDifferentOwner(Long email,Pageable pageable);

}
