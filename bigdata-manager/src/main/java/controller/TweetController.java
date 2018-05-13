package controller;

import entities.Tweet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ITweetService;

import java.util.List;

@Controller
public class TweetController {

    private static Logger logger = Logger.getLogger(TweetController.class);

    @Autowired
    private ITweetService tweetService;


    @RequestMapping(value="/tweets/{date}", method={RequestMethod.GET})
    public ResponseEntity<?> getTweets(@PathVariable("date") String date){
        List<Tweet> tweets = tweetService.getAllTweetsByDate(date);
        return (!tweets.isEmpty()) ?
                new ResponseEntity<List<Tweet>>(tweets, HttpStatus.OK) : new ResponseEntity<List<Tweet>>(HttpStatus.NO_CONTENT);
    }
}
