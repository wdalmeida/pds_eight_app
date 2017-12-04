package controller;

import model.TransferModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @RequestMapping(value="/submit", method={RequestMethod.GET})
    public ModelAndView getTransferForm() {
        ModelAndView mav = new ModelAndView("createTransferForm");
        mav.addObject("transferModel", new TransferModel());
        return mav;
    }

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ModelAndView submitTransfer() {
        ModelAndView mav = new ModelAndView("response");
        mav.addObject("message", "ok");
        return mav;
    }
}
