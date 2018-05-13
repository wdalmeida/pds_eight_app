package fr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfiguration {

    @Bean()
    public TwitterFactory configureTwitter() {
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
        return new TwitterFactory(cb.build());
    }
}
