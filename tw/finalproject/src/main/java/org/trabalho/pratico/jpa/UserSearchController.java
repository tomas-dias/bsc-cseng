package org.trabalho.pratico.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserSearchController {

    private static final Logger log = LoggerFactory.getLogger(UserSearchController.class);

    @Autowired
    ProductRepository repository;

    @PostMapping("/user/new-search")
    public String newSearch(
            @RequestParam(name="search_term") String name,
            Model model)
    {

        List<Product> productList = (List<Product>) repository.findByNameContainingIgnoreCase(name);

        log.info("Products found with findByNameContainingIgnoreCase():");
        log.info("-------------------------------");
        for (Product product : repository.findByNameContainingIgnoreCase(name)) {
            log.info(product.toString());
        }
        log.info("");

        model.addAttribute("productList", productList);
        return "user/new-search-view";

    }
}