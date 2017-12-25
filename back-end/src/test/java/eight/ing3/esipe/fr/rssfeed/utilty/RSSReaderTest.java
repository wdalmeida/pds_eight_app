package eight.ing3.esipe.fr.rssfeed.utilty;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RSSReader.class)
public class RSSReaderTest {

    private static final Logger logger = Logger.getLogger(RSSReaderTest.class);

    @Mock
    private SyndFeed feed;

    @Mock
    private XmlReader xmlReader;

    @Mock
    private URL url;

    @InjectMocks
    private RSSReader rssReader;

    private static String filename="test.txt";
    private static String urlString="http://localhost/";



    @Before
    public void setUp() throws ParseException, IOException, FeedException {
        url = new URL(urlString);

        feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
    }

    @Test
    public void readShouldWorks() throws Exception {
        DateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd");
        List<SyndEntry> entries = new ArrayList();
        SyndEntry entry;
        SyndContent description;
        feed.setTitle("Sample Feed (created with ROME)");
        feed.setLink("http://rome.dev.java.net");
        feed.setDescription("This feed has been created using ROME (Java syndication utilities");
        entry = new SyndEntryImpl();
        entry.setTitle("ROME v1.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01");
        entry.setPublishedDate(DATE_PARSER.parse("2004-06-08"));
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue("Initial release of ROME");
        entry.setDescription(description);
        entries.add(entry);
        feed.setEntries(entries);
        Writer writer = new FileWriter(filename);
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed,writer);
        writer.flush();
        writer.close();

        Path file = Paths.get(filename);

        whenNew(URL.class).withArguments(urlString).thenReturn(url);
        xmlReader= new XmlReader(file.toFile());
        whenNew(XmlReader.class).withArguments(url).thenReturn(xmlReader);
        SyndFeed result = rssReader.read(urlString);

        verifyNew(xmlReader.getClass());
        assertEquals(feed.getPublishedDate(),result.getPublishedDate());
        logger.debug("Expected date was "+feed.getEntries().get(0).getPublishedDate().toString()+"| Result is "+ result.getEntries().get(0).getPublishedDate().toString());

    }

    @Test(expected = IOException.class)
    public void readShouldThrowsIOException() throws Exception {
        url=new URL("");
        Path file = Paths.get(filename);
        logger.debug("File shouldn't exist (FALSE) " +file.toFile().exists());
        whenNew(URL.class).withArguments(urlString).thenReturn(url);
        xmlReader= new XmlReader(file.toFile());
        whenNew(XmlReader.class).withArguments(url).thenReturn(xmlReader);
        rssReader.read(urlString);

    }

    @Test(expected = FeedException.class)
    public void readThrowsFeedException() throws Exception {

        Writer writer = new FileWriter(filename);
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed,writer);
        writer.flush();
        writer.close();

        Path file = Paths.get(filename);

        whenNew(URL.class).withArguments(urlString).thenReturn(url);
        xmlReader= new XmlReader(file.toFile());
        whenNew(XmlReader.class).withArguments(url).thenReturn(xmlReader);
        rssReader.read(urlString);
    }

    @After
    public void clear(){
        File test = new File("test.txt");
        logger.debug("File path ==>"+test.getAbsolutePath());
        if(test.exists()){
            test.setWritable(true,false);
            test.delete();
            test.deleteOnExit();
            logger.debug("File existed and has been deleted");
        }
        else {
            logger.debug("File doesn't exist");
        }
    }
}