package fr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.config.TwitterConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class IntegrateTwitterData {

    @Autowired
    private TwitterFactory tf;

    @Autowired
    private InsertMongoDB insertMongoDB;

    private static final Logger logger = Logger.getLogger(IntegrateTwitterData.class);

    @Scheduled(fixedRate = 1000L * 60 + 300L )
    public ArrayList<String> getTweets() throws TwitterException {
        logger.info("start looking for tweets");
        Stream<String> stream = Stream.of("banque", "investissement", "épargne", "bank", "banque échange mutualiste");
        String topic = stream.findAny().get();

        System.out.println("Connected to Twitter");
        Twitter twitter = tf.getInstance();
        logger.info("connection to twitter done");
        ArrayList<String> tweetList = new ArrayList<String>();

        Query query = new Query(topic);
        query.setCount(1000);
        QueryResult result;
        int i = 0;
        // https://www.youtube.com/watch?v=NdIILGsm_ak&list=PLnbTrWotkRTSizmuRnOi_v1-yPzneQ0AO
        do {
            i ++;
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            //tweets.stream().map(s -> s.getText()).peek(s -> tweetList.add(s)).forEach(s -> insertMongoDB.insertIntoDocument(s));
            for (Status tweet : tweets) {
                tweetList.add(tweet.getText());
                insertMongoDB.insertIntoDocument(tweet.getText());
            }
            logger.info("end of looking for tweets");
        }
        while ((query = result.nextQuery()) != null && i < 10);

        logger.info("task finished");
        return tweetList;
    }
}