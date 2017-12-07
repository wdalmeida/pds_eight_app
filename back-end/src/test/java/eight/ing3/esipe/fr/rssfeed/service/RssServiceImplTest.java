package eight.ing3.esipe.fr.rssfeed.service;

import com.rometools.rome.feed.synd.*;
import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import eight.ing3.esipe.fr.rssfeed.utilty.RSSReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RssServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(RssServiceImpl.class);

    @Mock
    private RSSReader rssReader;

    @Mock
    private SyndFeed feedy;

    @Mock
    private SyndEntry entri;

    @Mock
    private List<SyndEntry> entries;

    @InjectMocks
    private RssServiceImpl service;


    @Test
    public void getOne() {
        String title="Title";
        String link="Link :";
        String description="Description";
        String imgUrl = "http://localhost/img.png";

        SyndContent descriptionContent = new SyndContentImpl();
        descriptionContent.setType("text/plain");
        descriptionContent.setValue(description);

        SyndEnclosure enclosure = new SyndEnclosureImpl();
        enclosure.setUrl(imgUrl);
        enclosure.setType("img");

        List<SyndEnclosure> syndEnclosureList = new ArrayList<>();
        syndEnclosureList.add(enclosure);

        entri = new SyndEntryImpl();
        entri.setTitle(title);
        entri.setDescription(descriptionContent);
        entri.setLink(link);
        entri.setEnclosures(syndEnclosureList);
        entries.add(entri);
        RSSFeed expected = new RSSFeed(title,link,description,imgUrl);
        int id = 1;

        //WHEN
        when(feedy.getEntries()).thenReturn(entries);
        when(entries.get(id)).thenReturn(entri);
        when(rssReader.read(anyString())).thenReturn(feedy);
        RSSFeed result = service.getOne(id);

        //THEN
        verify(rssReader,times(1)).read(anyString());
        verify(feedy,times(1)).getEntries();
        verify(entries,times(1)).get(id);
        logger.debug("All the method have been called one time");

        assertEquals(expected.getTitle(),result.getTitle());
        logger.debug("Expected title was "+expected.getTitle()+"| Result is "+ result.getTitle());

        assertEquals(expected.getLink(),result.getLink());
        logger.debug("Expected link was "+expected.getLink()+"| Result is "+ result.getLink());

        assertEquals(expected.getDescription(),result.getDescription());
        logger.debug("Expected desciption was "+expected.getDescription()+"| Result is "+ result.getDescription());

        assertEquals(expected.getImg(),result.getImg());
        logger.debug("Expected image url was "+expected.getImg()+"| Result is "+ result.getImg());

    }
}