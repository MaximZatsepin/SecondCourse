package org.example.trigonometria;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class TrigonometricCalculator extends Application {

    private TableView<ResultRow> tableView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Trigonometric Calculator");

        // Создаем текстовые поля и кнопки
        TextField degreeInput = new TextField();
        degreeInput.setPromptText("Degree");

        TextField radianInput = new TextField();
        radianInput.setPromptText("Radian");

        TextField resultOutput = new TextField();
        resultOutput.setEditable(false);

        Button sinButton = new Button("sin");
        sinButton.setOnAction(e -> {
            if (!degreeInput.getText().isEmpty()) {
                double degree = Double.parseDouble(degreeInput.getText());
                double radian = Math.toRadians(degree);
                double result = Math.sin(radian);
                resultOutput.setText(formatResult(result));
                addTableRow(degree, radian, result);
            } else if (!radianInput.getText().isEmpty()) {
                double radian = Double.parseDouble(radianInput.getText());
                double degree = Math.toDegrees(radian);
                double result = Math.sin(radian);
                resultOutput.setText(formatResult(result));
                addTableRow(degree, radian, result);
            } else {
                return;
            }
        });

        Button cosButton = new Button("cos");
        cosButton.setOnAction(e -> {
            if (!degreeInput.getText().isEmpty()) {
                double degree = Double.parseDouble(degreeInput.getText());
                double radian = Math.toRadians(degree);
                double result = Math.cos(radian);
                resultOutput.setText(formatResult(result));
                addTableRow(degree, radian, result);
            } else if (!radianInput.getText().isEmpty()) {
                double radian = Double.parseDouble(radianInput.getText());
                double degree = Math.toDegrees(radian);
                double result = Math.cos(radian);
                resultOutput.setText(formatResult(result));
                addTableRow(degree, radian, result);
            } else {
                return;
            }
        });

        Button tanButton = new Button("tan");
        tanButton.setOnAction(e -> {
            if (!degreeInput.getText().isEmpty()) {
                double degree = Double.parseDouble(degreeInput.getText());
                double radian = Math.toRadians(degree);
                double result = Math.tan(radian);
                resultOutput.setText(formatResult(result));
                addTableRow(degree, radian, result);
            } else if (!radianInput.getText().isEmpty()) {
                double radian = Double.parseDouble(radianInput.getText());
                double degree = Math.toDegrees(radian);
                double result = Math.tan(radian);
                resultOutput.setText(formatResult(result));
                addTableRow(degree, radian, result);
            } else {
                return;
            }
        });

        // Создаем таблицу для отображения результатов
        tableView = new TableView<>();

        TableColumn<ResultRow, Double> degreeColumn = new TableColumn<>("Градусы");
        degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));

        TableColumn<ResultRow, Double> radianColumn = new TableColumn<>("Радианы");
        radianColumn.setCellValueFactory(new PropertyValueFactory<>("radian"));

        TableColumn<ResultRow, Double> resultColumn = new TableColumn<>("Результат");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));

        tableView.getColumns().addAll(degreeColumn, radianColumn, resultColumn);

        // Создаем сетку для размещения элементов интерфейса
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Размещаем элементы на сетке
        grid.add(new Label("Введите градусы:"), 0, 0);
        grid.add(degreeInput, 1, 0);
        grid.add(new Label("или введите радианы:"), 2, 0);
        grid.add(radianInput, 3, 0);

        grid.add(new Label("Результат:"), 0, 2);
        grid.add(resultOutput, 1, 2);

        grid.add(sinButton, 0, 1);
        grid.add(cosButton, 1, 1);
        grid.add(tanButton, 2, 1);

        grid.add(tableView, 0, 4, 4, 1);

        // Создаем сцену
        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private String formatResult(double result) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(result);
    }

    private void addTableRow(double degree, double radian, double result) {
        tableView.getItems().add(new ResultRow(degree, radian, result));
    }

    public static class ResultRow {
        private final SimpleDoubleProperty degree;
        private final SimpleDoubleProperty radian;
        private final SimpleDoubleProperty result;

        public ResultRow(double degree, double radian, double result) {
            this.degree = new SimpleDoubleProperty(degree);
            this.radian = new SimpleDoubleProperty(radian);
            this.result = new SimpleDoubleProperty(result);
        }

        public double getDegree() {
            return degree.get();
        }

        public double getRadian() {
            return radian.get();
        }

        public double getResult() {
            return result.get();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
