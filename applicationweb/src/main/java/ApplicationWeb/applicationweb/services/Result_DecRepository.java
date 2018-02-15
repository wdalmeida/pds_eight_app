package ApplicationWeb.applicationweb.services;

import ApplicationWeb.applicationweb.model.NotificationModel;
import ApplicationWeb.applicationweb.model.Result_DecModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Result_DecRepository extends CrudRepository<Result_DecModel, Integer> {

    /*
    @Query("SELECT idaccount, status, recipient, label, details, amount, date FROM notification")
    List<NotificationModel> findAll(); */
}