package repository;

import entity.BeneficiaryAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface BeneficiaryAccountRepository extends CrudRepository<BeneficiaryAccountEntity,String> {
}
