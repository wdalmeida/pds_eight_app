package eight.ing3.esipe.fr.controller;

import eight.ing3.esipe.fr.entity.Transfer;
import eight.ing3.esipe.fr.entity.Tweet;
import eight.ing3.esipe.fr.view.DateFormView;
import entity.AccounEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TransferDataController {


    @Value("${big.data.transfers.url}")
    private String bigDataTransfersUrl;

    @Value("${big.data.tweets.url}")
    private String bigDataTweetsUrl;

    private static Logger logger = Logger.getLogger(TransferDataController.class);

    @RequestMapping(value="/analyst/transfersMining", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        logger.info("transfersMining home page");
        ModelAndView mav = new ModelAndView("analystTransfersMiningPage");
        mav.addObject("dateFormView", new DateFormView());
        return mav;
    }

    @RequestMapping(value="/analyst/transfersMining", method = RequestMethod.POST)
    public ModelAndView getData(@ModelAttribute("dateFormView") DateFormView dateFormView, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("analystTransfersMiningPage");
        RestTemplate restTemplate = new RestTemplate();

        //recover transfers
        ResponseEntity<List> transfersResponseEntity = restTemplate.getForEntity(
                bigDataTransfersUrl + dateFormView.getValueDate().toString() , List.class);
        if (transfersResponseEntity.getStatusCode() == HttpStatus.OK) {
            List<Transfer> transfers = transfersResponseEntity.getBody();
            mav.addObject("transfers",transfers);
            logger.info("transfers retrieved : " + transfers.toString());
        } else {
            logger.info("no transfers retrieved");
        }

        //recover tweets
        ResponseEntity<List> tweetsResponseEntity = restTemplate.getForEntity(
                bigDataTweetsUrl + dateFormView.getValueDate().toString() , List.class);
        if (tweetsResponseEntity.getStatusCode() == HttpStatus.OK) {
            List<Tweet> tweets = tweetsResponseEntity.getBody();
            mav.addObject("tweets",tweets);
            logger.info("tweets retrieved : " + tweets.toString());
        } else {
            logger.info("no tweets retrieved");
        }


        return mav;
    }
}
