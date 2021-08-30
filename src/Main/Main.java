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

import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //User user = new User(6, "Michael", 1, 1, 0.5);
        //Write.addUser(user);
        //user.addRecurringTask(new RecurringTask(user, "added", 100));
        //user.removeRecurringTask(new RecurringTask(user, "added", 100));
        //System.out.println(Read.getUser(4).getUserDoc());
        User userTest = Read.getUser(6);
        Controller controller = new Controller(primaryStage, userTest);
        //System.out.println(userTest.getRecurringTasks().get(0).getSessions().get(0).getDoc());
        //userTest.getRecurringTasks().get(0).getSessions().get(0).setStartTime(new Date());
        //System.out.println(userTest.getRecurringTasks().get(0).getSessions().get(0).getDoc());
        //userTest.setUserId(3);
        //Write.addUser(userTest);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
