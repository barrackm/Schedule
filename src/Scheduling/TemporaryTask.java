package Scheduling;

import org.bson.Document;

import java.util.Date;

public class TemporaryTask extends Task {
    private Date deadline;

    public TemporaryTask(User user, String name, long duration, Date deadline) {
        super(user, name, duration);
        this.deadline = deadline;
    }

    public TemporaryTask(String name, long duration, Date deadline) {
        super(name, duration);
        this.deadline = deadline;
    }

    public Document getDoc() {
        Document document = super.getDoc()
                .append("deadline", deadline);
        return document;
    }
}
