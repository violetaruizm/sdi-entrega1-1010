package com.uniovi.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Conversacion;
import com.uniovi.entities.Mensaje;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.service.ConversacionService;
import com.uniovi.service.MensajesService;
import com.uniovi.service.SalesService;
import com.uniovi.service.UsersService;
import com.uniovi.validators.MensajesValidator;

@Controller
public class MensajesController {

	@Autowired
	private UsersService userService;

	@Autowired
	private SalesService saleService;

	@Autowired
	private MensajesService mensajeService;

	@Autowired
	private ConversacionService conversacionService;

	@Autowired
	private MensajesValidator mensajesValidator;

	@RequestMapping(value = "/mensaje/enviar/{id}", method = RequestMethod.GET)
	public String enviarMensaje(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("user", new User());
		List<Mensaje> mensajes;
		Sale sale = saleService.getSaleById(id);
		String email = principal.getName();
		User user = userService.getUser(email);

		Conversacion conversacion = conversacionService.getConversacion(sale, user);
		if (conversacion != null) {

			mensajes = mensajeService.getMensajesConversacion(conversacion);

		} else {
			Conversacion nueva = conversacionService.crearConversacion(sale, sale.getOwner(), user);
			if (nueva != null) {
				mensajes = new ArrayList<Mensaje>();

			} else {
				return "redirect:/sale/all?error";
			}
		}
		// buscar mensajes
		// mandar mensajes a la vista
		// mandar mensaje nuevo a la vista
		// crear vista
		model.addAttribute("mensajes", mensajes);
		model.addAttribute("saleId", id);
		model.addAttribute("mensaje", new Mensaje());
		return "conversacion/conversacion";
	}

	@RequestMapping(value = "/mensaje/enviar/{id}", method = RequestMethod.POST)
	public String enviarMensaje(@PathVariable Long id, @Validated Mensaje mensaje, BindingResult result, Model model,
			Principal principal) {
		Sale sale = saleService.getSaleById(id);
		String email = principal.getName();
		User user = userService.getUser(email);

		Conversacion conversacion = conversacionService.getConversacion(sale, user);

		mensajesValidator.validate(mensaje, result);
		if (!result.hasErrors()) {

			mensajeService.nuevoMensaje(mensaje, conversacion, user);
			return "redirect:/mensaje/enviar/" + id;
		}

		return "redirect:/mensaje/enviar/" + id;

	}

	@RequestMapping(value = "/conversaciones", method = RequestMethod.GET)
	public String verConversaciones(Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.getUser(email);
		if (user != null) {
			List<Conversacion> conversaciones = conversacionService.encontrarConversacionesUsuario(user);
			model.addAttribute("conversacionList", conversaciones);

		}
		return "conversacion/conversacionesList";
	}

	@RequestMapping(value = "/conversacion/abrir/{id}", method = RequestMethod.GET)
	public String enviarMensajeDesdeListado(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("user", new User());
		List<Mensaje> mensajes;
		Conversacion conversacion = conversacionService.getConversacionId(id);
		if (conversacion != null) {

			mensajes = mensajeService.getMensajesConversacion(conversacion);

		} else {

			return "redirect:/conversaciones?error";

		}
		// buscar mensajes
		// mandar mensajes a la vista
		// mandar mensaje nuevo a la vista
		// crear vista
		model.addAttribute("mensajes", mensajes);
		model.addAttribute("conversacionId", id);
		model.addAttribute("mensaje", new Mensaje());
		return "conversacion/conversacionDesdeListado";
	}

	@RequestMapping(value ="/conversacion/enviar/{id}",method=RequestMethod.POST)
	public String nuevoMensaje(@PathVariable Long id,@Validated Mensaje mensaje,BindingResult result,Model model,Principal principal) {
		Sale sale = saleService.getSaleById(id);
		String email = principal.getName();
		User user = userService.getUser(email);

		Conversacion conversacion = conversacionService.getConversacionId(id);

		mensajesValidator.validate(mensaje, result);
		if (!result.hasErrors()) {

			mensajeService.nuevoMensaje(mensaje, conversacion, user);
			return "redirect:/conversacion/abrir/" + id;
		}

		return "redirect:/conversacion/abrir/" + id;
		
	}

}
