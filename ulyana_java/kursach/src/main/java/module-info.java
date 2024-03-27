module kursach.kursach {
    requires javafx.controls;
    requires javafx.fxml;


    opens kursach.kursach to javafx.fxml;
    exports kursach.kursach;
}