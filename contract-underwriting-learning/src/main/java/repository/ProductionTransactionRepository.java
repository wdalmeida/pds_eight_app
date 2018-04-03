package repository;

import entity.ProductionTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductionTransactionRepository extends CrudRepository<ProductionTransactionEntity,String> {
}
