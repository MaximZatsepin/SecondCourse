module demo1.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens demo1.demo1 to javafx.fxml;
    exports demo1.demo1;
}