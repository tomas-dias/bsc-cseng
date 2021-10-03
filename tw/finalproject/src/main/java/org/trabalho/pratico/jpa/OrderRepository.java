package org.trabalho.pratico.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUsername(String name);

    Order findById(long id);

    Order findOneByUsername(String username);

    Order findByName(String name);

    Order findByNameAndUsername(String name, String username);
}
