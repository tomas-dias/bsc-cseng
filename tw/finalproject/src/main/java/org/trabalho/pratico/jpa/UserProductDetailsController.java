package org.trabalho.pratico.jpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserProductDetailsController {

    private static final Logger log = LoggerFactory.getLogger(UserProductDetailsController.class);

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/user/product-details")
    public String userProductDetails(@RequestParam(name="name") String name, Model model){

        Product product = productRepository.findByName(name);

        model.addAttribute("name", product.getName());
        model.addAttribute("description", product.getDescription());
        model.addAttribute("price", product.getPrice());
        return "user/product";
    }
}
