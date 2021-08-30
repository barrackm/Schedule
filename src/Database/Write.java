package Database;

import Scheduling.RecurringTask;
import Scheduling.Session;
import Scheduling.TemporaryTask;
import Scheduling.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

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

            collection.updateOne(eq("user_id",
                    session.getTask().getUser().getUserId()), new Document("$set", new Document("recurring_tasks.$[element].sessions.$[element2]." + field, value)),
                    new UpdateOptions().arrayFilters(Arrays.asList(eq("element.name", session.getTask().getName()), eq("element2.session_start_time", session.getStartTime()))));
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

    public static void updateTemporaryTaskSession(Session session, String field, Object value) { //update memory after
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {

            MongoDatabase database = mongoClient.getDatabase("testing_schema");

            MongoCollection<Document> collection = database.getCollection("users");

            collection.updateOne(eq("user_id",
                    session.getTask().getUser().getUserId()), new Document("$set", new Document("temporary_tasks.$[element].sessions.$[element2]." + field, value)),
                    new UpdateOptions().arrayFilters(Arrays.asList(eq("element.name", session.getTask().getName()), eq("element2.session_start_time", session.getStartTime()))));
        }
    }
}
