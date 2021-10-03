package sd.tp.sv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.repositories.VaccinatedRepository;
import sd.tp.sv.repositories.VaccineRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class ListVaccinatedUsersService
{
    @Autowired
    VaccinatedRepository vaccinatedRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    public Map<String, Integer> listVaccinatedUsers(String date)
    {
        Map<String, Integer> list = new HashMap<>();

        vaccineRepository.findAll().forEach(vaccine -> list.put(vaccine.getName(),
                vaccinatedRepository.countByVaccineAndDate(vaccine.getName(), date)));

        return list;
    }
}
