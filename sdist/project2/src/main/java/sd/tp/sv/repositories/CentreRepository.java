package sd.tp.sv.repositories;

import org.springframework.data.repository.CrudRepository;
import sd.tp.sv.models.Centre;
import sd.tp.sv.models.CentreId;

import java.util.List;

public interface CentreRepository extends CrudRepository<Centre, CentreId>
{
    Centre findByNameAndDate(String name, String date);
    List<Centre> findByDateAndAvailability(String date, boolean availability);
    List<Centre> findByDate(String date);
}