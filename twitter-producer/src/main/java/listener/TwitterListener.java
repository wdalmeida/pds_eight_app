package listener;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;

@Component
public class TwitterListener {

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

        twitterStream.addListener(new StatusListener() {
            @Override
            public void onException(Exception e) {

            }

            public void onStatus(Status status) {
                System.out.println(status.getText());
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

        FilterQuery tweetFilterQuery = new FilterQuery(); // See
        tweetFilterQuery.track(new String[]{"banque", "transaction"}); // OR on keywords
        tweetFilterQuery.language(new String[]{"fr"}); // Note that language does not work properly on Norwegian tweets
        twitterStream.filter(tweetFilterQuery);
    }

}
