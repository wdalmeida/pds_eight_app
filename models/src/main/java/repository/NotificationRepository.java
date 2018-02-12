package repository;

import entity.NotificationEntity;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<NotificationEntity,String> {

}
