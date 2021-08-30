package Graphics;

import Scheduling.User;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HomePane extends BorderPane {
    private User user;

    public HomePane() {
        Pane pane = new Pane();
        pane.getChildren().add(new Label("No_User_Input"));
        this.setCenter(pane);
    }
    public HomePane(User user) {
        this.user = user;
        MonthPane monthPane = new MonthPane(user);
        TaskListPane taskListPane = new TaskListPane(user);
        this.setLeft(monthPane);
        this.setRight(taskListPane);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
