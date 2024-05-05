package sample.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Animations.MoveMenu;
import sample.Animations.MoveMiddleButtons;
import sample.Animations.MoveMenu;
import sample.HandlerDataBase;
import sample.Dish;
import sample.Order;
import sample.Person;
import sample.Order;
import sample.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AdminButton;

    @FXML
    private AnchorPane ButtonsPane;

    @FXML
    private Button EmployerButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button MainButton;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Text MainWelcomeText;

    @FXML
    private Button MiddleButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private AnchorPane ProfilePane;

    @FXML
    private Button OrderButton;

    @FXML
    private ChoiceBox<String> OrderChoiseDish;

    @FXML
    private ChoiceBox<String> OrderChoiceTime;

    @FXML
    private Button OrderСonfirmationDish;

    @FXML
    private AnchorPane OrderPage;

    @FXML
    private Button OrderPayButton;

    @FXML
    private Text OrderPriceText;

    @FXML
    private Text OrderText;

    @FXML
    private Button profile_AddMoneyButton;

    @FXML
    private Button profile_ApplyDataButton;

    @FXML
    private TextArea profile_DataText;

    @FXML
    private TextField profile_MoneyText;

    @FXML
    private TextField profile_NameText;

    @FXML
    private TextField profile_SurnameText;

    @FXML
    void onButtonPaneMouseEntered(MouseEvent event) { MoveMenu.moveDown(ButtonsPane); }

    @FXML
    void onButtonPaneMouseExited(MouseEvent event) { MoveMenu.moveUp(ButtonsPane); }

    public static Person current_user;
    public static String nameOfCurrentDish;
    public static String mainIngregientOfCurrentDish;
    public static Order currentOrder;
    public static int currentPrice;
    public static int currentCountDishes;

    private static String middleButtonsFlag = "up";

    private ObservableList<String> dishes;

    @FXML
    void initialize() {
        initSettings();

        MainWelcomeText.setText(String.format("Добро пожаловать, %s!\nВы вошли как %s.",current_user.getFullName(), current_user.getRole()));
        MainButton.setOnAction(event -> onMainButtonClick());
        ProfileButton.setOnAction(event -> onProfileButtonClick());
        ExitButton.setOnAction(event -> onExitButtonClick());
        MiddleButton.setOnAction(event -> onMiddleButtonClick());
        EmployerButton.setOnAction(event -> onEmployerButtonClick());
        AdminButton.setOnAction(event -> onAdminButtonClick());
        OrderButton.setOnAction(event -> onOrderButtonClick());
        OrderPayButton.setOnAction(event -> {onOrderPayButtonClick(); orderUpdate();});

        profile_ApplyDataButton.setOnAction(event -> profileOnUpdateDataButtonClick());
        profile_AddMoneyButton.setOnAction(event -> profileOnAddMoneyButtonClick());

        OrderСonfirmationDish.setOnAction(event -> onOrderСonfirmationDishClick());

        assert AdminButton != null : "fx:id=\"AdminButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ButtonsPane != null : "fx:id=\"ButtonsPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert EmployerButton != null : "fx:id=\"EmployerButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MainButton != null : "fx:id=\"MainButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MainPane != null : "fx:id=\"MainPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MainWelcomeText != null : "fx:id=\"MainWelcomeText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MiddleButton != null : "fx:id=\"MiddleButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ProfileButton != null : "fx:id=\"ProfileButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ProfilePane != null : "fx:id=\"ProfilePane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert OrderChoiseDish != null : "fx:id=\"OrderChoiseDish\" was not injected: check your FXML file 'MainWindow.fxml'.";
//        assert OrderChoiseSeat != null : "fx:id=\"OrderChoiseSeat\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert OrderPage != null : "fx:id=\"OrderPage\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert OrderPayButton != null : "fx:id=\"OrderPayButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert OrderPriceText != null : "fx:id=\"OrderPriceText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert OrderText != null : "fx:id=\"OrderText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_AddMoneyButton != null : "fx:id=\"profile_AddMoneyButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_ApplyDataButton != null : "fx:id=\"profile_ApplyDataButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_DataText != null : "fx:id=\"profile_DataText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_MoneyText != null : "fx:id=\"profile_MoneyText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_NameText != null : "fx:id=\"profile_NameText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_SurnameText != null : "fx:id=\"profile_SurnameText\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

    void initSettings(){
        ButtonsPane.setLayoutY(-150);
        current_user = LoginController.current_user;
        if(current_user.getRole().equals("guest")) showEmployerButtons(false);
        onMainButtonClick();

        EmployerButton.setDisable(true);
        AdminButton.setDisable(true);
    }

    public void openNewScene(String window) {
        Stage currentStage = (Stage) MainPane.getScene().getWindow();
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
    }

    public void openNewSceneWithoutClosing(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL); // Установка модального режима
            newStage.setScene(new Scene(root));
            newStage.showAndWait(); // Показать окно и ждать, пока оно не будет закрыто
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showEmployerButtons(boolean flag){
        MiddleButton.setVisible(flag);
        EmployerButton.setVisible(flag);
        AdminButton.setVisible(flag);

    }

    void paintAllButtonsBlue(){
        MainButton.setStyle("-fx-background-color:#bf615a; -fx-background-radius: 15;");
        OrderButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        MiddleButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        ProfileButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
    }

    void hideAllPanes(){
        MainPane.setVisible(false);
        ProfilePane.setVisible(false);
        OrderPage.setVisible(false);
    }

    public void onMainButtonClick(){
        MoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
        middleButtonsFlag = "up";

        paintAllButtonsBlue();
        hideAllPanes();
        MainButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        MainPane.setVisible(true);
        MainWelcomeText.setText(String.format("Добро пожаловать, %s!\nВы вошли как %s.",current_user.getFullName(), current_user.getRole()));

    }

    public void onProfileButtonClick(){
        MoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
        middleButtonsFlag = "up";

        paintAllButtonsBlue();
        hideAllPanes();
        ProfileButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        ProfilePane.setVisible(true);
        profileUpdate();

    }

    public void onExitButtonClick(){
        current_user = null;
        nameOfCurrentDish = null;
        mainIngregientOfCurrentDish = null;
        currentOrder = null;
        currentPrice = 0;

        openNewScene("/sample/Templates/Login.fxml");
    }

    public void onMiddleButtonClick(){
        if (middleButtonsFlag.equals("up")) {
            paintAllButtonsBlue();
            MiddleButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
            MoveMiddleButtons.moveMiddleButtonsDown(EmployerButton,AdminButton);
            middleButtonsFlag = "down";
        }else{
            paintAllButtonsBlue();
            MoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
            middleButtonsFlag = "up";
        }
    }

    public void onEmployerButtonClick(){
        openNewSceneWithoutClosing("/sample/Templates/ManagerWindow.fxml");
    }

    public void onOrderButtonClick(){
        MoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
        middleButtonsFlag = "up";

        paintAllButtonsBlue();
        hideAllPanes();
        OrderButton.setStyle("-fx-background-color: #bf615a; -fx-background-radius: 15;");
        OrderPage.setVisible(true);

        orderUpdate();
    }

    public void onAdminButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        if (!current_user.getRole().equals("admin")){
            alert.setContentText("Отказано в доступе!");
            alert.showAndWait();
            return;
        }
        openNewSceneWithoutClosing("/sample/Templates/AdminWindow.fxml");}

    // PROFILE FUNC'S

    public void profileUpdate(){
        profile_DataText.setText(String.format("Логин: %s\nИмя: %s\nФамилия: %s\nБаланс: %.2fруб\nРоль: %s\n",
                current_user.getLogin(),
                current_user.getName(),current_user.getSurname(),
                current_user.getMoney(), current_user.getRole()));
    }

    public void profileOnUpdateDataButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Информация обновлена!");
        alert.showAndWait();

        String name = profile_NameText.getText();
        if(name.equals("")) name = current_user.getName();
        String surname = profile_SurnameText.getText();
        if(surname.equals("")) surname = current_user.getSurname();

        current_user.setName(name);
        current_user.setSurname(surname);

        try { new HandlerDataBase().updateUserByLogin(current_user); }
        catch (SQLException e) { e.printStackTrace(); }
        profileUpdate();

    }

    public void profileOnAddMoneyButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        double money = Double.parseDouble(profile_MoneyText.getText());
        if(money <= 0) {
            alert.setContentText("Операция отклонена!");
            alert.showAndWait();
            return;
        }
        alert.setContentText("Операция выполнена!");
        alert.showAndWait();

        current_user.setMoney(current_user.getMoney() + money);

        try { new HandlerDataBase().updateUserByLogin(current_user); }
        catch (SQLException e) { e.printStackTrace(); }
        profileUpdate();
    }



    public void onOrderСonfirmationDishClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String chosenDish = OrderChoiseDish.getValue();
        if (chosenDish.equals("")) {
            alert.setContentText("Выберите блюдо!");
            alert.showAndWait();
            return;
        }
        nameOfCurrentDish = chosenDish.split(" ")[0];

        if(current_user.getFullName().equals(current_user.getLogin())){
            alert.setContentText("Для продолжения заполните данные аккаунта!");
            alert.showAndWait();
            return;
        }

        if (new HandlerDataBase().getCountOfDishByDishName(nameOfCurrentDish) == 0){
            alert.setContentText("Данного блюда нет в наличии");
            alert.showAndWait();
            return;
        }
        mainIngregientOfCurrentDish = new HandlerDataBase().getMainIngredientByDishName(nameOfCurrentDish);

        OrderText.setText(String.format("ФИО:  %s\nБлюдо:  %s\nГлавный ингредиент: %s\nКатегория: %s\nВремя приготовления: %s",
                current_user.getFullName(),nameOfCurrentDish,mainIngregientOfCurrentDish,
                new HandlerDataBase().getCategoryByDishName(nameOfCurrentDish),
                new HandlerDataBase().getCookingTimeByDishName(nameOfCurrentDish)));

        currentPrice = new HandlerDataBase().getPriceByDishName(nameOfCurrentDish);

        OrderPriceText.setText(String.format("Цена: %d рублей",currentPrice));

        currentOrder = new Order(current_user.getUserId(),nameOfCurrentDish,
                current_user.getName(),current_user.getSurname(),mainIngregientOfCurrentDish,currentPrice);
    }

    public static void onOrderPayButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        if(currentPrice == 0){
            alert.setContentText("Билет не выбран!");
            alert.showAndWait();
            return;
        }

        if(currentPrice > current_user.getMoney()){
            alert.setContentText("Недостаточно средств!");
            alert.showAndWait();
            return;
        }

        System.out.println(nameOfCurrentDish.replace(" ",""));
        Dish dish = new HandlerDataBase().getDishByDishName(nameOfCurrentDish.replace(" ",""));

        if(dish == null){
            alert.setContentText("Ошибка!");
            alert.showAndWait();
            return;
        }

        alert.setContentText("Покупка завершена!");
        alert.showAndWait();

        current_user.setMoney(current_user.getMoney() - currentPrice);
        try { new HandlerDataBase().updateUserByLogin(current_user); } catch (SQLException e) { e.printStackTrace(); }

        new HandlerDataBase().addOrder(currentOrder);

        dish.setCountDishes(dish.getCountDishes() - 1);
        new HandlerDataBase().updateDish(dish);

        currentCountDishes = new HandlerDataBase().getCountDishesByDishName(nameOfCurrentDish);

        if (currentCountDishes == 0){
            Dish curDish = new HandlerDataBase().getDishByDishName(nameOfCurrentDish);
            if (curDish != null) {
                curDish.setStatus("Не в наличии");
                new HandlerDataBase().updateDish(curDish);
            }
        }

    }

    public void orderUpdate(){
        List<Dish> all_dishes = new HandlerDataBase().getAllDish();
        List<String> all_dishes1 = new ArrayList<String>();
        for(Dish dish : all_dishes){
            all_dishes1.add(String.format("%s | %s | %s ",dish.getDishName(), dish.getMainIngredient(), dish.getCookingTime()));
        }
        dishes = FXCollections.observableArrayList(all_dishes1);
        OrderChoiseDish.setItems(dishes);
        OrderChoiseDish.setValue("");
        OrderText.setText("ФИО: -\n" +
                "Блюдо: -\n" +
                "главный ингредиент: -\n" +
                "Категория: -\n" +
                "Время приготовления: -");
        OrderPriceText.setText("Цена: ... рублей");
    }
}
