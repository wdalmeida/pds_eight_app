package eight.ing3.esipe.fr.controller;

import dto.TransferDto;
import entity.AccounEntity;
import model.TransferModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/transfers")
@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @Value("${transfers.manager.submit.url}")
    private String transferManagerSubmitUrl;

    @Value("${transfers.manager.account.url}")
    private String transferManagerAccountUrl;

    @RequestMapping(value="/create", method={RequestMethod.GET})
    public ModelAndView getSendingAccountSelectionForm() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.getForEntity(transferManagerAccountUrl, AccounEntity.class);
        logger.info(responseEntity.toString());
        return null;
    }

    /*@RequestMapping(value="/create", method={RequestMethod.GET})
    public ModelAndView getBeneficiaryAccountSelectionForm() {
        return null;
    }*/

    @RequestMapping(value="/submit", method={RequestMethod.GET})
    public ModelAndView getTransferForm() {
        ModelAndView mav = new ModelAndView("createTransferForm");
        mav.addObject("transferModel", new TransferModel());
        logger.info("Transfer form displayed...");
        return mav;
    }

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ModelAndView submitTransfer(@ModelAttribute("transferModel") TransferModel transferModel, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("response");

        logger.info("transfer filled : " + transferModel.toString());

        TransferDto transferDto = new TransferDto();
        transferDto.setBeneficiaryIban(transferModel.getBeneficiaryIban());
        transferDto.setValueDate(transferModel.getValueDate());
        transferDto.setWording(transferModel.getWording());
        transferDto.setSendingIBAN(transferModel.getSendingIBAN());
        transferDto.setAmount(transferModel.getAmount());

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TransferDto> request = new HttpEntity<>(transferDto);
        ResponseEntity<?> response = restTemplate.postForEntity(transferManagerSubmitUrl, request, TransferDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            mav.addObject("message", "OK");
        } else {
            mav.addObject("message", "KO");
        }
        return mav;

    }
}

