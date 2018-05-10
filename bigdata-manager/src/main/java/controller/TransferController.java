package controller;

import entities.Transfer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ITransferService;

import java.util.List;

@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @Autowired
    private ITransferService transferService;

    @RequestMapping(value="/transfers/{date}", method={RequestMethod.GET})
    public ResponseEntity<?> getTransfers(@PathVariable("date") String date){
        List<Transfer> transfers = transferService.getAllTransfersByDate(date);
        return (!transfers.isEmpty()) ?
                new ResponseEntity<List<Transfer>>(transfers, HttpStatus.OK) : new ResponseEntity<List<Transfer>>(HttpStatus.NO_CONTENT);
    }
}
