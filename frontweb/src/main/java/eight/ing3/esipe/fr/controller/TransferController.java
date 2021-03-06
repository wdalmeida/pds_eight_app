package eight.ing3.esipe.fr.controller;

import dto.TransferDto;
import entity.AccounEntity;
import entity.BeneficiaryAccountEntity;
import entity.TransferDetailsEntity;
import model.TransferModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequestMapping("/transfers")
@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @Value("${transfers.manager.submit.url}")
    private String transferManagerSubmitUrl;

    @Value("${transfers.manager.account.url}")
    private String transferManagerAccountUrl;

    @Value("${transfers.manager.beneficiaryaccount.url}")
    private String transferManagerBeneficiaryAccountUrl;

    @Value("${transfers.manager.transfersdetails.url}")
    private String transferManagerTransferdetailsUrl;

    @Value("${transfers.manager.fraudulenttransfer.url}")
    private String transferManagerFraudulentTransferUrl;

    @RequestMapping(value="/create", method={RequestMethod.GET})
    public ModelAndView getSendingAccountSelectionForm() {
        ModelAndView mav = new ModelAndView("createTransferForm");
        RestTemplate restTemplate = new RestTemplate();

        //recover accounts
        ResponseEntity<List> accountsResponseEntity = restTemplate.getForEntity(transferManagerAccountUrl, List.class);
        if (accountsResponseEntity.getStatusCode() == HttpStatus.OK) {
            List<AccounEntity> accounts = accountsResponseEntity.getBody();
            mav.addObject("accounts",accounts);
            logger.info("sending accounts : " + accounts.toString());
        } else {
            logger.info("no sending accounts retrieved");
        }

        //recover beneficiary accounts
        ResponseEntity<List> beneficiaryAccountsResponseEntity = restTemplate.getForEntity(transferManagerBeneficiaryAccountUrl, List.class);
        if (beneficiaryAccountsResponseEntity.getStatusCode() == HttpStatus.OK) {
            List<BeneficiaryAccountEntity> beneficiaryAccounts = beneficiaryAccountsResponseEntity.getBody();
            mav.addObject("beneficiaryAccounts",beneficiaryAccounts);
            logger.info("beneficiary accounts : " + beneficiaryAccounts.toString());
        } else {
            logger.info("no beneficiary accounts retrieved");
        }

        mav.addObject("transferModel", new TransferModel());
        logger.info("Transfer form displayed...");

        return mav;
    }

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ModelAndView submitTransfer(@ModelAttribute("transferModel") TransferModel transferModel, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("transferResponse");

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
        logger.info("response status : " + response.getStatusCode());
        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("transfer saved and well submitted");
            mav.addObject("transferModel", transferModel);
        } else {
            mav.addObject("transferModel", null);
            logger.info("transfer not saved");
        }

        logger.info("Transfer result page displayed");
        return mav;

    }

    @RequestMapping(value="/fraudulent", method={RequestMethod.GET})
    public ModelAndView getFraudulentTransfersForm() {
        ModelAndView mav = new ModelAndView("fraudulentTransfers");
        RestTemplate restTemplate = new RestTemplate();

        //recover transfer details
        ResponseEntity<List> transfersResponseEntity = restTemplate.getForEntity(transferManagerTransferdetailsUrl, List.class);
        if (transfersResponseEntity.getStatusCode() == HttpStatus.OK) {
            List<TransferDetailsEntity> transfers = transfersResponseEntity.getBody();
            mav.addObject("transfers",transfers);
            logger.info("transfers : " + transfers.toString());
        } else {
            logger.info("no transfers retrieved");
        }
        mav.addObject("transferDetailsEntity", new TransferDetailsEntity());
        return mav;
    }

    @RequestMapping(value="/fraudulent/{id}", method={RequestMethod.GET})
    public ModelAndView getFraudulentTransfersForm(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        ModelAndView mav = new ModelAndView("fraudulentResponse");
        HttpEntity<Integer> request = new HttpEntity<>(new Integer(id));
        ResponseEntity<Map> response = restTemplate.postForEntity(transferManagerFraudulentTransferUrl,request,Map.class);
        logger.info("fraudulent computation response : " + response.toString());
        if (response.getStatusCode() == HttpStatus.OK) {
            Map responseBody = response.getBody();
            mav.addObject("percent", responseBody.get("percent"));
            mav.addObject("transfer", responseBody.get("transfer"));
        } else {
            mav.addObject("percent", null);
        }

        return mav;
    }
}

