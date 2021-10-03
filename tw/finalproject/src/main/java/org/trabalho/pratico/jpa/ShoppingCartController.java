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

import java.util.List;


@Controller
public class ShoppingCartController {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    private float price;

    @PostMapping("/user/shopping-cart")
    public String newShoppingCart(
            Model model)
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Order> orderList = (List<Order>) repository.findByUsername(auth.getName());

//        log.info("Products found with findByNameContainingIgnoreCase():");
//        log.info("-------------------------------");
//        for (Product product : repository.findByNameContainingIgnoreCase(name)) {
//            log.info(product.toString());
//        }
//        log.info("");

        float aux = 0;
        for(Order order:orderList){
            price = aux + order.getPrice();
            aux = order.getPrice();
        }if(orderList.isEmpty()){
        price = 0;
        }
        model.addAttribute("orderList", orderList);
        model.addAttribute("total", price);
        return "user/shopping-cart-view";

    }
}