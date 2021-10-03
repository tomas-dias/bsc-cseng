package sd.tp.sv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.tp.sv.models.User;
import sd.tp.sv.repositories.UserRepository;

@Service
public class RegisterService
{
    @Autowired
    UserRepository userRepository;

    public String register(String email, String password)
    {
        if(userRepository.findByEmail(email) != null)
        {
            return "User already registered.";
        }

        userRepository.save(new User(email, password));

        return "User registered successfully.";
    }
}