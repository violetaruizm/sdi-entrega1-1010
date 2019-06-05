package com.uniovi.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			Conversacion nueva = null;
			nueva = conversacionService.crearConversacion(sale, sale.getOwner(), user);
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
		model.addAttribute("mensajeNuevo", new Mensaje());
		return "conversacion/conversacion";
	}

}
