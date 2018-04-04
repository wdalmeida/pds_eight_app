import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TestMainClass {
    public static void main(String [] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled (true)
                .setOAuthConsumerKey ("Y1ZidpFdSPwubI9R5aE77pFDo")
                .setOAuthConsumerSecret ("iVTKgL01THoivgX7itdm3pSqCyYPMViHyHc9OXVWdZIzilcWYY")
                .setOAuthAccessToken ("780564452785557504-V3xBQAn1qrC1jVXbR3jQ0TMnF7mmVUa")
                .setOAuthAccessTokenSecret ("8O4HWJ636EsIst8m992ymlYba6T2NKRu5Xx5PInkiU04V");

        TwitterFactory tf = new TwitterFactory (cb.build ());
        Twitter twitter = tf.getInstance ();

        Query query = new Query("avantis_bc");
        QueryResult result = twitter.search(query);

        do{

            List<Status> tweets = result.getTweets();

            for(Status tweet: tweets){
                System.out.println("Usuário: "+tweet.getUser().getScreenName() + " Tweet: "+tweet.getText());
            }

            query = result.nextQuery();

            if(query!=null)
                result = twitter.search(query);
        }while(query!=null);
    } }