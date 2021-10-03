package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.Appointment;
import sd.tp.sv.models.User;
import sd.tp.sv.models.Centre;
import sd.tp.sv.models.Vaccinated;
import sd.tp.sv.repositories.AppointmentRepository;
import sd.tp.sv.repositories.UserRepository;
import sd.tp.sv.repositories.CentreRepository;
import sd.tp.sv.repositories.VaccinatedRepository;

@Service
public class DoAppointmentService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    CentreRepository centreRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    VaccinatedRepository vaccinatedRepository;

    public String doAppointment(String email, String password, String name,
                                int age, String date, String centreName)
    {
        User user = userRepository.findByEmail(email);
        Centre centre = centreRepository.findByNameAndDate(centreName, date);
        Appointment appointment = appointmentRepository.findByEmail(email);
        Vaccinated vaccinated = vaccinatedRepository.findByEmail(email);

        if(!user.getPassword().equals(password))
            return "Credentials doesn't match.";
        if(appointment != null)
            return String.format("Appointment from user with email='%s' to date='%s' already done pending confirmation.",
                    user.getEmail(), date);
        if(vaccinated != null)
            return String.format("Only one appointment is allowed. User with email='%s' already vaccinated", user.getEmail());
        if(centre == null || !centre.getAvailability())
            return String.format("Centre with name='%s' not found or not operating on date='%s'.",
                    centreName, date);

        appointmentRepository.save(new Appointment(name, age, user, centre));

        return "Appointment done.";
    }
}
