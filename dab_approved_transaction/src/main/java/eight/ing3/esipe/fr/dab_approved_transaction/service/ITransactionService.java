package eight.ing3.esipe.fr.dab_approved_transaction.service;

import dto.TransactionDto;

public interface ITransactionService {
    TransactionDto changeTransactionStatus(TransactionDto transaction);
    Boolean sendApprouvedTransactionToQueue(TransactionDto transaction);
}
