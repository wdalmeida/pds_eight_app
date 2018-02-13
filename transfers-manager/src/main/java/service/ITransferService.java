package service;

import dto.TransferDto;
import entity.TransferDetailsEntity;

public interface ITransferService {

    Iterable<TransferDetailsEntity> getAllTransferDetails();

    boolean createTransferAndTransaction(TransferDto transferDto);

}
