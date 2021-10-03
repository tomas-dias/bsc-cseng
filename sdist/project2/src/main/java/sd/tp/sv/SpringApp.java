package sd.tp.sv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sd.tp.sv.models.Appointment;
import sd.tp.sv.models.Centre;
import sd.tp.sv.models.User;
import sd.tp.sv.models.Vaccine;
import sd.tp.sv.repositories.AppointmentRepository;
import sd.tp.sv.repositories.CentreRepository;
import sd.tp.sv.repositories.UserRepository;
import sd.tp.sv.repositories.VaccineRepository;

@SpringBootApplication
public class SpringApp
{
    private static final Logger log = LoggerFactory.getLogger(SpringApp.class);

    public static void main(String[] args)
    {
        SpringApplication.run(SpringApp.class, args);
    }

/*    @Bean
    public CommandLineRunner demo(
            UserRepository userRepository,
            AppointmentRepository appointmentRepository,
            CentreRepository centreRepository,
            VaccineRepository vaccineRepository)
    {
        return (args) ->
        {
            log.info("StartApplication...");

            User u1, u2, u3, u4, u5;
            u1 = new User("l42720@alunos.uevora.pt", "pass");
            u2 = new User("manuel@alunos.uevora.pt", "pass");
            u3 = new User("antonio@alunos.uevora.pt", "pass");
            u4 = new User("l42784@alunos.uevora.pt", "pass");
            u5 = new User("jose@alunos.uevora.pt", "pass");

            userRepository.save(u1);
            userRepository.save(u2);
            userRepository.save(u3);
            userRepository.save(u4);
            userRepository.save(u5);

            Centre c1, c2;
            c1 = new Centre("CV Évora", 3, "10-07-2021", true);
            c2 = new Centre("CV Lisboa",2, "10-07-2021", true);

            centreRepository.save(c1);
            centreRepository.save(c2);

            appointmentRepository.save(new Appointment(
                    "Rui", 20, u1, c1));
            appointmentRepository.save(new Appointment(
                    "Manuel", 35, u2, c1));
            appointmentRepository.save(new Appointment(
                    "António", 45, u3, c1));
            appointmentRepository.save(new Appointment(
                    "Tomás", 19, u4, c2));
            appointmentRepository.save(new Appointment(
                    "José", 50, u5, c2));

            vaccineRepository.save(new Vaccine("Pfizer"));
            vaccineRepository.save(new Vaccine("Moderna"));
            vaccineRepository.save(new Vaccine("Astrazeneca"));
            vaccineRepository.save(new Vaccine("Johnson"));
            vaccineRepository.save(new Vaccine("Sputnik"));

            log.info("Users found with findAll():");
            log.info("-------------------------------");
            userRepository.findAll().forEach(user -> log.info(user.toString()));

            log.info("Centres found with findAll():");
            log.info("-------------------------------");
            centreRepository.findAll().forEach(centre -> log.info(centre.toString()));

            log.info("Appointments found with findAll():");
            log.info("-------------------------------");
            appointmentRepository.findAll().forEach(appointment -> log.info(appointment.toString()));

            log.info("Vaccines found with findAll():");
            log.info("-------------------------------");
            vaccineRepository.findAll().forEach(vaccine -> log.info(vaccine.toString()));

            log.info("");
        };
    }*/
}