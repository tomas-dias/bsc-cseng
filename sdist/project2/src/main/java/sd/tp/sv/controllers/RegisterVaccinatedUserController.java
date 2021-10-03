package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.services.RegisterVaccinatedUserService;

@RestController
public class RegisterVaccinatedUserController
{
    @Autowired
    RegisterVaccinatedUserService service;

    @PostMapping("/register-vaccinated-user")
    public ResponseEntity registerVaccinatedUser(@RequestParam Long id,
                                                 @RequestParam String vaccine)
    {
        String message = service.registerVaccinatedUser(id, vaccine);

        if(message.equals(String.format("Registered vaccinated user with appointment id=%d.", id)))
            return new ResponseEntity<>(message, HttpStatus.CREATED);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
