package controller;

import com.sun.net.httpserver.Authenticator;
import dto.TransferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;
import producer.TransferProducer;

@Controller
public class TransferController {

    private static Logger logger = Logger.getLogger(TransferController.class);

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ResponseEntity<?> submitTransfer(@RequestBody TransferDto transferDto) {
        logger.info(transferDto.toString());
        //send to back
        //new TransferProducer().sendTransfer(transferModel);
        //save in database
        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }
}

