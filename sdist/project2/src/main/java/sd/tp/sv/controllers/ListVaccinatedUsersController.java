package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.services.ListVaccinatedUsersService;

import java.util.Map;

@RestController
public class ListVaccinatedUsersController
{
    @Autowired
    ListVaccinatedUsersService service;

    @GetMapping("/vaccinated")
    public Map<String, Integer> listVaccinatedUsers(@RequestParam String date)
    {
        return service.listVaccinatedUsers(date);
    }
}
