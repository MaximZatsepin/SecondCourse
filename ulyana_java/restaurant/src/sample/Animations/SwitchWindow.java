package sample.Animations;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SwitchWindow {
    public static void switchToRegistration(AnchorPane loginPane, AnchorPane registrationPane) {

        // Анимация сдвига LoginPane влево
        TranslateTransition LoginToLeft = new TranslateTransition(Duration.seconds(0.5), loginPane);
        LoginToLeft.setToX(-500);
        LoginToLeft.play();
        // Анимация пропадания LoginPane
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), loginPane);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();

        loginPane.setDisable(true);

        registrationPane.setTranslateX(500); // начальное положение за пределами экрана
        registrationPane.setDisable(false);

        // Анимация сдвига RegistraionPane влево
        TranslateTransition RegToLeft = new TranslateTransition(Duration.seconds(0.5),registrationPane);
        RegToLeft.setToX(0);
        RegToLeft.play();

        // Анимация появления RegistrationPane с изменением прозрачности
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), registrationPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }

    public static void switchToLogin(AnchorPane loginPane, AnchorPane registrationPane) {

        // Анимация сдвига RegPane вправо
        TranslateTransition RegToRight = new TranslateTransition(Duration.seconds(0.5), registrationPane);
        RegToRight.setToX(500);
        RegToRight.play();
        // Анимация пропадания RegPane
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), registrationPane);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();

        registrationPane.setDisable(true);

        loginPane.setTranslateX(-500); // начальное положение за пределами экрана
        loginPane.setDisable(false);

        // Анимация сдвига LoginPane вправо
        TranslateTransition LogToRight = new TranslateTransition(Duration.seconds(0.5),loginPane);
        LogToRight.setToX(0);
        LogToRight.play();

        // Анимация появления LoginPane с изменением прозрачности
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), loginPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
