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
public class RemoveProductFromCartController {

    private static final Logger log = LoggerFactory.getLogger(RemoveProductFromCartController.class);

    @Autowired
    OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    float price;

    @PostMapping("/user/remove")
    public String newShoppingCart( @RequestParam(name="name") String name,

            Model model)
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Order order = repository.findByNameAndUsername(name, auth.getName());
        repository.delete(order);

        List<Order> orderList = (List<Order>) repository.findByUsername(auth.getName());

        float aux = 0;
        for(Order order2:orderList){
            price = aux + order2.getPrice();
            aux = order2.getPrice();
        }
        if(orderList.isEmpty()){
            price = 0;
        }

        model.addAttribute("orderList", orderList);
        model.addAttribute("total", price);
        return "user/shopping-cart-view";

    }
}