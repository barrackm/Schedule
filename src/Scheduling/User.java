package Scheduling;

import Database.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private int userId = 2;
    private String name;
    private double preferredSessionDuration;
    private ArrayList<TemporaryTask> temporaryTasks = new ArrayList<TemporaryTask>();
    private ArrayList<RecurringTask> recurringTasks = new ArrayList<RecurringTask>();

    public User(String name, double preferredSessionDuration) {
        this.name = name;
        this.preferredSessionDuration = preferredSessionDuration;
        RecurringTask recurringTask = new RecurringTask(this, "Brush Teeth", 1, new Date(), 1);
        recurringTasks.add(recurringTask);
        recurringTasks.add(new RecurringTask(this, "Eat Lunch", 1, new Date(), 1));
        temporaryTasks.add(new TemporaryTask(this, "Do Homework", 1, new Date()));
        //Write.updateRecurringTask(recurringTask, "duration", 0.5);
    }

    public User(String name, double preferredSessionDuration, ArrayList<TemporaryTask> temporaryTasks, ArrayList<RecurringTask> recurringTasks) {
        this.name = name;
        this.preferredSessionDuration = preferredSessionDuration;
        for (TemporaryTask task : temporaryTasks) {
            task.setUser(this);
        }
        this.temporaryTasks = temporaryTasks;
        for (RecurringTask task : recurringTasks) {
            task.setUser(this);
        }
        this.recurringTasks = recurringTasks;
    }

    public void addRecurringTask(RecurringTask task) {
        Write.addRecurringTask(task);
        recurringTasks.add(task);
    }

    public void removeRecurringTask(RecurringTask task) {
        Write.removeRecurringTask(task);
        recurringTasks.remove(task);
    }

    public Document getUserDoc() {
        return new Document("_id", new ObjectId())
                .append("user_id", this.userId)
                .append("name", name)
                .append("ideal_session_length", this.preferredSessionDuration)
                .append("recurring_tasks", this.getRecurringTasksDoc(recurringTasks))
                .append("temporary_tasks", this.getTemporaryTasksDoc(temporaryTasks));
    }

    private ArrayList<Document> getRecurringTasksDoc(ArrayList<RecurringTask> list) {
        ArrayList<Document> tasksList = new ArrayList<>(list.size());
        for (RecurringTask task : list) {
            tasksList.add(task.getDoc());
        }
        return tasksList;
    }

    private ArrayList<Document> getTemporaryTasksDoc(ArrayList<TemporaryTask> list) {
        ArrayList<Document> tasksList = new ArrayList<>(list.size());
        for (TemporaryTask task : list) {
            tasksList.add(task.getDoc());
        }
        return tasksList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) { //for testing
        this.userId = userId;
    }
}
