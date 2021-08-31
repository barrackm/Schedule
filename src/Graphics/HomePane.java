package Graphics;

import Scheduling.User;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.util.Date;

public class HomePane extends BorderPane {
    private User user;

    public HomePane() {
        Pane pane = new Pane();
        pane.getChildren().add(new Label("No_User_Input"));
        this.setCenter(pane);
    }
    public HomePane(User user) {
        this.user = user;
        LocalDate today = LocalDate.now();
        MonthPane monthPane = new MonthPane(this, user, today.minusDays(today.getDayOfMonth() - 1));
        TaskListPane taskListPane = new TaskListPane(user);
        this.setLeft(monthPane);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(taskListPane);
        this.setRight(scrollPane);
    }

    public void setMonthPane(MonthPane monthPane) {

    }

    public void setUser(User user) {
        this.user = user;
    }
}
