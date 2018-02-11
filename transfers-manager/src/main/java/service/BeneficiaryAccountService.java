package service;

import entity.BeneficiaryAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BeneficiaryAccountRepository;

@Service
public class BeneficiaryAccountService implements IBeneficiaryAccountService {

    @Autowired
    private BeneficiaryAccountRepository beneficiaryAccountRepository;

    @Override
    public Iterable<BeneficiaryAccountEntity> getAllBeneficiaryAccounts() {
        return beneficiaryAccountRepository.findAll();
    }
}
