package sd.tp.sv.repositories;

import org.springframework.data.repository.CrudRepository;
import sd.tp.sv.models.Vaccinated;
import java.util.List;

public interface VaccinatedRepository extends CrudRepository<Vaccinated, Long>
{
    Integer countByVaccineAndDate(String vaccine, String date);

    Vaccinated findByEmail(String email);
}