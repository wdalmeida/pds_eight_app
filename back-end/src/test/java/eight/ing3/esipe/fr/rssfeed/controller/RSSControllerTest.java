package eight.ing3.esipe.fr.rssfeed.controller;

import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import eight.ing3.esipe.fr.rssfeed.service.RssService;
import eight.ing3.esipe.fr.rssfeed.service.RssServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RSSControllerTest {

    @Mock
    RssService rssService;

    @InjectMocks
    RSSController rssController;

    @Test
    public void getOneNotNull() {
        RSSFeed rss = new RSSFeed("t","t","t");
        int id = 0;
        ResponseEntity<RSSFeed> expected =  new ResponseEntity<>(rss, HttpStatus.OK) ;

        when(rssService.getOne(id)).thenReturn(rss);
        ResponseEntity<RSSFeed> result =rssController.getOne(id);


        verify(rssService,times(1)).getOne(id);
        assertEquals(expected,result);
    }

    @Test
    public void getOneNull(){
        int id = 0;
        ResponseEntity<RSSFeed> expected =  new ResponseEntity<>(HttpStatus.NO_CONTENT) ;

        when(rssService.getOne(id)).thenReturn(null);
        ResponseEntity<RSSFeed> result =rssController.getOne(id);


        verify(rssService,times(1)).getOne(id);
        assertEquals(expected,result);
    }

}