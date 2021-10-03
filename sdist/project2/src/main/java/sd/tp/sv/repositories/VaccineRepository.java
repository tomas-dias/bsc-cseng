package sd.tp.sv.repositories;

import org.springframework.data.repository.CrudRepository;
import sd.tp.sv.models.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine, String>
{
    Vaccine findByName(String name);
}
