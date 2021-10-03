package org.trabalho.pratico.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CurrentUserController {

    private static final Logger log = LoggerFactory.getLogger(CurrentUserController.class);

    @Autowired
    private UserRepository repository;

    @PostMapping("/user/current-user")
    public String currentUser(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = repository.findOneByUsername(auth.getName());
        model.addAttribute("username", auth.getName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("name", user.getFirstName());
        return "user/perfil";
    }
}
