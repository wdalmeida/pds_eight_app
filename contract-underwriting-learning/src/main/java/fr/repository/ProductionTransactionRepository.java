package fr.repository;

import fr.entity.ProductionTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductionTransactionRepository extends CrudRepository<ProductionTransactionEntity,String> {
}
