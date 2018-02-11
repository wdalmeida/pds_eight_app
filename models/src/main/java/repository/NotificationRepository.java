package repository;

import entity.AccounEntity;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<AccounEntity,String> {
}
