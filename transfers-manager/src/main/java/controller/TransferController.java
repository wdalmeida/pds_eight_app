package controller;

import com.sun.net.httpserver.Authenticator;
import dto.TransferDto;
import entity.AccounEntity;
import entity.BeneficiaryAccountEntity;
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
import utils.CollectionUtils;

import java.util.List;

@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBeneficiaryAccountService beneficiaryAccountService;

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ResponseEntity<?> submitTransfer(@RequestBody TransferDto transferDto) {
        logger.info(transferDto.toString());

        //send transfer to backend
        new TransferProducer().sendTransfer(transferDto);

        //save in database
        //TODO

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
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


}

