package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tout.ExternMouvInter;
import tout.FileRequest;
import tout.FileResponse;

@Service
public class ExternMouvService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    private static final Logger LOGGER = LoggerFactory.getLogger(ExternMouvService.class);
    public void save(FileRequest request,
                     final FileResponse response) throws JsonProcessingException {

        LOGGER.info("sending request='{}' to response='{}'", request.getPath(), response.getUuid());
        System.out.println(">> " + request.getPath());
        kafkaTemplate.send(request.toString(), response.toString());

    }
    }


