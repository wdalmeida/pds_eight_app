package repositories;

import entities.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, UUID> {

    @Query("{transfer : {$regex : '.*?0.*'}}")
    List<Transfer> findByDate(String date);

}
