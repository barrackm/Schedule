package Graphics;

import Scheduling.RecurringTask;
import Scheduling.TemporaryTask;
import Scheduling.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskDisplayPane extends VBox implements Comparable{
    private Task task;
    public TaskDisplayPane(RecurringTask task) {
        this.task = task;
        int paddingH = 10;
        int paddingV = 5;
        Label name = new Label();
        name.setText("Task: " + task.getName());
        name.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        Label duration = new Label("Duration: " + Task.durationToString(task.getDuration()));
        duration.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        Label timeInterval = new Label("Interval : " + Task.durationToString(task.getTimeBetween()));
        timeInterval.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        Label nextSession = new Label("Next Session: " + Task.dateTruncate(task.getSessions().get(0).getStartTime()));
        nextSession.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        HBox topRow = new HBox();
        HBox bottomRow = new HBox();

        topRow.getChildren().addAll(name, duration);
        bottomRow.getChildren().addAll(nextSession, timeInterval);

        this.getChildren().addAll(topRow, bottomRow);
        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    }

    public TaskDisplayPane(TemporaryTask task) {
        this.task = task;
        int paddingH = 10;
        int paddingV = 5;
        Label name = new Label();
        name.setText("Task: " + task.getName());
        name.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        Label duration = new Label("Duration: " + Task.durationToString(task.getDuration()));
        duration.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        Label nextSession = new Label("Next Session: " + Task.dateTruncate(task.getSessions().get(0).getStartTime()));
        nextSession.setPadding(new Insets(paddingV, paddingH, paddingV, paddingH));

        HBox topRow = new HBox();
        HBox bottomRow = new HBox();

        topRow.getChildren().addAll(name, duration);
        bottomRow.getChildren().addAll(nextSession);

        this.getChildren().addAll(topRow, bottomRow);
        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    }

    public Task getTask() {
        return task;
    }

    @Override
    public int compareTo(Object o) {
        TaskDisplayPane taskDisplayPane = (TaskDisplayPane) o;
        if (this.getTask().getSessions().get(0).getStartTime().getTime() -
                taskDisplayPane.getTask().getSessions().get(0).getStartTime().getTime() == 0) {
            return 0;
        }
        long diff = (this.getTask().getSessions().get(0).getStartTime().getTime() -
                taskDisplayPane.getTask().getSessions().get(0).getStartTime().getTime()) /
                Math.abs(this.getTask().getSessions().get(0).getStartTime().getTime() -
                        taskDisplayPane.getTask().getSessions().get(0).getStartTime().getTime());
        return (int) diff;
    }
}
