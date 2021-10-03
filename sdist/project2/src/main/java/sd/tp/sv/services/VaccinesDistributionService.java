package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.Appointment;
import sd.tp.sv.models.User;
import sd.tp.sv.models.Notification;
import sd.tp.sv.repositories.AppointmentRepository;
import sd.tp.sv.repositories.CentreRepository;
import sd.tp.sv.repositories.NotificationRepository;
import sd.tp.sv.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VaccinesDistributionService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CentreRepository centreRepository;

    @Autowired
    NotificationRepository notificationRepository;

    public void cancelAppointment(boolean condition, int diff,
                                  List<Appointment> appointments, String formattedDate)
    {
        if(condition)
        {
            for (int i = 0; i < diff; i++)
            {
                User user = userRepository.findByEmail(appointments.get(i).getUser().getEmail());

                String message = String.format("[%s] Impossibilidade de vacinação no dia '%s' no centro '%s'." +
                        " Por favor, reagende quando possível.",
                        formattedDate,
                        appointments.get(i).getCentre().getDate(),
                        appointments.get(i).getCentre().getName());

                notificationRepository.save(new Notification(
                        appointments.get(i).getId(), user, message));

                appointmentRepository.deleteById(appointments.get(i).getId());
            }
        }
    }

    public void confirmAppointment(String date, String formattedDate)
    {
        appointmentRepository.findByDate(date).forEach(appointment ->
        {
            User user = userRepository.findByEmail(appointment.getUser().getEmail());

            String message = String.format("[%s] Agendamento confirmado para o dia '%s' no centro '%s'",
                    formattedDate,
                    appointment.getCentre().getDate(),
                    appointment.getCentre().getName());

            notificationRepository.save(new Notification(
                    appointment.getId(),
                    user,
                    message
            ));
        });
    }

    public String vaccinesDistribution(int numVaccines, String date)
    {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);

        //check if the number of appointments for all centres are within their capacities
        centreRepository.findByDate(date).forEach(centre ->
        {
            List<Appointment> appointments = appointmentRepository.orderByAgeWhereDateAndCentre(date, centre.getName());

            int numAppointments = appointmentRepository.countByDateAndCentreName(date, centre.getName());

            cancelAppointment(
                    numAppointments > centre.getTotalVaccines(),
                    numAppointments - centre.getTotalVaccines(),
                    appointments,
                    formattedDate);
        });

        int numAppointments = appointmentRepository.countByDate(date);
        List<Appointment> appointments = appointmentRepository.orderByAgeWhereDate(date);

        //check if the number of appointments is higher than the number of vaccines distributed
        cancelAppointment(
                numAppointments > numVaccines,
                numAppointments - numVaccines,
                appointments,
                formattedDate);

        //notifies users of the appointment
        confirmAppointment(date, formattedDate);

        //set the availability of centres for a given date to false
        centreRepository.findByDate(date).forEach(centre ->
        {
            centre.setAvailability(false);
            centreRepository.save(centre);
        });

        return String.format("Vaccines were distributed to date='%s'.", date);
    }
}
