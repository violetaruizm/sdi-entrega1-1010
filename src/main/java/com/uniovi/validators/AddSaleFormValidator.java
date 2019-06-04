package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Sale;

@Component
public class AddSaleFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
	return Sale.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	Sale sale = (Sale) target;

	// campos vacios
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");

	// precio negativo
	if (sale.getPrice() <= 0) {
	    errors.rejectValue("price", "Error.price");
	}
	
	if(sale.isDestacada() && sale.getOwner().getMoney()-20<0) {
		 errors.rejectValue("destacada", "Error.destacada");
		
	}

    }

}
