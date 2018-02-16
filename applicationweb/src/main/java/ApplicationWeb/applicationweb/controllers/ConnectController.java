package ApplicationWeb.applicationweb.controllers;
import ApplicationWeb.applicationweb.model.NotificationModel;
import ApplicationWeb.applicationweb.model.Result_DecModel;
import ApplicationWeb.applicationweb.services.NotificationService;
import ApplicationWeb.applicationweb.services.Result_DecService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ConnectController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private Result_DecService result_decService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        logger.info("home page displayed");
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public ModelAndView getConnectPage(){
        logger.info("home page displayed");
        return new ModelAndView("ClientConnect");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomeClient(){
        logger.info("home page displayed");
        return new ModelAndView("ClientHome");
    }
    @RequestMapping(value = "/analyse", method = RequestMethod.GET)
    public ModelAndView getHomeAnalyse(Model model){
        List<Result_DecModel> result = result_decService.findAll();
        model.addAttribute("result", result);
        logger.info("info result_decouvert controller"+ result);
        return new ModelAndView("analyseHome");
    }
    @RequestMapping(value = "/notif", method = RequestMethod.GET)
    public String listNotification(Model model) {
        List<NotificationModel> notif = notificationService.findAll();
        model.addAttribute("notifications", notif);
        logger.info("info notifications controller"+ notif);
        return "listNotification";
    }
}