package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoginWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane LoginPane;

    @FXML
    private AnchorPane RegistrationPane;

    @FXML
    private Button backButton;

    @FXML
    private Button registrationButton;

    @FXML
    void initialize() {
        registrationButton.setOnAction(event -> {onRegistrationButtonClick(LoginPane, RegistrationPane);});
        backButton.setOnAction(event -> {onBackButtonClick(LoginPane, RegistrationPane);});
        assert LoginPane != null : "fx:id=\"LoginPane\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert RegistrationPane != null : "fx:id=\"RegistrationPane\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert registrationButton != null : "fx:id=\"registrationButton\" was not injected: check your FXML file 'loginWindow.fxml'.";

    }

    public void onRegistrationButtonClick(AnchorPane loginPane, AnchorPane registrationPane) {
        loginPane.setVisible(false);
        registrationPane.setVisible(true);
    }

    public void onBackButtonClick(AnchorPane loginPane, AnchorPane registrationPane){
        loginPane.setVisible(true);
        registrationPane.setVisible(false);
    }

}
