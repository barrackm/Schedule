package Scheduling;

import Database.Write;
import org.bson.Document;

import java.util.Date;

public class RecurringTask extends Task{
    private Date startTime;
    private long timeBetween;

    public RecurringTask(User user, String name, long duration, Date startTime, long timeBetween) {
        super(user, name, duration);
        this.startTime = startTime;
        this.timeBetween = timeBetween;
    }

    public RecurringTask(String name, long duration, Date startTime, long timeBetween) {
        super(name, duration);
        this.startTime = startTime;
        this.timeBetween = timeBetween;
    }

    public Document getDoc() {
        return super.getDoc()
                .append("start_time", startTime)
                .append("time_interval", timeBetween);
    }

    public void changeName(String name) {
        Write.updateRecurringTask(this, "name", name);
        this.setName(name);
    }

    public void changeDuration(long duration) {
        Write.updateRecurringTask(this, "duration", duration);
        this.setDuration(duration);
    }

    public void changeStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void changeTimeBetween(long timeBetween) {
        this.timeBetween = timeBetween;
    }
}
