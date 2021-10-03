package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.services.DoAppointmentService;

@RestController
public class DoAppointmentController
{
    @Autowired
    DoAppointmentService service;

    @PostMapping("/appointment")
    public ResponseEntity doAppointment(@RequestParam String email,
                                        @RequestParam String password,
                                        @RequestParam String name,
                                        @RequestParam int age, @RequestParam String date,
                                        @RequestParam String centreName)
    {
        String message = service.doAppointment(email, password, name, age, date, centreName);

        if(message.equals("Appointment done."))
            return new ResponseEntity<>(message, HttpStatus.CREATED);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}