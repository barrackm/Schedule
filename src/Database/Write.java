package Database;

import Scheduling.*;
import com.mongodb.client.*;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class Write {

    public static void addUser(User user) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");

            collection.insertOne(user.getUserDoc());
        }
    }

    public static void addRecurringTask(RecurringTask task) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");


            collection.updateOne(eq("user_id",
                    task.getUser().getUserId()), new Document("$addToSet", new Document("recurring_tasks", task.getDoc())));
        }
    }

    public static void removeRecurringTask(RecurringTask task) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");

            collection.updateOne(eq("user_id",
                    task.getUser().getUserId()), new Document("$pull", new Document("recurring_tasks", new Document("name", task.getName()))));
        }
    }

    public static void updateRecurringTask(RecurringTask task, String field, Object value) { //update memory after
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");

            collection.updateOne(eq("user_id",
                    task.getUser().getUserId()), new Document("$set", new Document("recurring_tasks.$[element]." + field, value)),
                    new UpdateOptions().arrayFilters(Arrays.asList(eq("element.name", task.getName()))));


        }
    }

    public static void updateRecurringTaskSession(Session session, String field, Object value) { //update memory after
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");

            System.out.println(collection.updateOne(eq("user_id",
                    session.getTask().getUser().getUserId()), new Document("$set", new Document("recurring_tasks.$[element].sessions.$[element2]." + field, value)),
                    new UpdateOptions().arrayFilters(Arrays.asList(eq("element.name", session.getTask().getName()), eq("element2.session_start_time", session.getStartTime())))));

            System.out.println(session.getTask().getName());
            System.out.println(session.getStartTime());
            System.out.println(value);
        }
    }

    public static void addTemporaryTask(TemporaryTask task) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");


            collection.updateOne(eq("user_id",
                    task.getUser().getUserId()), new Document("$addToSet", new Document("temporary_tasks", task.getDoc())));
        }
    }

    public static void removeTemporaryTask(TemporaryTask task) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");


            collection.updateOne(eq("user_id",
                    task.getUser().getUserId()), new Document("$pull", new Document("temporary_tasks", new Document("name", task.getName()))));
        }
    }

    public static void updateTemporaryTask(TemporaryTask task, String field, Object value) { //update memory after
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");


            collection.updateOne(eq("user_id",
                    task.getUser().getUserId()), new Document("$set", new Document("temporary_tasks.$[element]." + field, value)),
                    new UpdateOptions().arrayFilters(Arrays.asList(eq("element.name", task.getName()))));


        }
    }
}
