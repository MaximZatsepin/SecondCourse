package sample.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MoveMenu {
    public static void moveUp(AnchorPane buttonsPane){
        TranslateTransition RegToRight = new TranslateTransition(Duration.seconds(0.5), buttonsPane);
        RegToRight.setToY(0);
        RegToRight.play();
    }

    public static void moveDown(AnchorPane buttonsPane){
        TranslateTransition RegToRight = new TranslateTransition(Duration.seconds(0.5), buttonsPane);
        RegToRight.setToY(100);
        RegToRight.play();
    }
}
