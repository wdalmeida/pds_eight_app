package ApplicationWeb.applicationweb.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private  NotificationDAO notificationDAO;

    protected final Log logger = LogFactory.getLog(getClass());

    @Transactional
    public List<NotificationModel> getAllNotification() {
        logger.info("service"+ notificationDAO.findAll());
        return notificationDAO.findAll();

    }

}
