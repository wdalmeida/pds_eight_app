package service;

import dto.TransferDto;
import entity.AccounEntity;
import entity.TransactionEntity;
import entity.TransferDetailsEntity;
import entity.TransferEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccounRepository;
import repository.TransactionRepository;
import repository.TransferDetailsRepository;
import repository.TransferRepository;

import java.time.LocalTime;

import static java.time.LocalDate.now;

@Service
public class TransferService implements ITransferService{

    private static Logger logger = Logger.getLogger(TransferService.class);

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccounRepository accounRepository;

    @Autowired
    private TransferDetailsRepository transferDetailsRepository;

    @Override
    public Iterable<TransferDetailsEntity> getAllTransferDetails() {
        return transferDetailsRepository.findAll();
    }

    @Override
    public boolean createTransferAndTransaction(TransferDto transferDto) {

        AccounEntity accounEntity = accounRepository.findOne(transferDto.getSendingIBAN());

        if (accounEntity.getBalance() >= transferDto.getAmount()) {

            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setAmount(transferDto.getAmount());
            transactionEntity.setWording(transferDto.getWording());
            transactionEntity.setValueDate(transferDto.getValueDate());
            transactionEntity.setRead(false);
            transactionEntity.setAccountEntity(accounEntity);

            TransferEntity transferEntity = new TransferEntity();
            transferEntity.setBeneficiaryIban(transferDto.getBeneficiaryIban());
            transferEntity.setTransactionEntity(transactionEntity);

            TransferDetailsEntity transferDetailsEntity = new TransferDetailsEntity();
            transferDetailsEntity.setTransferEntity(transferEntity);
            transferDetailsEntity.setNewbalanceorig(accounEntity.getBalance() - transferDto.getAmount());
            transferDetailsEntity.setOldBalanceOrg(accounEntity.getBalance());
            //get hour in month
            transferDetailsEntity.setStep(LocalTime.now().getHour() + (24 * (now().getDayOfMonth()-1)));

            transactionRepository.save(transactionEntity);
            logger.info("transaction saved");

            transferRepository.save(transferEntity);
            logger.info("transfer saved");

            accounEntity.setBalance(accounEntity.getBalance() - transferDto.getAmount());
            accounRepository.save(accounEntity);
            logger.info("balance of the account is reduced of " + transferDto.getAmount() +
                            " (closed balance : " + accounEntity.getBalance() + ")");

            transferDetailsRepository.save(transferDetailsEntity);
            logger.info("details of transfer saved");

            return true;
        } else {

            logger.info("not enough balance on the account to realize the transfer");
            return false;
        }

    }




}
