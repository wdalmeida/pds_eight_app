package controller;

import com.sun.net.httpserver.Authenticator;
import dto.TransferDto;
import entity.AccounEntity;
import entity.BeneficiaryAccountEntity;
import entity.TransferDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;
import producer.TransferProducer;
import service.IAccountService;
import service.IBeneficiaryAccountService;
import service.ITransferService;
import utils.CollectionUtils;

import java.util.List;

@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ITransferService transferService;

    @Autowired
    private IBeneficiaryAccountService beneficiaryAccountService;

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ResponseEntity<?> submitTransfer(@RequestBody TransferDto transferDto) {
        logger.info("transfer to save" + transferDto.toString());

        //save in database
        boolean saved = transferService.createTransferAndTransaction(transferDto);

        logger.info("transfer not saved");
        if (saved) {
            //send transfer to backend
            //new TransferProducer().sendTransfer(transferDto);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value="/accounts", method={RequestMethod.GET})
    public ResponseEntity<?> getAccounts(){
        Iterable<AccounEntity> accountIterable = accountService.getAllAccount();
        List<AccounEntity> accountList = CollectionUtils.makeCollection(accountIterable);
        return (!accountList.isEmpty()) ?
                new ResponseEntity<List<AccounEntity>>(accountList, HttpStatus.OK) : new ResponseEntity<List<AccounEntity>>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/beneficiaryaccounts", method={RequestMethod.GET})
    public ResponseEntity<?> getBeneficiaryAccounts(){
        Iterable<BeneficiaryAccountEntity> accountIterable = beneficiaryAccountService.getAllBeneficiaryAccounts();
        List<BeneficiaryAccountEntity> accountList = CollectionUtils.makeCollection(accountIterable);
        return (!accountList.isEmpty()) ?
                new ResponseEntity<List<BeneficiaryAccountEntity>>(accountList, HttpStatus.OK) : new ResponseEntity<List<BeneficiaryAccountEntity>>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/transfersdetails", method={RequestMethod.GET})
    public ResponseEntity<?> getTransfersDetails(){
        Iterable<TransferDetailsEntity> transferDetailsIterable = transferService.getAllTransferDetails();
        List<TransferDetailsEntity> transferDetailsList = CollectionUtils.makeCollection(transferDetailsIterable);
        return (!transferDetailsList.isEmpty()) ?
                new ResponseEntity<List<TransferDetailsEntity>>(transferDetailsList, HttpStatus.OK) : new ResponseEntity<List<TransferDetailsEntity>>(HttpStatus.NO_CONTENT);

    }
}

