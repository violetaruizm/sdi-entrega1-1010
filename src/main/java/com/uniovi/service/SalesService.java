package com.uniovi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

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
	private HttpSession httpSession;

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

	public Sale getSale(Long id) {
		Set<Sale> consultedList = (Set<Sale>) httpSession
				.getAttribute("consultedList");

		if (consultedList == null) {
			consultedList = new HashSet<Sale>();
		}

		Sale markObtained = salesRepository.findById(id).get();
		consultedList.add(markObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return markObtained;

	}

	public void addSale(Sale sale) {
// Si en Id es null le asignamos el ultimo + 1 de la lista
		salesRepository.save(sale);
	}

	public void deleteSale(Long id) {
		Sale sale = salesRepository.getOne(id);
		sale.setValid(false);
		salesRepository.save(sale);
	}

	public Page<Sale> searchSalesByTitle(Pageable pageable,String searchText, User user) {
		

		if (searchText != null && !searchText.isEmpty()) {
			searchText= "%"+searchText+"%";
			return salesRepository.searchByTitle(searchText, user.getId(),pageable);
		} else {
			return salesRepository.findByDifferentOwner(user.getId(),pageable);

		}

		
	}

	public boolean buySale(Long id, User user) {
		Sale sale = salesRepository.getOne(id);
		if(user.getMoney()>=sale.getPrice()) {
			user.setMoney(user.getMoney()-sale.getPrice());
			sale.setBuyer(user);
			sale.setStatus(Status.SOLD);
			salesRepository.save(sale);
			return true;
		} return false;
		
		
	}

}
