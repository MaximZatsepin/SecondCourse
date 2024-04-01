package kursach.kursach;





import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Enterance_controller {
    @FXML private ImageView imageView;
    @FXML private TextField textField;
    @FXML private TextField fontSizeField;
    @FXML private TextField xCoordField;
    @FXML private TextField yCoordField;
    @FXML private Button addButton;
    @FXML private Button processAllButton;
    @FXML private Label messageLabel;

    private File imageFile;

    public void initialize() {
        addButton.setDisable(true);
        processAllButton.setDisable(true);
    }

    public void loadImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        imageFile = fileChooser.showOpenDialog(null);
        if (imageFile != null) {
            Image image = new Image(imageFile.toURI().toString());
            imageView.setImage(image);
            addButton.setDisable(false);
            processAllButton.setDisable(false);
        }
    }

    public void addLabel(ActionEvent actionEvent) {
        String text = textField.getText();
        double fontSize = Double.parseDouble(fontSizeField.getText());
        double x = Double.parseDouble(xCoordField.getText());
        double y = Double.parseDouble(yCoordField.getText());

        javafx.scene.text.Text label = new javafx.scene.text.Text(text);
        label.setFont(new javafx.scene.text.Font(fontSize));
        label.setFill(Color.BLACK); // You can customize the color as needed

        label.setLayoutX(x);
        label.setLayoutY(y);

        imageView.getParent().getChildrenUnmodifiable().add(label);
    }

    public void processAllImages(ActionEvent actionEvent) {
        if (imageFile != null) {
            File directory = imageFile.getParentFile();
            processImagesInDirectory(directory);
            messageLabel.setText("All images processed successfully.");
        } else {
            messageLabel.setText("No image loaded.");
        }
    }

    private void processImagesInDirectory(File directory) {
        try {
            Files.walk(directory.toPath())
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".png")
                            || path.toString().toLowerCase().endsWith(".jpg")
                            || path.toString().toLowerCase().endsWith(".gif"))
                    .forEach(this::processImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processImageFile(Path path) {
        // Process each image file here
        // You can reuse the code for adding label from the addLabel method
    }
}
