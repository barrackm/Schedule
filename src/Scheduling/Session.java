package Scheduling;

import Database.*;
import org.bson.Document;
import java.util.Date;

public class Session {
    private Task task;
    private Date startTime;
    private long duration;

    public Session(Task task, Date startTime, long duration) {
        this.task = task;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Session(Date startTime, long duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setStartTime(Date startTime) {
        Write.updateRecurringTaskSession(this, "session_start_time", startTime);
    }

    public Date getStartTime() {
        return startTime;
    }

    public Document getDoc() {
        return new Document()
                .append("session_start_time", startTime)
                .append("duration", duration);
    }
}
