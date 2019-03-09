package com.uniovi.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.uniovi.entities.User;
import com.uniovi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class LogInValidator implements Validator{
	
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		//el dni está vacio
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		
		//dni no tiene el numero correcto de caracteres
		if (usersService.getUser(user.getEmail())==null) {
			errors.rejectValue("email", "Error.login.email.no.exist");
		}
		User user1 = usersService.getUser(user.getEmail());
		
	}


}
