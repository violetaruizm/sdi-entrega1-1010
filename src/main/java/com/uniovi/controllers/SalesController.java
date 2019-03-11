package com.uniovi.controllers;

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
		sale.setOwner(activeUser);
		
		return "perfecto";
	}


}
