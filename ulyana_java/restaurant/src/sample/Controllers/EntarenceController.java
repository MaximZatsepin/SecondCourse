package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EntarenceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loadingStage;

    @FXML
    void initialize() {
        assert loadingStage != null : "fx:id=\"loadingStage\" was not injected: check your FXML file 'Entarence.fxml'.";

        new LoadingScreen().start();
    }



    public void openNewScene(String window) {
        Platform.runLater(() -> {
            Stage currentStage = (Stage) loadingStage.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(window));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        });
    }



    class LoadingScreen extends Thread {
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            { openNewScene("/sample/Templates/Login.fxml"); }
        }
    }
}
