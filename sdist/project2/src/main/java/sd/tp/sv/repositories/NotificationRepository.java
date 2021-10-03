package sd.tp.sv.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sd.tp.sv.models.Notification;
import sd.tp.sv.models.User;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {}