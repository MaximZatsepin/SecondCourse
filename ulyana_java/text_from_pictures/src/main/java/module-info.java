module test_kursach.text_from_pictures {
    requires javafx.controls;
    requires javafx.fxml;


    opens test_kursach.text_from_pictures to javafx.fxml;
    exports test_kursach.text_from_pictures;
}