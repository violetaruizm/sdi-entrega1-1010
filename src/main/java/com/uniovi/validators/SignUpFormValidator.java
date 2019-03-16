package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	User user = (User) target;

	// el email está vacio
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");

	// email no tiene el numero correcto de caracteres
	if (user.getEmail().length() < 5 || user.getEmail().length() > 24) {
	    errors.rejectValue("email", "Error.signup.email.length");
	}

	// el usuario ya está registrado
	if (usersService.getUser(user.getEmail()) != null) {
	    errors.rejectValue("email", "Error.signup.email.duplicate");
	}

	// el nombre no tiene el numero correcto de caracteres
	if (user.getName().length() < 5 || user.getName().length() > 24) {
	    errors.rejectValue("name", "Error.signup.name.length");
	}

	// El apellido no tiene el numero correcto de caracteres
	if (user.getSurname().length() < 5 || user.getSurname().length() > 24) {
	    errors.rejectValue("surname", "Error.signup.lastName.length");
	}

	// la contraseña no tiene el numero correcto de caracteres
	if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
	    errors.rejectValue("password", "Error.signup.password.length");
	}

	// la contraseña y la repeticion de la contraseña no coinciden
	if (!user.getPasswordConfirm().equals(user.getPassword())) {
	    errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
	}

    }

}
