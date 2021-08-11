package com.mongodb.quickstart;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;

import java.net.URI;
import java.net.URISyntaxException;

public class HelloMongoDB {
    public static void main(String[] args) {

        String connectionString = "mongodb://localhost:27017";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
//                .codecRegistry(
//                        CodecRegistries.fromRegistries(
//                                CodecRegistries.fromCodecs(
//                                        new MyLongCodec(),
//                                        new MyDateCodec()
//                                ),
//                                com.mongodb.MongoClient.getDefaultCodecRegistry()
//                        )
//                )
                .codecRegistry(CustomCodecRegistry.getCustomCodecRegistry())
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings, null)) {
            MongoDatabase db = mongoClient.getDatabase("test");
//            final BasicDBObject command = new BasicDBObject();
//            command.put("serverStatus", 1);
            FindIterable<BasicDBObject> iterable = db.getCollection("test").find(BasicDBObject.class);
            MongoCursor<BasicDBObject> cursor = iterable.iterator();

            while (cursor.hasNext()) {
                System.out.println(cursor.next().toString());
            }
//            DBObject result = db.getCollection("test").find().limit(1).first();
//            for(Entry<String, Object> entry : result.entrySet())
//            {
//                System.out.println(entry.getKey());
//                System.out.println(entry.getValue());
//            }
//            Date d = (Date)db.runCommand(command).get("localTime");
//            System.out.println(d);
        }
    }

    public static URI appendURI(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());
    }
}
