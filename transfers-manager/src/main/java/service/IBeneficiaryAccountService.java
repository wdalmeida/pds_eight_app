package service;

import entity.BeneficiaryAccountEntity;

public interface IBeneficiaryAccountService {

    Iterable<BeneficiaryAccountEntity> getAllBeneficiaryAccounts();
}
