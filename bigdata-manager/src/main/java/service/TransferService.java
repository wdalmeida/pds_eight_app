package service;

import entities.Transfer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import repositories.TransferRepository;

import java.util.List;

@Service
public class TransferService implements ITransferService {

    private static Logger logger = Logger.getLogger(TransferService.class);

    @Autowired
    private TransferRepository transferRepository;

    public List<Transfer> getAllTransfersByDate(String date) {
        List<Transfer> transfers = transferRepository.findByDate(date);
        return transfers;
    }
}
