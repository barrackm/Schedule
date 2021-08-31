package Graphics;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CalendarDayPane extends VBox {
    private LocalDate localDate;
    public CalendarDayPane(LocalDate date) {
        this.localDate = date;
        this.setPrefSize(75, 75);
        this.getChildren().add(new Label("" + localDate.getDayOfMonth()));
    }

    public CalendarDayPane() {
        this.setPrefSize(75, 75);
        this.getChildren().add(new Label(""));
    }
}
