module test_kursach.test_kursach {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens test_kursach.test_kursach to javafx.fxml;
    exports test_kursach.test_kursach;
}