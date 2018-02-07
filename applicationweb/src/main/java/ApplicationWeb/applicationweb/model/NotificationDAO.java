package ApplicationWeb.applicationweb.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationDAO extends CrudRepository<NotificationModel, Integer>{

    @Override
    public List<NotificationModel> findAll();
}
