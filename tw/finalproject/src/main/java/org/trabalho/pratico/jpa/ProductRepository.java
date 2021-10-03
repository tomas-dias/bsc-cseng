package org.trabalho.pratico.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    Product findByName(String name);

    Product findByNameAndPrice(String name, float price);

    Product findById(long id);
}
