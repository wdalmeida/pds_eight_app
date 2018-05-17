package eight.ing3.esipe.fr.dab_approved_transaction.service;

import dto.TransactionDto;
import eight.ing3.esipe.fr.dab_approved_transaction.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for reading RSS Feed
 *
 * @author Warren D'ALMEIDA
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Override
    public TransactionDto changeTransactionStatus(TransactionDto transaction) {
        //read queue
        //check if transaction exists
        //  update database
        return null;
    }

    @Override
    public Boolean sendApprouvedTransactionToQueue(TransactionDto transaction) {
        // send to queue
        return null;
    }
}
