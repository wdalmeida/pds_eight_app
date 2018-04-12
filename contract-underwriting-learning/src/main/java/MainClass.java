import service.IntegrateTwitterData;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Iterator;
public class MainClass {

    public static void main(String args[])
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled (false)
                .setOAuthConsumerKey ("n1PhG625Vcl0C3SHw6lxuv6kf")
                .setOAuthConsumerSecret ("cAyKfxNb9nDdwwMlsNiZpJEtlVYf7TseKlEw2mxWUI1XHNRIUI")
                .setOAuthAccessToken ("780564452785557504-NtXA8Czm7voA5104GjHgSm5qCREkPF1\n")
                .setOAuthAccessTokenSecret ("tNAXGjmG82xV3CSizF2U8ut641vSNlLeIe7iqg8Yp2ehu");
        IntegrateTwitterData obTweetManager = new IntegrateTwitterData();
        ArrayList<String> objArrayResult = obTweetManager.getTweets("world");
        Iterator<String> iterator = objArrayResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(">>>>>>>>>>>>"+iterator.next());
        }
    }
}