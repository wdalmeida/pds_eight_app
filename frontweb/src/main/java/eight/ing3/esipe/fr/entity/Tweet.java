package eight.ing3.esipe.fr.entity;

public class Tweet {

    private String uuid;

    private String tweet;

    public Tweet(){

    }

    public Tweet(String uuid, String tweet) {
        this.uuid = uuid;
        this.tweet = tweet;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

}
