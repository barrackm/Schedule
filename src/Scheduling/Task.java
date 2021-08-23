package Scheduling;

import org.bson.Document;

import java.util.Date;

public class Task {
    private User user;
    private String name;
    private long duration;

    public Task(User user, String name, long duration) {
        this.user = user;
        this.name = name;
        this.duration = duration;
    }

    public Task(String name, long duration) {
        this.name = name;
        this.duration = duration;
    }

    public Document getDoc() {
        return new Document()
                .append("name", name)
                .append("duration", duration);
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
}
