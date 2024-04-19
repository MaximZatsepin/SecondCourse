package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TreeItem;

import java.time.LocalDate;

public class Controller {
    @FXML
    private TreeTableView<Person> treeTableView;
    @FXML
    private TreeTableColumn<Person, String> firstNameColumn;
    @FXML
    private TreeTableColumn<Person, String> lastNameColumn;
    @FXML
    private TreeTableColumn<Person, LocalDate> birthDateColumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker birthDatePicker;

    public void initialize() {
        // Создаем корень дерева и устанавливаем его в TreeTableView
        TreeItem<Person> root = new TreeItem<>(new Person("root", "-", LocalDate.of(2000, 1, 1)));
        treeTableView.setRoot(root);

        // Устанавливаем фабрики значений для столбцов
        firstNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastName"));
        birthDateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthDate"));
    }


    public void addPerson(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        LocalDate birthDate = birthDatePicker.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || birthDate == null) {
            // Если какое-то из полей пустое, вы можете добавить обработку ошибки или просто вернуться
            return;
        }

        // Получаем выделенный элемент
        TreeItem<Person> selectedItem = treeTableView.getSelectionModel().getSelectedItem();

        // Если ничего не выделено или выделен корень, добавляем в корень
        if (selectedItem == null || selectedItem == treeTableView.getRoot()) {
            addPersonToRoot(firstName, lastName, birthDate);
        } else {
            // Добавляем нового человека как дочерний элемент выделенного элемента
            selectedItem.getChildren().add(new TreeItem<>(new Person(firstName, lastName, birthDate)));
        }

        // Очистим поля ввода после добавления человека
        firstNameField.clear();
        lastNameField.clear();
        birthDatePicker.setValue(null);
    }

    // Метод для добавления нового человека в корень дерева
    private void addPersonToRoot(String firstName, String lastName, LocalDate birthDate) {
        TreeItem<Person> root = treeTableView.getRoot();
        if (root == null) {
            root = new TreeItem<>(new Person(firstName, lastName, birthDate));
            treeTableView.setRoot(root);
        } else {
            root.getChildren().add(new TreeItem<>(new Person(firstName, lastName, birthDate)));
        }
    }



    public void deletePerson(ActionEvent event) {
        TreeItem<Person> selectedItem = treeTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem != treeTableView.getRoot()) {
            selectedItem.getParent().getChildren().remove(selectedItem);
        }
    }


    // Внутренний класс для представления человека
    public static class Person {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        public Person(String firstName, String lastName, LocalDate birthDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }
    }
}
