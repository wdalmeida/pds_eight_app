package eight.ing3.esipe.fr.dab_approved_transaction.controller;


import dto.TransactionDto;
import eight.ing3.esipe.fr.dab_approved_transaction.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to access RSS Feed
 *
 * @author Warren D'ALMEIDA
 */
@RestController
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ITransactionService service;


    @PostMapping("/transaction/approuved")
    public  ResponseEntity transactionToApprouved(@RequestBody TransactionDto transaction) {
        logger.debug("Controller - Transaction to be approuved");
        Boolean sent = service.sendApprouvedTransactionToQueue(transaction);
        return (!sent) ?
                new ResponseEntity<>(sent, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
