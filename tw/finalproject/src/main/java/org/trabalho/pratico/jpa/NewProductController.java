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
public class NewProductController {

    private static final Logger log = LoggerFactory.getLogger(NewProductController.class);

    @Autowired
    private ProductRepository repository;

    @PostMapping("/add-product")
    public String newProduct(
            @RequestParam(name="product_name") String name,
            @RequestParam(name="product_description") String description,
            @RequestParam(name="product_price") float price,
            Model model
    )
    {

        repository.save(new Product(name, description, price));

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product aProduct : repository.findAll()) {
            log.info(aProduct.toString());
        }
        log.info("");

        List<Product> productList = (List<Product>) repository.findAll();

//        model.addAttribute("name", name);
//        model.addAttribute("description", description);
//        model.addAttribute("price", price);
        model.addAttribute("productList", productList);
        return "admin/new_product_view";
    }
}
