package tout;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.modelmbean.XMLParseException;
import javax.swing.text.Document;
import java.io.File;


@RestController
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);


    @RequestMapping(value = "/file", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String response () throws XMLParseException{

        return "ok";
    }
}
