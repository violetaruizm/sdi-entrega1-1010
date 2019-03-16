package com.uniovi.controllers;

import java.util.List;

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

    @PostMapping("/signup")
    public String signup(@Validated User user, BindingResult result, Model model) {
	signUpFormvalidator.validate(user, result);
	if (result.hasErrors()) {
	    return "signup";
	}
	user.setRole(Role.ROLE_STANDARD);
	user.setValid(true);
	user.setMoney(100);
	userService.addUser(user);
	securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
	return "redirect:home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

	return "login";
    }
    
    @RequestMapping(value = "/login?logout", method = RequestMethod.GET)
    public String logout(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = userService.getUser(email);
	if (activeUser!=null) {
	    SecurityContextHolder.clearContext();
	   
	}

	return "login";
    }

    @RequestMapping("/user/list")
    public String getListado(Model model) {
	model.addAttribute("usersList", userService.getStandardUsers());
	return "user/list";
    }

    @PostMapping("/user/delete")
    public String deleteUsers(@RequestParam List<Long> idsUser) {
	idsUser.forEach(id -> userService.deleteUser(id));
	return "redirect:/user/list?succesful";
    }

}
