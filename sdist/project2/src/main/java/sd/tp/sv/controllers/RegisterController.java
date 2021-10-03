package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.services.RegisterService;

@RestController
public class RegisterController
{
    @Autowired
    RegisterService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam String email, @RequestParam String password)
    {
        String message = service.register(email, password);

        if(message.equals("User registered successfully."))
            return new ResponseEntity<>(message, HttpStatus.CREATED);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
