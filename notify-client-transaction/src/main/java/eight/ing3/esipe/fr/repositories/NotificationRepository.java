package eight.ing3.esipe.fr.repositories;

import eight.ing3.esipe.fr.bean.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    void save();
}
