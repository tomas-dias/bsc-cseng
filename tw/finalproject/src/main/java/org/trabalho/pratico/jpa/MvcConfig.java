package org.trabalho.pratico.jpa;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    public void addViewControllers(ViewControllerRegistry registry) {
        //Não Registados
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/registo").setViewName("registo");
        registry.addViewController("/new-user").setViewName("new-user");
        registry.addViewController("/new-search").setViewName("new-search");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/advanced_search").setViewName("advanced_search");
        registry.addViewController("/ps5").setViewName("ps5");
        registry.addViewController("/new-search").setViewName("new-search");
        registry.addViewController("/xbox").setViewName("xbox");
        registry.addViewController("/new-search-view").setViewName("new-search-view");
        registry.addViewController("/lista-produtos").setViewName("lista-produtos");
        registry.addViewController("/lista").setViewName("lista");
        //User
        registry.addViewController("/user/index").setViewName("user/index");
        registry.addViewController("/user/ps5").setViewName("user/ps5");
        registry.addViewController("/user/advanced_search").setViewName("user/advanced_search");
        registry.addViewController("/user/current-user").setViewName("user/current-user");
        registry.addViewController("/user/perfil").setViewName("user/perfil");
        registry.addViewController("/user/new-order").setViewName("user/new-order");
        registry.addViewController("/user/xbox").setViewName("user/xbox");
        registry.addViewController("/user/new-order-view").setViewName("user/new-order-view");
        registry.addViewController("/user/lista-produtos").setViewName("user/lista-produtos");
        registry.addViewController("/user/lista").setViewName("user/lista");
        registry.addViewController("/user/new-search").setViewName("user/new-search");
        registry.addViewController("/user/new-search-view").setViewName("user/new-search-view");
        registry.addViewController("/user/shopping-cart").setViewName("user/shopping-cart");
        registry.addViewController("/user/shopping-cart-view").setViewName("user/shopping-cart-view");
        registry.addViewController("/user/product-details").setViewName("user/product-details");
        registry.addViewController("/user/product").setViewName("user/product");

        //registry.addViewController("/shoppingc-cart").setViewName("shopping-cart");
        //registry.addViewController("/shoppingc-cart-view").setViewName("shopping-cart-view");

        //Admin
        registry.addViewController("/admin/index").setViewName("admin/index");
        registry.addViewController("/admin/add_product").setViewName("admin/add_product");
        registry.addViewController("/add-product").setViewName("add-product");
        registry.addViewController("/admin/advanced_search").setViewName("admin/advanced_search");
        registry.addViewController("/admin/current-user").setViewName("admin/current-user");
        registry.addViewController("/admin/perfil").setViewName("admin/perfil");
        registry.addViewController("/admin/ps5").setViewName("admin/ps5");
        registry.addViewController("/admin/xbox").setViewName("admin/xbox");
        registry.addViewController("/admin/new-order").setViewName("admin/new-order");
        registry.addViewController("/admin/new_product_view").setViewName("admin/new_product_view");
        registry.addViewController("/admin/lista-produtos").setViewName("admin/lista-produtos");
        registry.addViewController("/admin/lista").setViewName("admin/lista");
        registry.addViewController("/admin/new-search").setViewName("admin/new-search");
        registry.addViewController("/admin/new-search-view").setViewName("admin/new-search-view");
    }

}
