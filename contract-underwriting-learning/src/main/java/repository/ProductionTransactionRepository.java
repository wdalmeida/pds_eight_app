package repository;

import dto.ProductionTransactionDto;
import entity.ProductionTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductionTransactionRepository extends CrudRepository<ProductionTransactionDto,String> {
}
