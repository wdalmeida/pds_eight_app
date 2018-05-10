package service;

import entities.Tweet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TweetRepository;

import java.util.List;

@Service
public class TweetService implements ITweetService {

    private static Logger logger = Logger.getLogger(TweetService.class);

    @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> getAllTweetsByDate(String date) {
        List<Tweet> tweets = tweetRepository.findByDate(date);
        return tweets;
    }
}
