package sd.tp.sv.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sd.tp.sv.models.Appointment;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long>
{
    @Query(value = "SELECT * FROM APPOINTMENT WHERE DATE = ?1", nativeQuery = true)
    List<Appointment> findByDate(String date);

    @Query(value = "SELECT * FROM APPOINTMENT WHERE EMAIL = ?1", nativeQuery = true)
    Appointment findByEmail(String email);

    @Query(value = "SELECT COUNT(DATE) FROM APPOINTMENT WHERE DATE = ?1", nativeQuery = true)
    Integer countByDate(String date);

    @Query(value = "SELECT COUNT(DATE) FROM APPOINTMENT WHERE DATE = ?1 AND CENTRE_NAME = ?2 GROUP BY CENTRE_NAME", nativeQuery = true)
    Integer countByDateAndCentreName(String date, String centreName);

    @Query(value = "SELECT * FROM APPOINTMENT WHERE DATE = ?1 AND CENTRE_NAME = ?2 ORDER BY AGE ASC", nativeQuery = true)
    List<Appointment> orderByAgeWhereDateAndCentre(String date, String centreName);

    @Query(value = "SELECT * FROM APPOINTMENT WHERE DATE = ?1 ORDER BY AGE ASC", nativeQuery = true)
    List<Appointment> orderByAgeWhereDate(String date);
}