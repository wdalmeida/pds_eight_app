package repository;

import entity.AccounEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccounRepository extends CrudRepository<AccounEntity,String> {
}
