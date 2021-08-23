package Graphics;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    public Controller(Stage primaryStage) {
        Pane pane = new Pane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(pane, 1000, 620));
        primaryStage.show();
    }
}
