package repositories;

import entities.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, UUID> {

    @Query("{tweet : {$regex : '.*?0.*'}}")
    List<Tweet> findByDate(String date);

}
