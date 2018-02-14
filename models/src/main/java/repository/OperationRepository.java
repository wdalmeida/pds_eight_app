package repository;

import entity.OperationEntity;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<OperationEntity,Long> {

}
