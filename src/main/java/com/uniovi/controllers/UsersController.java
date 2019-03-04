package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;
import com.uniovi.service.SecurityService;
import com.uniovi.service.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormvalidator;
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result, Model model) {

		System.out.println(user.toString());
		signUpFormvalidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		
        user.setRole(Role.STANDARD);
        user.setMoney(100);
		userService.addUser(user);
		//securityService.autoLogin(user.getEmail(), user.getRepassword());
		
		System.out.println("sign up succesful");
		return "signup";
	}

}
