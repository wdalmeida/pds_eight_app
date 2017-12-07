package eight.ing3.esipe.fr.rssfeed.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import eight.ing3.esipe.fr.rssfeed.utilty.RSSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for reading RSS Feed
 *
 * @author Warren D'ALMEIDA
 */
@Service
public class RssServiceImpl implements IRssService {

    private static final Logger logger = LoggerFactory.getLogger(RssServiceImpl.class);

    @Autowired
    private RSSReader reader;


    /**
     * Convert the feed to one object
     *
     * @param id 0 is the latest
     * @return RSSFeed Object
     */
    @Override
    public RSSFeed getOne(int id) {
        String url="http://bfmbusiness.bfmtv.com/rss/info/flux-rss/flux-toutes-les-actualites/";
        logger.debug("Service news : " + id);
        SyndFeed rss = reader.read(url);
        SyndEntry se = rss.getEntries().get(id);
        RSSFeed rf = new RSSFeed(se.getTitle(),se.getLink(),se.getDescription().getValue(),se.getEnclosures().get(0).getUrl());
        return  rf;
    }
}
