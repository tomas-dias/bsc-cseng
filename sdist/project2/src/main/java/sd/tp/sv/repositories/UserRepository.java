package sd.tp.sv.repositories;

import org.springframework.data.repository.CrudRepository;
import sd.tp.sv.models.User;

public interface UserRepository extends CrudRepository<User, String>
{
    User findByEmail(String email);
}