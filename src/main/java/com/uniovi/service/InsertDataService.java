package com.uniovi.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversacion;
import com.uniovi.entities.Mensaje;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;
import com.uniovi.entities.types.Status;
import com.uniovi.repositories.ConversacionRepository;
import com.uniovi.repositories.MensajesRepository;
import com.uniovi.repositories.SalesRepository;
import com.uniovi.repositories.UserRepository;

@Service
public class InsertDataService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SalesRepository saleRepo;
    
    @Autowired
    private MensajesRepository mensajesRepo;
    
    @Autowired
    private ConversacionRepository conversacionRepo;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    

    @PostConstruct
    public void init() {
	
	
	 User admin = new User();
	    admin.setEmail("admin@email.com");
	    admin.setPassword(bCryptPasswordEncoder.encode("admin"));
	    admin.setRole(Role.ROLE_ADMIN);
	    admin.setName("Admin");
	    admin.setSurname("Admin");
	    admin.setValid(true);
	    userRepo.save(admin);
	    
	    User user1 = new User();
	    user1.setEmail("user1@email.com");
	    user1.setPassword(bCryptPasswordEncoder.encode("12345"));
	    user1.setRole(Role.ROLE_STANDARD);
	    user1.setName("User1");
	    user1.setSurname("user1");
	    user1.setValid(true);
	    user1.setMoney(100);
	   userRepo.save(user1);
	    
	    User user2 = new User();
	    user2.setEmail("user2@email.com");
	    user2.setPassword(bCryptPasswordEncoder.encode("12345"));
	    user2.setRole(Role.ROLE_STANDARD);
	    user2.setName("User2");
	    user2.setSurname("User2");
	    user2.setValid(true);
	    user2.setMoney(250);
	    userRepo.save(user2);
	    
	    User user3 = new User();
	    user3.setEmail("user3@email.com");
	    user3.setPassword(bCryptPasswordEncoder.encode("12345"));
	    user3.setRole(Role.ROLE_STANDARD);
	    user3.setName("User3");
	    user3.setSurname("User3");
	    user3.setValid(true);
	    user3.setMoney(80.95);
	    userRepo.save(user3);
	    
	    User user4 = new User();
	    user4.setEmail("user4@email.com");
	    user4.setPassword(bCryptPasswordEncoder.encode("12345"));
	    user4.setRole(Role.ROLE_STANDARD);
	    user4.setName("User4");
	    user4.setSurname("User4");
	    user4.setValid(true);
	    user4.setMoney(125.30);
	    userRepo.save(user4);
	    
	    User user5 = new User();
	    user5.setEmail("user5@email.com");
	    user5.setPassword(bCryptPasswordEncoder.encode("12345"));
	    user5.setRole(Role.ROLE_STANDARD);
	    user5.setName("User5");
	    user5.setValid(true);
	    user5.setSurname("User5");
	    user5.setMoney(50);
	    userRepo.save(user5);
	    
	    
	    //Ofertas de cada usuario
	    
	    Sale oferta1a = new Sale();
	    Sale oferta1b = new Sale();
	    Sale oferta1c = new Sale();
	    oferta1a.setTitle("Oferta 1a");
	    oferta1b.setTitle("Oferta 1b");
	    oferta1c.setTitle("Oferta 1c");
	    oferta1a.setValid(true);
	    oferta1b.setValid(true);
	    oferta1c.setValid(true);
	    oferta1a.setStatus(Status.SOLD);
	    oferta1b.setStatus(Status.SOLD);
	    oferta1c.setStatus(Status.ONSALE);
	    oferta1a.setDestacada(true);
	    oferta1b.setDestacada(false);
	    oferta1c.setDestacada(false);
	    oferta1a.setPrice(10);
	    oferta1b.setPrice(110);
	    oferta1c.setPrice(50);
	    
	    
	    oferta1a.setOwner(user1);
	    oferta1b.setOwner(user1);
	    oferta1c.setOwner(user1);
	    
	    Sale oferta2a = new Sale();
	    Sale oferta2b = new Sale();
	    Sale oferta2c = new Sale();
	    oferta2a.setTitle("Oferta 2a");
	    oferta2b.setTitle("Oferta 2b");
	    oferta2c.setTitle("Oferta 2c");
	    oferta2a.setOwner(user2);
	    oferta2b.setOwner(user2);
	    oferta2c.setOwner(user2);
	    oferta2a.setValid(true);
	    oferta2b.setValid(true);
	    oferta2c.setValid(true);
	    oferta2a.setStatus(Status.ONSALE);
	    oferta2b.setStatus(Status.SOLD);
	    oferta2c.setStatus(Status.SOLD);
	    oferta2a.setPrice(100);
	    oferta2b.setPrice(10.50);
	    oferta2c.setPrice(55.6);
	    
	    Sale oferta3a = new Sale();
	    Sale oferta3b = new Sale();
	    Sale oferta3c = new Sale();
	    oferta3a.setTitle("Oferta 3a");
	    oferta3b.setTitle("Oferta 3b");
	    oferta3c.setTitle("Oferta 3c");
	    oferta3a.setOwner(user3);
	    oferta3b.setOwner(user3);
	    oferta3c.setOwner(user3);
	    oferta3a.setValid(true);
	    oferta3b.setValid(true);
	    oferta3c.setValid(true);
	    oferta3a.setStatus(Status.SOLD);
	    oferta3b.setStatus(Status.ONSALE);
	    oferta3c.setStatus(Status.SOLD);
	    oferta3a.setPrice(1000);
	    oferta3b.setPrice(116);
	    oferta3c.setPrice(35.25);
	    
	    
	    Sale oferta4a = new Sale();
	    Sale oferta4b = new Sale();
	    Sale oferta4c = new Sale();
	    oferta4a.setTitle("Oferta 4a");
	    oferta4b.setTitle("Oferta 4b");
	    oferta4c.setTitle("Oferta 4c");
	    oferta4a.setOwner(user4);
	    oferta4b.setOwner(user4);
	    oferta4c.setOwner(user4);
	    
	    oferta4a.setValid(true);
	    oferta4b.setValid(true);
	    oferta4c.setValid(true);
	    oferta4a.setStatus(Status.SOLD);
	    oferta4b.setStatus(Status.SOLD);
	    oferta4c.setStatus(Status.ONSALE);
	    
	    oferta4a.setPrice(12.6);
	    oferta4b.setPrice(63);
	    oferta4c.setPrice(21.50);
	    Sale oferta5a = new Sale();
	    Sale oferta5b = new Sale();
	    Sale oferta5c = new Sale();
	    oferta5a.setTitle("Oferta 5a");
	    oferta5b.setTitle("Oferta 5b");
	    oferta5c.setTitle("Oferta 5c");
	    oferta5a.setOwner(user5);
	    oferta5b.setOwner(user5);
	    oferta5c.setOwner(user5);
	    oferta5a.setValid(true);
	    oferta5b.setValid(true);
	    oferta5c.setValid(true);
	    oferta5a.setStatus(Status.SOLD);
	    oferta5b.setStatus(Status.ONSALE);
	    oferta5c.setStatus(Status.SOLD);
	    oferta5a.setPrice(47.23);
	    oferta5b.setPrice(222);
	    oferta5c.setPrice(2.50);
	    //Compras
	    oferta1a.setBuyer(user2);
	    oferta1b.setBuyer(user3);
	    
	    oferta2c.setBuyer(user1);
	    oferta2b.setBuyer(user4);
	    
	    oferta3a.setBuyer(user4);
	    oferta3c.setBuyer(user5);
	    
	    oferta4a.setBuyer(user3);
	    oferta4b.setBuyer(user5);
	    
	    oferta5a.setBuyer(user1);
	    oferta5c.setBuyer(user2);
	    
	    saleRepo.save(oferta1a);
	    saleRepo.save(oferta1b);
	    saleRepo.save(oferta1c);
	    
	    saleRepo.save(oferta2a);
	    saleRepo.save(oferta2b);
	    saleRepo.save(oferta2c);
	    
	    saleRepo.save(oferta3a);
	    saleRepo.save(oferta3b);
	    saleRepo.save(oferta3c);
	    
	    saleRepo.save(oferta4a);
	    saleRepo.save(oferta4b);
	    saleRepo.save(oferta4c);
	    
	    saleRepo.save(oferta5a);
	    saleRepo.save(oferta5b);
	    saleRepo.save(oferta5c);
	    
	    Conversacion conversacion = new Conversacion();
	    conversacion.setBuyer(user1);
	    conversacion.setOwner(user2);
	    conversacion.setSale(oferta2a);
	    
	    Mensaje mensaje1 = new Mensaje();
	    mensaje1.setConversacion(conversacion);
	    mensaje1.setContenido("kiss my ass goodbye");
	    mensaje1.setEmisor(user1.getEmail());
	    mensaje1.setFecha(new Date());
	    
	    Mensaje mensaje2 = new Mensaje();
	    mensaje2.setConversacion(conversacion);
	    mensaje2.setContenido("or you could sit and watch me cry");
	    mensaje2.setEmisor(user2.getEmail());
	    mensaje2.setFecha(new Date());
	    conversacionRepo.save(conversacion);
	    mensajesRepo.save(mensaje1);
	    mensajesRepo.save(mensaje2);
	    
	    
    }

}
