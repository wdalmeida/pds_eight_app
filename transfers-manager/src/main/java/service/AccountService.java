package service;

import entity.AccounEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccounRepository;

@Service
public class AccountService implements IAccountService {

    private AccounRepository accountRepository;

    @Override
    public Iterable<AccounEntity> getAllAccount() {
        return accountRepository.findAll();
    }

}
