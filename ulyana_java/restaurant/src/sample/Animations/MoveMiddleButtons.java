package sample.Animations;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class MoveMiddleButtons {
    public static void moveMiddleButtonsDown(Button EmployerButton, Button AdminButton){
        TranslateTransition one = new TranslateTransition(Duration.seconds(0.5), EmployerButton);
        one.setToY(85);
        one.play();
        TranslateTransition two = new TranslateTransition(Duration.seconds(0.5), AdminButton);
        two.setToY(85);
        two.play();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), EmployerButton);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(0.5), AdminButton);
        fadeIn1.setFromValue(0.0);
        fadeIn1.setToValue(1.0);
        fadeIn1.play();

        EmployerButton.setDisable(false);
        AdminButton.setDisable(false);
    }

    public static void moveMiddleButtonsUp(Button EmployerButton, Button AdminButton){
        TranslateTransition one = new TranslateTransition(Duration.seconds(0.5), EmployerButton);
        one.setToY(0);
        one.play();
        TranslateTransition two = new TranslateTransition(Duration.seconds(0.5), AdminButton);
        two.setToY(0);
        two.play();

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), EmployerButton);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();

        FadeTransition fadeOut1 = new FadeTransition(Duration.seconds(0.5), AdminButton);
        fadeOut1.setFromValue(1.0);
        fadeOut1.setToValue(0.0);
        fadeOut1.play();

        EmployerButton.setDisable(true);
        AdminButton.setDisable(true);
    }
}
