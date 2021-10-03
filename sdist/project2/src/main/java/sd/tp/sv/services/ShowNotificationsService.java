package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.User;
import sd.tp.sv.models.Notification;
import sd.tp.sv.repositories.UserRepository;

@Service
public class ShowNotificationsService
{
    @Autowired
    UserRepository userRepository;

    public Iterable<Notification> showNotifications(String email, String password)
    {
        User user = userRepository.findByEmail(email);

        if(user.getPassword().equals(password))
            return user.getNotifications();

        return null;
    }
}