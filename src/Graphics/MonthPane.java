package Graphics;

import Scheduling.User;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
    private User user;
    private LocalDate monthStart;
    public MonthPane(HomePane homePane, User user, LocalDate localDate) {
        this.setMinWidth(400);
        this.setPadding(new Insets(50, 50, 50, 50));
        this.localDate = localDate; //remove plus days thing
        this.month = localDate.getMonth();
        this.user = user;
        this.monthStart = localDate;

        BorderPane titlePane = new BorderPane();
        Button prevMonthBtn = new Button("<<");
        Button nextMonthBtn = new Button(">>");

        prevMonthBtn.setOnAction(e -> {
            System.out.println(monthStart);
            monthStart = monthStart.minusDays(1);
            System.out.println(monthStart);
            Month prevMonth = monthStart.getMonth();
            monthStart = monthStart.minusDays(prevMonth.length(monthStart.isLeapYear()) - 1);
            System.out.println(monthStart);
            homePane.setLeft(new MonthPane(homePane, user, monthStart));
        });
        nextMonthBtn.setOnAction(e -> {
            monthStart = monthStart.plusDays(month.length(monthStart.isLeapYear()));
            homePane.setLeft(new MonthPane(homePane, user, monthStart));
        });

        titlePane.setLeft(prevMonthBtn);
        titlePane.setRight(nextMonthBtn);
        titlePane.setCenter(new Label(month.toString() + "    " + monthStart.getYear()));
        titlePane.setPadding(new Insets(10, 10, 10, 10));



        this.getChildren().add(titlePane);

        int firstDay = dayOfWeekInt(localDate.getDayOfWeek());
        boolean monthOver = false;

        for (int i = 0; i < 6; i++) {
            HBox weekBox = new HBox();
            for (int j = 0; j < 7; j++) {
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
