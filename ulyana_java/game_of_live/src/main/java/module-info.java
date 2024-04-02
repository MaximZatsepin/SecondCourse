module demo1.game_of_live {
    requires javafx.controls;
    requires javafx.fxml;


    opens demo1.game_of_live to javafx.fxml;
    exports demo1.game_of_live;
}