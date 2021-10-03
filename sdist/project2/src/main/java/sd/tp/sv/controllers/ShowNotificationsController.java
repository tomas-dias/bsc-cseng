package sd.tp.sv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.tp.sv.models.Notification;
import sd.tp.sv.services.ShowNotificationsService;

@RestController
public class ShowNotificationsController
{
    @Autowired
    ShowNotificationsService service;

    @GetMapping("/notifications")
    public Iterable<Notification> showNotifications(@RequestParam String email,
                                                    @RequestParam String password)
    {
        return service.showNotifications(email, password);
    }
}