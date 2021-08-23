package Main;

import Database.Read;
import Graphics.Controller;
import Scheduling.RecurringTask;
import Scheduling.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import Database.Write;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Controller controller = new Controller(primaryStage);
        //User user = new User("Michael", 0.5);
        //Write.addUser(user);
        //user.addRecurringTask(new RecurringTask(user, "added", 100));
        //user.removeRecurringTask(new RecurringTask(user, "added", 100));
        //Read.getUser(2);
        User userTest = Read.getUser(2);
        userTest.setUserId(3);
        Write.addUser(userTest);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
