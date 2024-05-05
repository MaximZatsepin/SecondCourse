package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import sample.Animations.MoveMenu;
import sample.HandlerDataBase;
import sample.Dish;
import sample.Order;

public class ManagerWindowController {
    @FXML
    private Button AnalyticsButton;

    @FXML
    private Button AnalyticsButtonView;

    @FXML
    private AnchorPane AnalyticsPane;

    @FXML
    private PieChart AnalyticsPie;

    @FXML
    private Button BackButton;

    @FXML
    private AnchorPane ButtonsPane;

    @FXML
    private Button DishAddButton;

    @FXML
    private Button DishButton;

    @FXML
    private TextField DishCategoryText;

    @FXML
    private Button DishChangeButton;

    @FXML
    private Button DishClearButton;

    @FXML
    private TextField DishCookingTimeText;

    @FXML
    private Button DishDeleteButton;

    @FXML
    private TextField DishMainIngredientText;

    @FXML
    private TextField DishNameText;

    @FXML
    private AnchorPane DishPane;

    @FXML
    private TextField DishPriceText;

    @FXML
    private ChoiceBox<String> DishStatusChoiceBox;

    @FXML
    private TableView<Dish> DishTable;

    @FXML
    private TableColumn<Dish, String> DishTableCategory;

    @FXML
    private TableColumn<Dish, String> DishTableCookingTime;

    @FXML
    private TableColumn<Dish, Integer> DishTableCountDishes;

    @FXML
    private TableColumn<Dish, String> DishTableMainIngredient;

    @FXML
    private TableColumn<Dish, Integer> DishTablePrice;

    @FXML
    private TableColumn<Dish, String> DishTableStatus;

    @FXML
    private TableColumn<Dish, String> DishTableName;

    @FXML
    private Button OrderButton;

    @FXML
    private TableColumn<Order, String> OrderColumnCategory;

    @FXML
    private TableColumn<Order, String> OrderColumnDishName;

    @FXML
    private TableColumn<Order, Integer> OrderColumnIDUser;

    @FXML
    private TableColumn<Order, String> OrderColumnNameUser;

    @FXML
    private TableColumn<Order, Integer> OrderColumnPrice;

    @FXML
    private TableColumn<Order, String> OrderColumnSurnameUser;

    @FXML
    private Button OrderFindIDButton;

    @FXML
    private TextField OrderIDFind;

    @FXML
    private AnchorPane OrderPane;

    @FXML
    private TableView<Order> OrderTable;

    @FXML
    void onButtonPaneMouseEntered(MouseEvent event) { MoveMenu.moveDown(ButtonsPane); }

    @FXML
    void onButtonPaneMouseExited(MouseEvent event) { MoveMenu.moveUp(ButtonsPane); }



    private ObservableList<String> DishStatuses = FXCollections.observableArrayList(Arrays.asList("В наличии", "Не в наличии"));


    @FXML
    void initialize() {

        initSettings();

        BackButton.setOnAction(event -> onExitButtonClick());
        OrderButton.setOnAction(event -> onOrderButtonClick());
        DishButton.setOnAction(event -> onDishButtonClick());
        AnalyticsButton.setOnAction(event -> onAnalyticsButtonClick());

        DishAddButton.setOnAction(event -> onDishAddButtonClick());
        DishDeleteButton.setOnAction(event -> onDishDeleteButtonClick());
        DishClearButton.setOnAction(event -> onDishClearButtonClick());
        DishChangeButton.setOnAction(event -> onDishChangeButtonClick());
//
        OrderFindIDButton.setOnAction(event -> onFindIdButtonClick());

        AnalyticsButtonView.setOnAction(event -> onAnalyticsChooseDishBoxClick());

        assert AnalyticsButton != null : "fx:id=\"AnalyticsButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
       assert AnalyticsPane != null : "fx:id=\"AnalyticsPane\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert AnalyticsPie != null : "fx:id=\"AnalyticsPie\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert ButtonsPane != null : "fx:id=\"ButtonsPane\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishAddButton != null : "fx:id=\"DishAddButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishButton != null : "fx:id=\"DishButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishCategoryText != null : "fx:id=\"DishCategoryText\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishChangeButton != null : "fx:id=\"DishChangeButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishClearButton != null : "fx:id=\"DishClearButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishCookingTimeText != null : "fx:id=\"DishCookingTimeText\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishDeleteButton != null : "fx:id=\"DishDeleteButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishMainIngredientText != null : "fx:id=\"DishMainIngredientText\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishNameText != null : "fx:id=\"DishNameText\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishPane != null : "fx:id=\"DishPane\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishPriceText != null : "fx:id=\"DishPriceText\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishStatusChoiceBox != null : "fx:id=\"DishStatusChoiceBox\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTable != null : "fx:id=\"DishTable\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTableCategory != null : "fx:id=\"DishTableCategory\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTableCookingTime != null : "fx:id=\"DishTableCookingTimesh\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTableCountDishes != null : "fx:id=\"DishTableCountDishes\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTableMainIngredient != null : "fx:id=\"DishTableMainIngredient\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTablePrice != null : "fx:id=\"DishTablePrice\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTableStatus != null : "fx:id=\"DishTableStatus\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert DishTableName != null : "fx:id=\"DishTablename\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderColumnCategory != null : "fx:id=\"OrderColumnCategory\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderColumnDishName != null : "fx:id=\"OrderColumnDishName\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderColumnIDUser != null : "fx:id=\"OrderColumnIDUser\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderColumnNameUser != null : "fx:id=\"OrderColumnNameUser\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderColumnPrice != null : "fx:id=\"OrderColumnPrice\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderColumnSurnameUser != null : "fx:id=\"OrderColumnSurnameUser\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderFindIDButton != null : "fx:id=\"OrderFindIDButton\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderIDFind != null : "fx:id=\"OrderIDFind\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderPane != null : "fx:id=\"OrderPane\" was not injected: check your FXML file 'ManagerWindow.fxml'.";
        assert OrderTable != null : "fx:id=\"OrderTable\" was not injected: check your FXML file 'ManagerWindow.fxml'.";

    }



    public void initSettings(){

        ButtonsPane.setLayoutY(-150);
        onDishButtonClick();

        DishTableName.setCellValueFactory(new PropertyValueFactory<Dish,String>("dishName"));
        DishTableMainIngredient.setCellValueFactory(new PropertyValueFactory<Dish,String>("mainIngredient"));
        DishTableCategory.setCellValueFactory(new PropertyValueFactory<Dish,String>("category"));
        DishTableCookingTime.setCellValueFactory(new PropertyValueFactory<Dish,String>("cookingTime"));
        DishTablePrice.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("price"));
        DishTableCountDishes.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("countDishes"));
        DishTableStatus.setCellValueFactory(new PropertyValueFactory<Dish,String>("status"));
        updateDishTable();

        DishStatusChoiceBox.setItems(DishStatuses);

        OrderColumnIDUser.setCellValueFactory(new PropertyValueFactory<Order,Integer>("userId"));
        OrderColumnDishName.setCellValueFactory(new PropertyValueFactory<Order,String>("dishName"));
        OrderColumnNameUser.setCellValueFactory(new PropertyValueFactory<Order,String>("userName"));
        OrderColumnSurnameUser.setCellValueFactory(new PropertyValueFactory<Order,String>("userSurname"));
        OrderColumnCategory.setCellValueFactory(new PropertyValueFactory<Order,String>("dishCategory"));
        OrderColumnPrice.setCellValueFactory(new PropertyValueFactory<Order,Integer>("price"));
        updateOrderTable();

    }

    public void onDishButtonClick(){
        hideAllPanes();
        paintAllButtonsBlue();
        DishButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        DishPane.setVisible(true);

        updateDishTable();
    }


    public void hideAllPanes(){
        AnalyticsPane.setVisible(false);
        DishPane.setVisible(false);
        OrderPane.setVisible(false);
    }


    public void paintAllButtonsBlue(){
        DishButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        OrderButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        AnalyticsButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
    }


    public void updateDishTable() {
        ObservableList<Dish> dishData = FXCollections.observableArrayList(new HandlerDataBase().getAllDish());
        DishTable.setItems(dishData);
    }

    public void onAnalyticsButtonClick(){
        hideAllPanes();
        paintAllButtonsBlue();
        AnalyticsButton.setStyle("-fx-background-color:  #bf615a; -fx-background-radius: 15;");
        AnalyticsPane.setVisible(true);

        AnalyticsUpdate();
    }


    public void onOrderButtonClick(){
        hideAllPanes();
        paintAllButtonsBlue();
        OrderButton.setStyle("-fx-background-color:  #bf615a; -fx-background-radius: 15;");
        OrderPane.setVisible(true);

        updateOrderTable();
    }



    public void updateOrderTable(){
        ObservableList<Order> orderData = FXCollections.observableArrayList(new HandlerDataBase().getAllOrders());
        OrderTable.setItems(orderData);
    }



    public void AnalyticsUpdate(){
        List<Dish> all_dish = new HandlerDataBase().getAllDish();
        List<String> all_dish1 = new ArrayList<String>();
        for(Dish dish : all_dish){
            all_dish1.add(String.format("%s | %s-%s",dish.getDishName(), dish.getMainIngredient(),dish.getCategory()));
        }
        ObservableList<String> dishes = FXCollections.observableArrayList(all_dish1);
    }




    public void onExitButtonClick(){
        Stage currentStage = (Stage) BackButton.getScene().getWindow();
        currentStage.close();
    }


    public void onDishAddButtonClick(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String name = DishNameText.getText();
        String ingredient = DishMainIngredientText.getText();
        String category = DishCategoryText.getText();
        String cookingTime = DishCookingTimeText.getText();
        int price = Integer.parseInt(DishPriceText.getText());
        int count = 30;
        String status = DishStatusChoiceBox.getValue();

        Dish newDish = new Dish(name, ingredient, category, cookingTime, price,count,status);
        new HandlerDataBase().addDishToTable(newDish);

        updateDishTable();
    }

    public void onDishDeleteButtonClick(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Dish chosen_dish = DishTable.getSelectionModel().getSelectedItem();
        if(chosen_dish == null){
            alert.setContentText("Выберите запись!");
            alert.showAndWait();
            return;
        }
        new HandlerDataBase().deleteDishtByDishName(chosen_dish.getDishName());

        updateDishTable();
    }

    public void onDishClearButtonClick(){
        DishNameText.setText("");
        DishMainIngredientText.setText("");
        DishCategoryText.setText("");
        DishCookingTimeText.setText("");
        DishPriceText.setText("");
        DishStatusChoiceBox.setValue("");

        updateDishTable();
    }

    public void onDishChangeButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Dish chosen_dish = DishTable.getSelectionModel().getSelectedItem();
        if (chosen_dish == null) {
            alert.setContentText("Выберите запись!");
            alert.showAndWait();
            return;
        }

        String name = DishNameText.getText().equals("") ? chosen_dish.getDishName() : DishNameText.getText();
        String ingredient = DishMainIngredientText.getText().equals("") ? chosen_dish.getMainIngredient() : DishMainIngredientText.getText();
        String category = DishCategoryText.getText().equals("") ? chosen_dish.getCategory() : DishCategoryText.getText();
        String cookingTime = DishCookingTimeText.getText().equals("") ? chosen_dish.getCookingTime() : DishCookingTimeText.getText();
        int price = DishPriceText.getText().equals("") ? chosen_dish.getPrice() : Integer.parseInt(DishPriceText.getText());
        String status = DishStatusChoiceBox.getValue().isEmpty() ? "В наличии" : DishStatusChoiceBox.getValue();


        chosen_dish.setDishName(name);
        chosen_dish.setMainIngredient(ingredient);
        chosen_dish.setCategory(category);
        chosen_dish.setCookingTime(cookingTime);
        chosen_dish.setPrice(price);
        chosen_dish.setStatus(status);

        new HandlerDataBase().updateDish(chosen_dish);

        updateDishTable();
    }


    public void onFindIdButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String id = OrderIDFind.getText();
        if(id.equals("") || id == null){
            alert.setContentText("Ввведите id!");
            alert.showAndWait();
            updateOrderTable();
            return;
        }

        ObservableList<Order> orderData = FXCollections.observableArrayList(new HandlerDataBase().getAllOrdersByUserId(id));
        OrderTable.setItems(orderData);
    }




    public void onAnalyticsChooseDishBoxClick(){
        List<Dish> allDishes = new HandlerDataBase().getAllDish();

        AnalyticsPie.getData().clear();

        for (Dish dish : allDishes) {
            int countOfDishes = dish.getCountDishes();
            AnalyticsPie.getData().add(new PieChart.Data(dish.getDishName() + ": " + countOfDishes, countOfDishes));
        }

    }
}
