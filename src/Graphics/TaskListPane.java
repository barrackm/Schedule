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

        for(TemporaryTask tempTask : user.getTemporaryTasks()) {
            tasks.add(tempTask);
            taskDisplayPanes.add(new TaskDisplayPane(tempTask));
        }
        for(RecurringTask tempTask : user.getRecurringTasks()) {
            tasks.add(tempTask);
            taskDisplayPanes.add(new TaskDisplayPane(tempTask));
        }
        Collections.sort(tasks);
        Collections.sort(taskDisplayPanes);
        for(TaskDisplayPane taskDisplayPane : taskDisplayPanes) {
            this.getChildren().add(taskDisplayPane); //Will need to sort and add scrolling
        }
    }
}
