package com.uniovi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private SalesRepository salesRepository;

	public List<Sale> getSalesByOwner(User owner) {
		List<Sale> sales = salesRepository.findByOwner(owner);

		return sales;
	}

	public List<Sale> getSalesByBuyer(User buyer) {
		List<Sale> sales = salesRepository.findByOwner(buyer);

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

	public void deleteMark(Long id) {
		salesRepository.getOne(id).setValid(false);
	}

}
