package eight.ing3.esipe.fr.controller;

import dto.TransferDto;
import entity.AccounEntity;
import entity.BeneficiaryAccountEntity;
import model.TransferModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
            mav.addObject("accountsMessage","Aucun compte");
        }

        //recover beneficiary accounts
        ResponseEntity<List> beneficiaryAccountsResponseEntity = restTemplate.getForEntity(transferManagerBeneficiaryAccountUrl, List.class);
        if (beneficiaryAccountsResponseEntity.getStatusCode() == HttpStatus.OK) {
            List<BeneficiaryAccountEntity> beneficiaryAccounts = beneficiaryAccountsResponseEntity.getBody();
            mav.addObject("beneficiaryAccounts",beneficiaryAccounts);
            logger.info("beneficiary accounts : " + beneficiaryAccounts.toString());
        } else {
            mav.addObject("beneficiaryAccountsMessage","Aucun compte bénéficiare");
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

        if (response.getStatusCode() == HttpStatus.OK) {
            mav.addObject("transferModel", transferModel);
        }

        logger.info("Transfer result page displayed");
        return mav;

    }
}

