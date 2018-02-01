package service;

import dto.TransferDto;

public interface ITransferService {

    boolean createTransferAndTransaction(TransferDto transferDto);

}
