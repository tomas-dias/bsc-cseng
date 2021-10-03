package org.trabalho.pratico.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewUserController {

	private static final Logger log = LoggerFactory.getLogger(NewUserController.class);

    @Autowired
    private UserRepository repository;
    
	@PostMapping("/new-user")
	public String newClient(
			@RequestParam(name="name", required=false, defaultValue="World") String name,
			@RequestParam(name = "lastname", required = false, defaultValue = "Nada") String lastname,
			@RequestParam(name = "email", required = false, defaultValue = "Não tem Email") String email,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password,
			Model model)
	{
		String encodedPassword = new BCryptPasswordEncoder().encode(password);
		repository.save(new User(name, lastname, email, username, encodedPassword, "ROLE_USER"));
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (User aClient : repository.findAll()) {
			log.info(aClient.toString());
		}
		log.info("");
		
		model.addAttribute("name", name);
		model.addAttribute("lastname", lastname);
		model.addAttribute("email", email);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("user");
		return "login";
	}
}
