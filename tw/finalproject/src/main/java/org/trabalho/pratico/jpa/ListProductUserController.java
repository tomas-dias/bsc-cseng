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

import java.util.List;


@Controller
public class ListProductUserController {

    private static final Logger log = LoggerFactory.getLogger(ListProductUserController.class);

    @Autowired
    private ProductRepository repository;

    @PostMapping("/user/lista-produtos")
    public String currentUser(Model model)
    {
        List<Product> productList = (List<Product>) repository.findAll();
        model.addAttribute("lista", productList);
        return "user/lista";
    }
}
