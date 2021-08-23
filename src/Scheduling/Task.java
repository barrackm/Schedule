package Scheduling;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private User user;
    private String name;
    private long duration;
    private ArrayList<Session> sessions;

    public Task(User user, String name, long duration) {
        this.user = user;
        this.name = name;
        this.duration = duration;
    }

    public Task(String name, long duration, ArrayList<Session> sessions) {
        this.name = name;
        this.duration = duration;
        this.sessions = sessions;
    }

    public Document getDoc() {
        return new Document()
                .append("name", name)
                .append("duration", duration)
                .append("sessions", getSessionsDoc());
    }

    private ArrayList<Document> getSessionsDoc() {
        ArrayList<Document> sessionList = new ArrayList<>(sessions.size());
        for (Session session : sessions) {
            sessionList.add(session.getDoc());
        }
        return sessionList;
    }

    protected void setSessions(ArrayList<Session> sessions) { //must run when created
        this.sessions = sessions;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setDuration(long duration) {
        this.duration = duration;
    }

    protected long getDuration() {
        return this.duration;
    }

}
