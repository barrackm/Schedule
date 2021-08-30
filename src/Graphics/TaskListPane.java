package Graphics;

import Scheduling.Task;
import Scheduling.TemporaryTask;
import Scheduling.RecurringTask;
import Scheduling.User;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;

public class TaskListPane extends VBox {
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<TaskDisplayPane> taskDisplayPanes = new ArrayList<>();
    public TaskListPane(User user) {
        this.setMinWidth(150);

        for(TemporaryTask tempTask : user.getTemporaryTasks()) {
            tasks.add(tempTask);
            taskDisplayPanes.add(new TaskDisplayPane(tempTask));
        }
        for(RecurringTask tempTask : user.getRecurringTasks()) {
            tasks.add(tempTask);
            taskDisplayPanes.add(new TaskDisplayPane(tempTask));
        }
        for(TaskDisplayPane taskDisplayPane : taskDisplayPanes) {
            System.out.println(taskDisplayPane.getTask().getSessions().get(0).getStartTime());
        }
        Collections.sort(tasks);
        Collections.sort(tasks);
        Collections.sort(taskDisplayPanes);
        Collections.sort(taskDisplayPanes);
        for(TaskDisplayPane taskDisplayPane : taskDisplayPanes) {
            System.out.println(taskDisplayPane.getTask().getSessions().get(0).getStartTime());
            this.getChildren().add(taskDisplayPane); //Will need to sort and add scrolling
        }
    }
}
