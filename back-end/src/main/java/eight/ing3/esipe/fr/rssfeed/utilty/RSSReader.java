package eight.ing3.esipe.fr.rssfeed.utilty;


import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;


/**
 * @author Warren D'ALMEIDA
 *
 */
@Component
public class RSSReader {

    private static final Logger logger = Logger.getLogger(RSSReader.class);

    /**
     * Method which read the RSS Feed
     *
     * @param url URL corresponding to the feed to read
     * @return SyndFeed
     */
    public SyndFeed read(String url){
        SyndFeed feed =null;
        logger.debug("What is in url ? ==>"+url);
        try {
            URL feedUrl = new URL(url);
            logger.debug("JAVA.URL = " + feedUrl.toString());
            SyndFeedInput input = new SyndFeedInput();
            XmlReader xml = new XmlReader(feedUrl);
            feed = input.build(xml);
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
        return feed;
    }

}