module org.example.trigonometria {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.trigonometria to javafx.fxml;
    exports org.example.trigonometria;
}