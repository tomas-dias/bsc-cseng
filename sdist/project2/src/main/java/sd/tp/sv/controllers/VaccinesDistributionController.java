package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.services.VaccinesDistributionService;

@RestController
public class VaccinesDistributionController
{
    @Autowired
    VaccinesDistributionService service;

    @PostMapping("/vaccines-distribution")
    public ResponseEntity vaccinesDistribution(@RequestParam int numVaccines,
                                               @RequestParam String date)
    {
        String message = service.vaccinesDistribution(numVaccines, date);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}