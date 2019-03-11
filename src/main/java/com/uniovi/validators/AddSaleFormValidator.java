package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.service.UsersService;

@Component
public class AddSaleFormValidator implements Validator{
	

	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Sale.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sale sale = (Sale) target;
		
		//el dni está vacio
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price",
				"Error.empty");
		
		//dni no tiene el numero correcto de caracteres
		if (sale.getPrice()<=0 ) {
			errors.rejectValue("price", "Error.price");
		}
		

	}

}
