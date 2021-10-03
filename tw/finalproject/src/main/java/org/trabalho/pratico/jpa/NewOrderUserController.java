package org.trabalho.pratico.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class NewOrderUserController {

    private static final Logger log = LoggerFactory.getLogger(NewOrderUserController.class);
    private float price;
    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/user/new-order")
    public String newOrder(
            @RequestParam(name = "payment") String payment,
            @RequestParam(name = "quantity") int quantity,
            @RequestParam (name = "name") String name,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Product product = productRepository.findByName(name);

        Order order = repository.findByNameAndUsername(product.getName(), auth.getName());

        if(order == null) {
            repository.save(new Order(payment, quantity, product.getName(), product.getPrice() * quantity, auth.getName()));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Order aOrder : repository.findAll()) {
                log.info(aOrder.toString());
            }
            log.info("");
        }else if(order.getUsername().equals(auth.getName())){
            repository.delete(order);

            repository.save(new Order(payment, quantity, product.getName(), product.getPrice() * quantity, auth.getName()));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Order aOrder : repository.findAll()) {
                log.info(aOrder.toString());
            }
            log.info("");
        }

        model.addAttribute("username", auth.getName());
        model.addAttribute("productName", product.getName());
        model.addAttribute("price", product.getPrice() * quantity);
        model.addAttribute("payment", payment);
        model.addAttribute("quantity", quantity);

        return "user/new-order-view";
    }
}