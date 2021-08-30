package Scheduling;

import Database.Write;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class RecurringTask extends Task{
    private Date startTime;
    private long timeBetween;

    public RecurringTask(User user, String name, long duration, Date startTime, long timeBetween) {
        super(user, name, duration);
        this.startTime = startTime;
        this.timeBetween = timeBetween;
        this.setSessions(generateSessions());
    }

    // Load From database
    public RecurringTask(String name, long duration, Date startTime, long timeBetween, ArrayList<Session> sessions) {
        super(name, duration,sessions);
        this.startTime = startTime;
        this.timeBetween = timeBetween;
        for (Session session : sessions) {
            session.setTask(this);
        }
        this.setSessions(sessions);
    }

    public Document getDoc() {
        return super.getDoc()
                .append("start_time", startTime)
                .append("time_interval", timeBetween);
    }

    private ArrayList<Session> generateSessions() {
        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(new Session(this, new Date(100000), this.getDuration()));
        sessions.add(new Session(this, new Date(1000), this.getDuration()));
        return sessions;
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
        Write.updateRecurringTask(this, "start_time", startTime);
        this.startTime = startTime;
    }

    public void changeTimeBetween(long timeBetween) {
        Write.updateRecurringTask(this, "time_interval", timeBetween);
        this.timeBetween = timeBetween;
    }
}
