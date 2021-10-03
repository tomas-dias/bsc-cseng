package org.trabalho.pratico.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByFirstName(String firstName);

	User findById(long id);

	User findOneByUsername(String username);

}
