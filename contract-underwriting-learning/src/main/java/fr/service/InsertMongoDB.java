package fr.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class InsertMongoDB {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private DB dataBase;


    public void printDB(){
        mongoClient.getDatabaseNames().stream().forEach(System.out::println);
    }

    public void insertIntoDocument(String tweet){

        DBCollection table = dataBase.getCollection("tweets");
        BasicDBObject document = new BasicDBObject();
        document.put("tweet", tweet);
        document.put("createdDate", new Date());
        table.insert(document);
    }
}
