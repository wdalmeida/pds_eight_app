package service;

import entities.Tweet;

import java.util.List;

public interface ITweetService {

    List<Tweet> getAllTweetsByDate(String date);
}
