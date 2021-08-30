package Scheduling;

import Database.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private int userId;
    private String name;
    private long dayStartTime;
    private long dayEndTime;
    private double preferredSessionDuration;
    private ArrayList<TemporaryTask> temporaryTasks = new ArrayList<TemporaryTask>();
    private ArrayList<RecurringTask> recurringTasks = new ArrayList<RecurringTask>();

    public User(int userId, String name, long dayStartTime, long dayEndTime, double preferredSessionDuration) {
        this.userId = userId; //solve
        this.name = name;
        this.dayStartTime = dayStartTime;
        this.dayEndTime = dayEndTime;
        this.preferredSessionDuration = preferredSessionDuration;
        RecurringTask recurringTask = new RecurringTask(this, "Brush Teeth", 1, new Date(), 1);
        recurringTasks.add(recurringTask);
        recurringTasks.add(new RecurringTask(this, "Eat Lunch", 1, new Date(), 1));
        temporaryTasks.add(new TemporaryTask(this, "Do Homework", 1, new Date()));
        //Write.updateRecurringTask(recurringTask, "duration", 0.5);
    }

    public User(int userId, String name, long dayStartTime, long dayEndTime, double preferredSessionDuration, ArrayList<TemporaryTask> temporaryTasks, ArrayList<RecurringTask> recurringTasks) {
        this.userId = userId;
        this.name = name;
        this.dayStartTime = dayStartTime;
        this.dayEndTime = dayEndTime;
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
                .append("day_start_time", this.dayStartTime)
                .append("day_end_time", this.dayEndTime)
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

    public ArrayList<RecurringTask> getRecurringTasks() {
        return recurringTasks;
    }

    public ArrayList<TemporaryTask> getTemporaryTasks() {
        return temporaryTasks;
    }
}
