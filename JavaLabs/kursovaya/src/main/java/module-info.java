module org.example.kursovaya {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.kursovaya to javafx.fxml;
    exports org.example.kursovaya;
}