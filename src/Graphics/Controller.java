package Graphics;

import Scheduling.User;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    public Controller(Stage primaryStage, User user) {
        HomePane homePane = new HomePane(user);
        primaryStage.setTitle("Schedule");
        primaryStage.setScene(new Scene(homePane, 1000, 620));
        primaryStage.show();
    }

    public Controller(Stage primaryStage) {
        HomePane homePane = new HomePane();
        primaryStage.setTitle("Schedule");
        primaryStage.setScene(new Scene(homePane, 1000, 620));
        primaryStage.show();
    }
}
