package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.service.UsersService;

@Controller
public class HomeController {
	@Autowired
	private UsersService userService;
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUser(email);
		
		model.addAttribute("email", activeUser.getEmail());
		model.addAttribute("money",String.valueOf(activeUser.getMoney()));
		return "home";
	}

}
