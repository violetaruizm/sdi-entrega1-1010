package com.uniovi.service;

import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Status;
import com.uniovi.repositories.SalesRepository;

@Service
public class SalesService {



    @Autowired
    private SalesRepository salesRepository;

    public List<Sale> getSalesByOwner(User owner) {
	List<Sale> sales = salesRepository.findByOwnerAndValid(owner, true);

	return sales;
    }

    public List<Sale> getSalesByBuyer(User buyer) {
	List<Sale> sales = salesRepository.findByBuyerAndValid(buyer, true);

	return sales;
    }

    public List<Sale> getSales() {
	return salesRepository.findAll();

    }
    
    public Sale getSaleById(Long id) {
    	Optional<Sale> optional = salesRepository.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}
    	
    	return null;
    }

   
    

    public void addSale(Sale sale,User activeUser) {
// Si en Id es null le asignamos el ultimo + 1 de la lista
    	sale.setDate(new LocalDateTime());

    	if(sale.isDestacada()) {
    		activeUser.setMoney(activeUser.getMoney()-20);}
    	
    	sale.setValid(true);

    	
    	sale.setStatus(Status.ONSALE);

	salesRepository.save(sale);
    }

    public boolean deleteSale(Long id) {
	Sale sale = salesRepository.getOne(id);
	sale.setValid(false);
	sale.setStatus(Status.OUT);
	salesRepository.save(sale);
	return true;
    }

    public Page<Sale> searchSalesByTitle(Pageable pageable, String searchText, User user) {

	if (searchText != null && !searchText.isEmpty()) {
	    searchText = "%" + searchText + "%";
	    return salesRepository.searchByTitle(searchText, user.getId(), pageable);
	} else {
	    return salesRepository.findByDifferentOwner(user.getId(), pageable);

	}

    }

    public boolean buySale(Long id, User user) {
	Sale sale = salesRepository.getOne(id);
	if (user.getMoney() >= sale.getPrice()) {
	    user.setMoney(user.getMoney() - sale.getPrice());
	    sale.setBuyer(user);
	    sale.setStatus(Status.SOLD);
	    salesRepository.save(sale);
	    return true;
	}
	return false;

    }
    
    public void deleteAll() {
	salesRepository.deleteAll();
    }

	public boolean destacarOferta(Long id,User user) {
		Sale sale = salesRepository.getOne(id);
		
		if(user.getMoney()-20>=0) {
			sale.setDestacada(true);
			user.setMoney(user.getMoney()-20);
			sale.setBuyer(user);
			salesRepository.save(sale);
			return true;
		}
		return false;
	}



}
