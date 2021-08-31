package Graphics;

import Scheduling.User;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class MonthPane extends VBox {
    private LocalDate localDate;
    private Month month;
    public MonthPane(User user, Date date) {
        this.setMinWidth(400);
        this.setPadding(new Insets(50, 50, 50, 50));
        this.localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(31); //remove plus days thing
        this.month = localDate.getMonth();
        this.getChildren().add(new Label(month.toString()));
        int firstDay = dayOfWeekInt(localDate.getDayOfWeek());
        boolean monthOver = false;

        for (int i = 0; i < 6; i++) {
            HBox weekBox = new HBox();
            for (int j = 0; j < 7; j++) {
                System.out.println(monthOver);
                if (localDate.getMonth() != month) {
                    monthOver = true;
                }
                if (i == 0 && j < firstDay || monthOver) {
                    weekBox.getChildren().add(new CalendarDayPane());
                } else {
                    weekBox.getChildren().add(new CalendarDayPane(localDate));
                    localDate = localDate.plusDays(1);
                }
            }
            this.getChildren().add(weekBox);
        }
    }

    private int dayOfWeekInt(DayOfWeek dayOfWeek) {
        switch (dayOfWeek.toString()) {
            case "SUNDAY":
                return 0;
            case "MONDAY":
                return 1;
            case "TUESDAY":
                return 2;
            case "WEDNESDAY":
                return 3;
            case "THURSDAY":
                return 4;
            case "FRIDAY":
                return 5;
            case "SATURDAY":
                return 6;
        }
        return -1;
    }
}
