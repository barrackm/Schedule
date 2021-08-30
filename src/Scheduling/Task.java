package Scheduling;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class Task implements Comparable {
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

    public ArrayList<Session> getSessions() {
        return sessions;
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

    public long getDuration() {
        return this.duration;
    }

    public static String durationToString(long duration) {
        if (duration > 3600000) {
            if ((int) (duration % 3600000) / 60000 == 0) {
                return (int) duration / 3600000 + ":" + "00";
            }
            return (int) duration / 3600000 + ":" + (int) (duration % 3600000) / 60000;
        } else {
            return (int) duration / 60000  + " minutes";
        }
    }

    public static String dateTruncate(Date date) {
        String s = date.toString();
        StringBuilder newS = new StringBuilder();
        boolean colonFound = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ':') {
                if (colonFound) {
                    break;
                }
                else {
                    colonFound = true;
                }
            }
            newS.append(c);
        }
        return newS.toString();
    }

    @Override
    public int compareTo(Object o) {
        Task task = (Task) o;
        int diff = (int) ((int) task.getSessions().get(0).getStartTime().getTime() -
                this.getSessions().get(0).getStartTime().getTime());
        if (diff > 0) return 1;
        else if (diff < 0) return -1;
        return diff;
    }
}
