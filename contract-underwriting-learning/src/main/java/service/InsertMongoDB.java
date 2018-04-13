package service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

public class InsertMongoDB {
    MongoClient mongo = new MongoClient( "localhost" , 27017 );
    MongoClient mongoClient = new MongoClient();
    //DB db = mongoClient.getDB("database name");
    //boolean auth = db.authenticate("username", "password".toCharArray());

    public void printDB(){

        /*DB dbTest = mongo.getDB("test");
        System.out.println(dbTest);*/
        List<String> dbs = mongo.getDatabaseNames();
        for(String db : dbs){
            System.out.println(db);
        }
    }

    public void insertIntoDocument(){
        DB db = mongo.getDB("testdb");
        DBCollection table = db.getCollection("customer");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Ji Na");
        document.put("age", 25);
        document.put("createdDate", new Date());
        table.insert(document);
    }

   /* public static void main(String[] args){
        InsertMongoDB mongo = new InsertMongoDB();
        mongo.printDB();
        mongo.insertIntoDocument();
    }*/

}
