package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.Centre;
import sd.tp.sv.repositories.CentreRepository;

@Service
public class ListCentresService
{
    @Autowired
    CentreRepository centreRepository;

    public Iterable<Centre> listCentres(String date)
    {
        return centreRepository.findByDateAndAvailability(date, true);
    }
}