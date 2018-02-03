package eight.ing3.esipe.fr.controller;

import model.LoginModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        logger.info("home page displayed");
        return new ModelAndView("home");
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(){
        logger.info("login page displayed");
        ModelAndView mav = new ModelAndView("loginPage");
        mav.addObject("loginModel", new LoginModel());
        return mav;
    }

    @RequestMapping(value="/customerHomePage", method = RequestMethod.POST)
    public ModelAndView displayCustomerHomePage(@ModelAttribute("loginModel") LoginModel loginModel, BindingResult bindingResult){
        logger.info("customer home page displayed");
        ModelAndView mav = new ModelAndView("customerHomePage");
        mav.addObject("loginModel", new LoginModel());
        return mav;
    }

    @RequestMapping(value="/transfers", method = RequestMethod.GET)
    public ModelAndView getTransfersHomePage(){
        logger.info("transfers home page displayed");
        return new ModelAndView("transfersHomePage");
    }
}
