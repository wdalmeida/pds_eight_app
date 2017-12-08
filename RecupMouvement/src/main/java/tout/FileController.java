package tout;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.*;
import service.ExternMouvService;

@Configuration
@EnableKafka
@RestController
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/file", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FileResponse request(@RequestBody FileRequest request)  throws JsonProcessingException{
        final FileResponse response = new FileResponse();
        LOGGER.info("UUID = {}", response.getUuid().toString());
           ExternMouvService externMouvService=new ExternMouvService();
        externMouvService.save(request, response);
        return response;
    }
/*
    @RequestMapping(value = "/file", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FileResponse request(@RequestBody FileRequest request)  throws JsonProcessingException{
        final FileResponse response = new FileResponse();
        LOGGER.info("UUID = {}", response.getUuid().toString());
     //   ExternMouvService.save(request, response);
        return response;
    }*/

}
