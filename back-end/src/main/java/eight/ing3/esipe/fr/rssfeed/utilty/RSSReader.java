package eight.ing3.esipe.fr.rssfeed.utilty;


import java.io.IOException;
import java.net.URL;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.log4j.Logger;


/**
 * @author Warren D'ALMEIDA
 *
 */
public class RSSReader {

    private static final Logger logger = Logger.getLogger(RSSReader.class);

    /**
     * Method which read the RSS Feed
     *
     * @param url URL corresponding to the feed to read
     * @return SyndFeed
     */
    public static SyndFeed read(String url){
        SyndFeed feed =null;
        logger.debug("What is in url ? ==>"+url);
        try {
            URL feedUrl = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            feed= input.build(new XmlReader(feedUrl));
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }

        logger.debug("What is in feed ? ==>"+feed);

        return feed;
    }

}