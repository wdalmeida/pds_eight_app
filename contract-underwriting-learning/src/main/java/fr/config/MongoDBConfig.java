package fr.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    @Value("${mongodb.host}")
    private String mongoDBHost;

    @Value("${mongodb.port}")
    private String mongoDBPort;

    @Bean
    public MongoClient getMongoClient() {
        MongoClient mongo = new MongoClient( mongoDBHost , Integer.parseInt(mongoDBPort) );
        return mongo;
    }



    @Bean
    public DB getMongoDataBase() {
        MongoClient mongoClient = getMongoClient();
        return mongoClient.getDB("testdb");
    }


}
