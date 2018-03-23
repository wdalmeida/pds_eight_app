package repositories;

import entities.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, UUID> {
}
