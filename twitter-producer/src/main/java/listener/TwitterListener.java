package listener;


import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import producer.TwitterProducer;
import twitter4j.*;
import twitter4j.Logger;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;

@Component
public class TwitterListener {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TwitterListener.class);

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @PostConstruct
    public void init() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
        TwitterProducer twitterProducer = new TwitterProducer();
        twitterProducer.init();
        twitterStream.addListener(new StatusListener() {
            @Override
            public void onException(Exception e) {

            }

            public void onStatus(Status status) {
                twitterProducer.sendTwitterMessage(status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {

            }

            @Override
            public void onScrubGeo(long l, long l1) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }
        });

        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track("banque", "transaction", "transfert", "carte bancaire");
        tweetFilterQuery.language("fr");
        twitterStream.filter(tweetFilterQuery);
    }

}
