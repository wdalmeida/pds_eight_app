package service;

import entities.Transfer;

import java.util.List;

public interface ITransferService {

    List<Transfer> getAllTransfersByDate(String date);
}
