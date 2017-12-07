package eight.ing3.esipe.fr.rssfeed.controller;

import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RSSControllerIT {


   @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOneTest() throws Exception {
        ResponseEntity<RSSFeed> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/rss/1",RSSFeed.class);
        RSSFeed rssFeed = responseEntity.getBody();
        assertNotNull(rssFeed.getTitle());
        assertNotNull(rssFeed.getDescription());
        assertNotNull(rssFeed.getLink());
    }
}

