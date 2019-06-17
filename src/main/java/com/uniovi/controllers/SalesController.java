package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		model.addAttribute("money", activeUser.getMoney());
		model.addAttribute("sale", new Sale());
		return "sale/add";
	}

	@PostMapping("/sale/add")
	public String add(@Validated Sale sale, BindingResult result, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		sale.setOwner(activeUser);
		addSaleFormValidator.validate(sale, result);
		if (result.hasErrors()) {
			return "sale/add";
		}

		salesService.addSale(sale, activeUser);

		return "redirect:/sale/list";
	}

	@RequestMapping(value = "/sale/list", method = RequestMethod.GET)
	public String getListado(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		model.addAttribute("money", activeUser.getMoney());
		model.addAttribute("salesList", salesService.getSalesByOwner(activeUser));
		return "sale/list";
	}

	@PostMapping("/sale/delete")
	public String deleteSales(@RequestParam List<Long> idsSale) {
		idsSale.forEach(id -> salesService.deleteSale(id));
		return "redirect:/sale/list?succesful";
	}

	@RequestMapping(value = "/sale/bought", method = RequestMethod.GET)
	public String getListadoCompradas(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		model.addAttribute("money", activeUser.getMoney());
		model.addAttribute("salesList", salesService.getSalesByBuyer(activeUser));

		return "sale/boughtList";
	}

	@RequestMapping("/sale/all")
	public String getList(Pageable pageable, Model model, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User user = userService.getUser(email);
		if (user != null) {
			if (searchText == null || searchText.equals("null")) {
				searchText = "";
			}
			Page<Sale> sales = salesService.searchSalesByTitle(pageable, searchText, user);
			model.addAttribute("money", user.getMoney());
			model.addAttribute("salesList", sales.getContent());
			model.addAttribute("page", sales);

		}

		return "sale/allSalesList";
	}

	@RequestMapping("/sale/buy/{id}")
	public String buyOffer(@PathVariable Long id, Principal principal) {
		String email = principal.getName();
		User user = userService.getUser(email);
		if (user != null) {
			boolean sold = salesService.buySale(id, user);
			if (sold) {
				return "redirect:/sale/bought?success";

			}

		}
		return "redirect:/sale/bought?error";

	}

	@RequestMapping("/sale/delete/{id}")
	public String deleteOffer(@PathVariable Long id, Principal principal) {
		String email = principal.getName();
		User user = userService.getUser(email);
		if (user != null) {
			boolean sold = salesService.deleteSale(id);
			if (sold) {
				return "redirect:/sale/list?success";

			}

		}
		return "redirect:/sale/list?error";

	}

	@GetMapping("/sale/destacar/{id}")
	public String destacarOferta(@PathVariable Long id, Principal principal) {
		String email = principal.getName();
		User user = userService.getUser(email);
		if (user != null) {
			boolean sold = salesService.destacarOferta(id, user);
			if (sold) {
				return "redirect:/sale/list?success";

			}
		}
		return "redirect:/sale/list?error";

	}
}
