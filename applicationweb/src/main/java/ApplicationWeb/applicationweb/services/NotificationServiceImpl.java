package ApplicationWeb.applicationweb.services;

import ApplicationWeb.applicationweb.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<NotificationModel> findAll() {
        return (List<NotificationModel>) this.notificationRepository.findAll();
    }

  /*  @Override
    public List<NotificationModel> findClient(){
        return (List<NotificationModel>) this.notificationRepository.findClient();}*/
}
