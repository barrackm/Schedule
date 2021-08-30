package Scheduling;

import Database.Write;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class TemporaryTask extends Task {
    private Date deadline;

    public TemporaryTask(User user, String name, long duration, Date deadline) {
        super(user, name, duration);
        this.deadline = deadline;
        this.setSessions(generateSessions());
    }

    public TemporaryTask(String name, long duration, Date deadline, ArrayList<Session> sessions) {
        super(name, duration, sessions);
        this.deadline = deadline;
        for (Session session : sessions) {
            session.setTask(this);
        }
        this.setSessions(sessions);
    }

    public Document getDoc() {
        Document document = super.getDoc()
                .append("deadline", deadline);
        return document;
    }

    private ArrayList<Session> generateSessions() {
        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(new Session(this, new Date(1111000), this.getDuration()));
        sessions.add(new Session(this, new Date(1111111000), this.getDuration()));
        return sessions;
    }

    public void changeName(String name) {
        Write.updateTemporaryTask(this, "name", name);
        this.setName(name);
    }

    public void changeDuration(long duration) {
        Write.updateTemporaryTask(this, "duration", duration);
        this.setDuration(duration);
    }
}
