package eight.ing3.esipe.fr.rssfeed.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;
import eight.ing3.esipe.fr.rssfeed.service.IRssService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RSSController.class)
public class RSSControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IRssService IRssService;

    @Test
    public void getOneNotNull() throws Exception {
        RSSFeed rss = new RSSFeed("t","t","t");
        int id = 1;
        String expectedJson = objectMapper.writeValueAsString(rss);

        //When
        when(IRssService.getOne(id)).thenReturn(rss);


        //Verify
        mockMvc.perform(get("http://localhost/rss/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(expectedJson));

        verify(IRssService,times(1)).getOne(id);
    }

    @Test
    public void getOneWithContentNull() throws Exception {
        int id = 1;

        //When
        when(IRssService.getOne(id)).thenReturn(null);

        //Then
        mockMvc.perform(get("http://localhost/rss/"+id))
                .andExpect(status().isNoContent());
        verify(IRssService,times(1)).getOne(id);
    }

    @Test
    public void getBEMNotNull() throws Exception {
        ArrayList<RSSFeed> rssList = new ArrayList<>();
        for(int i=0;i<10;i++){
            rssList.add(new RSSFeed("title"+i,"link"+i,"description"+i,"imgLink"+i));
        }
        String expectedJson = objectMapper.writeValueAsString(rssList);

        //When
        when(IRssService.getAllFromBEM()).thenReturn(rssList);


        //Verify
        mockMvc.perform(get("http://localhost/rss/bem"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(expectedJson));

        verify(IRssService,times(1)).getAllFromBEM();
    }

    @Test
    public void getBEMWithContentNull() throws Exception {

        //When
        when(IRssService.getAllFromBEM()).thenReturn(null);

        //Then
        mockMvc.perform(get("http://localhost/rss/bem"))
                .andExpect(status().isNoContent());
        verify(IRssService,times(1)).getAllFromBEM();
    }

}