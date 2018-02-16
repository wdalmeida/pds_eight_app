package eight.ing3.esipe.fr.rssfeed.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import eight.ing3.esipe.fr.rssfeed.utilty.RSSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        logger.debug(rss.toString());
        SyndEntry se = rss.getEntries().get(id);
        logger.debug(se.toString());
        String desc = se.getDescription().getValue();
        if(desc.contains("<br")) {
            desc = desc.substring(0, desc.indexOf("<br"));
            desc= desc.trim();
            logger.debug("Delete Html ");
        }
        RSSFeed rf = new RSSFeed(se.getTitle(),se.getLink(),desc,se.getEnclosures().get(0).getUrl(),se.getPublishedDate().toString());
        return  rf;
    }

    @Override
    public List<RSSFeed> getAllFromBEM() {
        String url="http://10.10.1.201/";
        logger.debug("Service news : ");
        SyndFeed rss = reader.read(url);
        List<SyndEntry> newsList= rss.getEntries();
        logger.debug(newsList.get(0).toString());
        String desc;
        List<RSSFeed> result = new ArrayList<>();
        for(SyndEntry se : newsList){
            desc = se.getDescription().getValue();
            if(desc.contains("<br")) {
                desc = desc.substring(0, desc.indexOf("<br"));
                desc= desc.trim();
                logger.debug("Delete Html ");
            }
            result.add(new RSSFeed(se.getTitle(),se.getLink(),desc,se.getEnclosures().get(0).getUrl(),se.getPublishedDate().toString()));
        }
        return  result;
    }
}
