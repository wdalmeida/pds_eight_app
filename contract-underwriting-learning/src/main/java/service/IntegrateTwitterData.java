package service;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class IntegrateTwitterData {

    public static ArrayList<String> getTweets(String topic) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        String CONSUMER_KEY = "Y1ZidpFdSPwubI9R5aE77pFDo";
        String CONSUMER_SECRET = "iVTKgL01THoivgX7itdm3pSqCyYPMViHyHc9OXVWdZIzilcWYY";
        String ACCESS_KEY = "780564452785557504-V3xBQAn1qrC1jVXbR3jQ0TMnF7mmVUa";
        String ACCESS_SECRET = "8O4HWJ636EsIst8m992ymlYba6T2NKRu5Xx5PInkiU04V";
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_KEY)
                .setOAuthAccessTokenSecret(ACCESS_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        ArrayList<String> tweetList = new ArrayList<String>();
        try {
            Query query = new Query(topic);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets:" + te.getMessage());
        }
        return tweetList;
    }
}