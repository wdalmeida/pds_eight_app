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
import java.util.Date;
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
    public void getOneWithoutHTML() {
        String title="Title";
        String link="Link :";
        String description="Description";
        String imgUrl = "http://localhost/img.png";
        Date RssDate= new Date();


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
        entri.setPublishedDate(RssDate);

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
        logger.debug("Expected title was "+expected.getTitle()+" | Result is "+ result.getTitle());

        assertEquals(expected.getLink(),result.getLink());
        logger.debug("Expected link was "+expected.getLink()+" | Result is "+ result.getLink());

        assertEquals(expected.getDescription(),result.getDescription());
        logger.debug("Expected description was "+expected.getDescription()+" | Result is "+ result.getDescription());

        assertEquals(expected.getImgLink(),result.getImgLink());
        logger.debug("Expected image url was "+expected.getImgLink()+" | Result is "+ result.getImgLink());

    }

    @Test
    public void getOneWithHTML() {
        String title="Title";
        String link="Link :";
        String description="Description with    <br <img .../>";
        String imgUrl = "http://localhost/img.png";
        String expectDesc ="Description with";
        Date RssDate= new Date();

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
        entri.setPublishedDate(RssDate);

        entries.add(entri);
        RSSFeed expected = new RSSFeed(title,link,description,imgUrl,RssDate.toString());

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

        assertEquals(expectDesc,result.getDescription());
        logger.debug("Expected description was "+expectDesc+"| Result is "+ result.getDescription());

        assertEquals(expected.getImgLink(),result.getImgLink());
        logger.debug("Expected image url was "+expected.getImgLink()+"| Result is "+ result.getImgLink());

        assertEquals(expected.getPublishedDate(),result.getPublishedDate());
        logger.debug("Expected image url was "+expected.getPublishedDate()+"| Result is "+ result.getPublishedDate());
    }
}