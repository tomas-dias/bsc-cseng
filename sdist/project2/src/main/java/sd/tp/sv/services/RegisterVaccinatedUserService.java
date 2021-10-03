package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.Centre;
import sd.tp.sv.models.Appointment;
import sd.tp.sv.models.Vaccinated;
import sd.tp.sv.repositories.AppointmentRepository;
import sd.tp.sv.repositories.CentreRepository;
import sd.tp.sv.repositories.VaccinatedRepository;
import sd.tp.sv.repositories.VaccineRepository;

@Service
public class RegisterVaccinatedUserService
{
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CentreRepository centreRepository;

    @Autowired
    VaccinatedRepository vaccinatedRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    public String registerVaccinatedUser(Long id, String vaccine)
    {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);

        if(appointment == null)
            return String.format("Appointment with id=%d not found.", id);

        Centre centre = centreRepository
                .findByNameAndDate(
                        appointment.getCentre().getName(),
                        appointment.getCentre().getDate());

        if(centre == null || centre.getAvailability())
            return String.format(
                    "Centre with name='%s' not found or still accepting appointments to date='%s'.",
                    appointment.getCentre().getName(), appointment.getCentre().getDate());

        if(vaccinatedRepository.findById(id).isPresent())
        {
            return String.format("User with appointment id=%d already vaccinated.", id);
        }

        if(vaccineRepository.findByName(vaccine) == null)
        {
            return String.format("Vaccine with name='%s' not found.", vaccine);
        }


        appointmentRepository.deleteById(id);
        vaccinatedRepository.save(new Vaccinated(id, appointment.getUser().getEmail(), appointment.getCentre().getDate(), vaccine));

        return String.format("Registered vaccinated user with appointment id=%d.", id);
    }
}