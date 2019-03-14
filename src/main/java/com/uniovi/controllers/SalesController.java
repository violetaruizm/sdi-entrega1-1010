package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;
import com.uniovi.service.SalesService;
import com.uniovi.service.UsersService;
import com.uniovi.validators.AddSaleFormValidator;

@Controller
public class SalesController {
	
	@Autowired
	private SalesService salesService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private AddSaleFormValidator addSaleFormValidator;
	
	@RequestMapping(value = "/sale/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("sale",new Sale());
		return "sale/add";
	}

	@PostMapping("/sale/add")
	public String add(@Validated Sale sale, BindingResult result,
			Model model) {
		
		addSaleFormValidator.validate(sale, result);
		if (result.hasErrors()) {
			return "sale/add";
		}
		sale.setDate(new LocalDateTime());

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		sale.setValid(true);
		
		sale.setOwner(activeUser);
		salesService.addSale(sale);
		
		return "redirect:/sale/list";
	}
	
	@RequestMapping(value = "/sale/list", method= RequestMethod.GET)
	public String getListado(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		System.out.println(salesService.getSalesByOwner(activeUser).size());
		model.addAttribute("salesList", salesService.getSalesByOwner(activeUser));
		return "sale/list";
}
	@PostMapping("/sale/delete")
    public String deleteSales(@RequestParam List<Long> idsSale) {
		idsSale.forEach(id -> salesService.deleteSale(id));
        return "redirect:/sale/list?succesful";
}
	
	@RequestMapping(value = "/sale/bought", method= RequestMethod.GET)
	public String getListadoCompradas(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		System.out.println(salesService.getSalesByBuyer(activeUser).size());
		model.addAttribute("salesList", salesService.getSalesByBuyer(activeUser));
		return "sale/boughtList";
}
	
	
	@RequestMapping("/sale/all")
	public String getList(Model model, Principal principal,
	@RequestParam(value = "", required=false) String searchText){
	String email = principal.getName(); // DNI es el name de la autenticación
	User user = userService.getUser(email);
	if(user!=null) {
	model.addAttribute("salesList",
			salesService.searchSalesByTitle(searchText, email) );}
	
	return "sale/allSalesList";
	}


}
