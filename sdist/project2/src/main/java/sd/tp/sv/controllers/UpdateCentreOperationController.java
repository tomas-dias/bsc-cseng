package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.services.UpdateCentreOperationService;

@RestController
public class UpdateCentreOperationController
{
    @Autowired
    UpdateCentreOperationService service;

    @PostMapping("/update-centre-operation")
    public ResponseEntity updateCentreOperation(@RequestParam String name,
                                                @RequestParam int totalVaccines,
                                                @RequestParam String date)
    {
        String message = service.updateCentreOperation(name, totalVaccines, date);

        if(message.equals(String.format(
                "Centre with name='%s' now operating on date='%s' with capacity=%d.",
                name, date, totalVaccines)))

            return new ResponseEntity<>(message, HttpStatus.CREATED);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}