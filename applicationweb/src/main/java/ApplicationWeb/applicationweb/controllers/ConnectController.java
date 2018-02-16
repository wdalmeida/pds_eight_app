package ApplicationWeb.applicationweb.controllers;
;
import ApplicationWeb.applicationweb.model.NotificationModel;
import ApplicationWeb.applicationweb.model.NotificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.Notification;
import java.util.Collection;
import java.util.List;

@Controller
public class ConnectController {

    protected final Log logger = LogFactory.getLog(getClass());

@Autowired
private NotificationService notificationService;
    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        logger.info("info");
        return "test";
    }*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String app() {
        logger.info("info");
        return "home";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String welcomeCustomer() {
        logger.info("info connect Customer");
        return "ClientConnect";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String customerHome() {
        logger.info("info home Customer");
        return "ClientHome";
    }
    @RequestMapping(value = "/analyse", method = RequestMethod.GET)
    public String analyseHome() {
        logger.info("info home analyse");
        return "analyseHome";
    }
    @RequestMapping(value = "/notif", method = RequestMethod.GET)
    public String listNotification(Model model,
                                   @RequestParam(value = "recipient", required = false) String recipient,
                                   @RequestParam(value = "label", required = false) String label,
                                   @RequestParam(value = "details", required = false) String details,
                                   @RequestParam(value = "date", required = false) String date,
                                   @RequestParam(value = "idaccount", required = false) String idaccount,
                                   @RequestParam(value = "amount", required = false) String amount) {
        List<NotificationModel> notif = notificationService.getAllNotification();
        model.addAttribute("notifications", notif);
        logger.info("info notifications controller"+ notif);
        return "listNotification";
    }
}