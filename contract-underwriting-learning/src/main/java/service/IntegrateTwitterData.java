package service;

import java.util.ArrayList;
import java.util.List;

import config.TwitterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class IntegrateTwitterData {

    @Autowired
    static
    ConfigurationBuilder cb = TwitterConfiguration.configureTwitter();

    public static ArrayList<String> getTweets(String topic) throws TwitterException {


        TwitterFactory tf = new TwitterFactory(cb.build());
        System.out.println("Connected to Twitter");
        Twitter twitter = tf.getInstance();

        ArrayList<String> tweetList = new ArrayList<String>();

            Query query = new Query(topic);
            query.setCount(50);
            QueryResult result;

    /*
            twitter.search(query)
                    .getTweets()
                    .stream()
                    .map(s -> s.getText())
                    .forEach(s -> tweetList.add(s));
            */

            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);

        return tweetList;
    }
}