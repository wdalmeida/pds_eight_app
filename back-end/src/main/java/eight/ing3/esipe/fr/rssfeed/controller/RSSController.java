package eight.ing3.esipe.fr.rssfeed.controller;


import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import eight.ing3.esipe.fr.rssfeed.service.IRssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller to access RSS Feed
 *
 * @author Warren D'ALMEIDA
 */
@RestController
public class RSSController {

    private static final Logger logger = LoggerFactory.getLogger(RSSController.class);

    @Autowired
    private IRssService IRssService;

    /**
     * Method to get a news on the BFM feed
     *
     * @param id Latest is 0
     * @return RSSFeed in JSON with HTTP status 200 or only HTTP status 204
     */
    @GetMapping("/rss/{id}")
    public  ResponseEntity<RSSFeed> getOne(@PathVariable int id) throws IOException {
        logger.debug("Get news : " + id);
        RSSFeed rss = IRssService.getOne(id);
        return (rss!=null) ?
                new ResponseEntity<>(rss, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * Method to get all news from BEM
     *
     * @return List of RSSFeed in JSON with HTTP status 200 or only HTTP status 204
     */
    @GetMapping("/rss/bem")
    public  ResponseEntity<List<RSSFeed>> getAllFromBEM() throws IOException {
        logger.debug("Get news from BEM ");
        List<RSSFeed> allNews = IRssService.getAllFromBEM();
        return (allNews!=null) ?
                new ResponseEntity<>(allNews, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
