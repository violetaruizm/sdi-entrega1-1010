package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.repositories.SalesRepository;
import com.uniovi.service.UsersService;

@Controller
public class HomeController {
    @Autowired
    private UsersService userService;
    @Autowired
    private SalesRepository salesRepository;
	


    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }


    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model) {

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = userService.getUser(email);
	if (!activeUser.isValid()) {
	    SecurityContextHolder.clearContext();
	    return "redirect:/login?error";
	}

	model.addAttribute("email", activeUser.getEmail());
	model.addAttribute("money", String.valueOf(activeUser.getMoney()));
	model.addAttribute("money1", String.valueOf(activeUser.getMoney()));
	model.addAttribute("salesList",salesRepository.findByDifferentOwnerAndHighlighted(activeUser.getId()));
	return "home";
    }

}
