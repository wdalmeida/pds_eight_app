import service.IntegrateTwitterData;

import java.util.ArrayList;
import java.util.Iterator;
public class MainClass {

    public static void main(String args[])
    {
        IntegrateTwitterData obTweetManager = new IntegrateTwitterData();
        ArrayList<String> objArrayResult = obTweetManager.getTweets("MongolBichig");
        Iterator<String> iterator = objArrayResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(">>>>>>>>>>>>"+iterator.next());
        }
    }
}