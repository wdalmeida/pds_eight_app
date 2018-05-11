package fr.service;

import java.util.ArrayList;
import java.util.List;

import fr.config.TwitterConfiguration;
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
            query.setCount(15);
            QueryResult result;

            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                    InsertMongoDB insert = new InsertMongoDB();
                    insert.insertIntoDocument(tweet.getText());
                    System.out.println("Inserted into Twitter");
                }
            }
            while ((query = result.nextQuery()) != null);
            /*String user = t.getUser().getScreenName();
            String msg = t.getText();*/
        return tweetList;
    }
}