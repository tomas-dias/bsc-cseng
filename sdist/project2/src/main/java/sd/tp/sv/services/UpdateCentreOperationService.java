package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.Centre;
import sd.tp.sv.repositories.CentreRepository;

@Service
public class UpdateCentreOperationService
{
    @Autowired
    CentreRepository centreRepository;

    public String updateCentreOperation(String name, int totalVaccines, String date)
    {
        if(centreRepository.findByNameAndDate(name, date) != null)
            return String.format("Centre with name='%s' already operating on date='%s'.", name, date);

        Centre centre = new Centre(name, totalVaccines, date, true);

        centreRepository.save(centre);

        return String.format("Centre with name='%s' now operating on date='%s' with capacity=%d.",
                name, date, totalVaccines);
    }
}
