package kursach.kursach;

import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static final String url = "jdbc:mysql://localhost:3306/Kursach";
    static final String user = "root";
    static final String pass = "123456";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загрузка файла FXML
        Parent root = FXMLLoader.load(getClass().getResource("entarence.fxml"));

        // Создание сцены
        Scene scene = new Scene(root, 733, 514);

        // Установка сцены на основную форму
        primaryStage.setScene(scene);

        // Установка заголовка окна
        primaryStage.setTitle("Добро пожаловать!");

        // Отображение основной формы
        primaryStage.show();
    }

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            createTablesDictionary(connection);


        }catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }


    private static void createTablesDictionary(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            // Проверка существования таблицы пользователей
            ResultSet resultSetUsers = connection.getMetaData().getTables(null, null, "users", null);
            // Если таблицы пользователей не существует
            if (!resultSetUsers.next()) {
                // Создаем таблицу
                String createUsersTableQuery = "CREATE TABLE users (" +
                        "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "login VARCHAR(255) NOT NULL," +
                        "password VARCHAR(255) NOT NULL)";
                statement.executeUpdate(createUsersTableQuery);
                System.out.println("Таблица пользователей успешно создана");

            }
            else System.out.println("Таблица users загружена!");
        }
    }
}