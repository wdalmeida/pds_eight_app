package entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Document(collection = "file_xml")
public class ExternMouv {

    private String originPath;
    private String targetPath;

    public ExternMouv() {
    }

    public ExternMouv( String originPath, String targetPath) {

        this.originPath = originPath;
        this.targetPath = targetPath;
    }

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

}
