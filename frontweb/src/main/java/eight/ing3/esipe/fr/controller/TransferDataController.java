package eight.ing3.esipe.fr.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransferDataController {

    private static Logger logger = Logger.getLogger(TransferDataController.class);

    @RequestMapping(value="/analyst/transfersMining", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        logger.info("akekoukou");
        return null;
    }
}
