package service;

import dto.TransferDto;
import entity.TransferDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;

public interface ITransferService {

    Iterable<TransferDetailsEntity> getAllTransferDetails();

    boolean createTransferAndTransaction(TransferDto transferDto);

}
