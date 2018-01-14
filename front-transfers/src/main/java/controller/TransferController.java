package controller;

import model.TransferModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import org.apache.log4j.Logger;
import producer.TransferProducer;

@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @RequestMapping(value="/submit", method={RequestMethod.GET})
    public ModelAndView getTransferForm() {
        ModelAndView mav = new ModelAndView("createTransferForm");
        mav.addObject("transferModel", new TransferModel());
        logger.info("Transfer form displayed...");
        return mav;
    }

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ModelAndView submitTransfer(@ModelAttribute("transferModel") TransferModel transferModel, BindingResult bindingResult) {
        logger.info(transferModel.toString());
        new TransferProducer().sendTransfer(transferModel);
        ModelAndView mav = new ModelAndView("response");
        return mav;

    }
}

