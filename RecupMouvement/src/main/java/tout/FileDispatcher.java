package tout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
//@RestController
public class FileDispatcher extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(FileDispatcher.class, args);
    }


    }



