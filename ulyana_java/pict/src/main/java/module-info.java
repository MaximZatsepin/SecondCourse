module kursach.kursach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kursach.kursach to javafx.fxml;
    exports kursach.kursach;
}