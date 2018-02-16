package ApplicationWeb.applicationweb.services;

import ApplicationWeb.applicationweb.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationModel, Integer> {

/*
    @Query("SELECT amount, date, detail, iban,label, recipient, status FROM notification where iban=1")
    List<NotificationModel> findClient();*/
}