package Database;

import Scheduling.RecurringTask;
import Scheduling.Session;
import Scheduling.TemporaryTask;
import Scheduling.User;
import com.mongodb.client.*;
import org.bson.Document;

import org.json.*;

import java.util.ArrayList;
import java.util.Date;


import static com.mongodb.client.model.Filters.*;

public class Read {
    public static User getUser(int userId) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase database = mongoClient.getDatabase("testing_schema");
            MongoCollection<Document> collection = database.getCollection("users");
            FindIterable<Document> findIterable = collection.find(eq("user_id", userId));
            String data = findIterable.first().toJson();
            JSONObject jsonObject = new JSONObject(data);

            String name = jsonObject.getString("name");
            double sessionLength = jsonObject.getDouble("ideal_session_length");

            JSONArray recurringTasksArray = jsonObject.getJSONArray("recurring_tasks");
            ArrayList<RecurringTask> recurringTasks = new ArrayList<>(recurringTasksArray.length());

            for (var task : recurringTasksArray) {
                JSONObject taskJson = (JSONObject) task;
                String taskName = taskJson.getString("name");

                var durationJson = taskJson.getJSONObject("duration");
                long taskDuration = Long.parseLong(durationJson.getString("$numberLong"));

                var startTimeJson = taskJson.getJSONObject("start_time");
                Date taskStartTime = new Date(startTimeJson.getLong("$date"));

                var timeIntervalJson = taskJson.getJSONObject("time_interval");
                long taskTimeInterval = Long.parseLong(timeIntervalJson.getString("$numberLong"));

                JSONArray sessionsArray = taskJson.getJSONArray("sessions");
                ArrayList<Session> sessions = new ArrayList<>(sessionsArray.length());

                for (var session : sessionsArray) {
                    JSONObject sessionJson = (JSONObject) session;

                    var sessionDurationJson = sessionJson.getJSONObject("duration");
                    long sessionDuration = Long.parseLong(sessionDurationJson.getString("$numberLong"));

                    var sessionStartTimeJson = sessionJson.getJSONObject("session_start_time");
                    Date sessionStartTime = new Date(sessionStartTimeJson.getLong("$date"));

                    sessions.add(new Session(sessionStartTime, sessionDuration));
                }

                recurringTasks.add(new RecurringTask(taskName, taskDuration, taskStartTime, taskTimeInterval, sessions));
            }

            JSONArray temporaryTasksArray = jsonObject.getJSONArray("temporary_tasks");
            ArrayList<TemporaryTask> temporaryTasks = new ArrayList<>(temporaryTasksArray.length());

            for (var task : temporaryTasksArray) {
                JSONObject taskJson = (JSONObject) task;
                String taskName = taskJson.getString("name");

                var durationJson = taskJson.getJSONObject("duration");
                long taskDuration = Long.parseLong(durationJson.getString("$numberLong"));

                var deadlineJson = taskJson.getJSONObject("deadline");
                Date taskDeadline = new Date(deadlineJson.getLong("$date"));

                JSONArray sessionsArray = taskJson.getJSONArray("sessions");
                ArrayList<Session> sessions = new ArrayList<>(sessionsArray.length());

                for (var session : sessionsArray) {
                    JSONObject sessionJson = (JSONObject) session;

                    var sessionDurationJson = sessionJson.getJSONObject("duration");
                    long sessionDuration = Long.parseLong(sessionDurationJson.getString("$numberLong"));

                    var sessionStartTimeJson = sessionJson.getJSONObject("session_start_time");
                    Date sessionStartTime = new Date(sessionStartTimeJson.getLong("$date"));

                    sessions.add(new Session(sessionStartTime, sessionDuration));
                }

                temporaryTasks.add(new TemporaryTask(taskName, taskDuration, taskDeadline, sessions));
            }

            return new User(name, sessionLength, temporaryTasks, recurringTasks);
        }
    }
}
