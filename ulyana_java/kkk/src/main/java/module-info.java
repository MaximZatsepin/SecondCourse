module org.example.kkk {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.kkk to javafx.fxml;
    exports org.example.kkk;
}