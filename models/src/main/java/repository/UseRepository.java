package repository;

import entity.UseEntity;
import org.springframework.data.repository.CrudRepository;

public interface UseRepository extends CrudRepository<UseEntity,String> {
}
