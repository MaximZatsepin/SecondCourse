package kursach.kursach;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Enterance_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField entarenceLogin;

    @FXML
    private PasswordField entarencePass;

    @FXML
    private Button entarenceSingInButton;

    @FXML
    private Button entarenceSingUpButton;

    @FXML
    private ChoiceBox<?> enteranceChoiceBox;

    @FXML
    void initialize() {
        entarenceSingInButton.setOnAction(actionEvent -> {
            System.out.println("Вы нажали кнопку Войти");
        });
        entarenceSingUpButton.setOnAction(actionEvent -> {
            System.out.println("Вы нажали кнопку Зарегистрироваться");
        });


//        enteranceChoiceBox.getItems().add("Пользователь");


    }

    private void enteranceSingInButton(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));

        // Создание сцены
        Scene scene = new Scene(root, 733, 514);

        // Установка сцены на основную форму
        primaryStage.setScene(scene);

        // Установка заголовка окна
        primaryStage.setTitle("Добро пожаловать!");

        // Отображение основной формы
        primaryStage.show();
    }


}
