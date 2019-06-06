package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Mensaje;


@Component
public class MensajesValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Mensaje.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Mensaje mensaje = (Mensaje) target;
		if(mensaje.getContenido()==null || mensaje.getContenido()=="") {
			 errors.rejectValue("contenido", "Error.mensaje.vacio");
		}
		
	}

}
