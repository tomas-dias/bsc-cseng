package org.trabalho.pratico.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ListProductsAdminController {

    private static final Logger log = LoggerFactory.getLogger(ListProductsAdminController.class);

    @Autowired
    private ProductRepository repository;

    @PostMapping("/admin/lista-produtos")
    public String currentUser(Model model)
    {
        List<Product> productList = (List<Product>) repository.findAll();
        model.addAttribute("lista", productList);
        return "admin/lista";
    }
}
