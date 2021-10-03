package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.models.Centre;
import sd.tp.sv.services.ListCentresService;

@RestController
public class ListCentresController
{
    @Autowired
    ListCentresService service;

    @GetMapping("/centres")
    public Iterable<Centre> listCentres(@RequestParam String date)
    {
        return service.listCentres(date);
    }
}