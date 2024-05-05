package sample.Animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private final Timeline timeline;

    public Shake(Node node) {
        this.timeline = new Timeline(
                new KeyFrame(Duration.millis(0),    new KeyValue(node.translateXProperty(),   0)),
                new KeyFrame(Duration.millis(100),  new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(200),  new KeyValue(node.translateXProperty(),  10)),
                new KeyFrame(Duration.millis(300),  new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(400),  new KeyValue(node.translateXProperty(),  10)),
                new KeyFrame(Duration.millis(500),  new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(600),  new KeyValue(node.translateXProperty(),  10)),
                new KeyFrame(Duration.millis(700),  new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(800),  new KeyValue(node.translateXProperty(),  10)),
                new KeyFrame(Duration.millis(900),  new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(1000), new KeyValue(node.translateXProperty(),   0))
        );

        this.timeline.setDelay(Duration.seconds(0.1));
    }

    public Shake(Timeline timeline) {
        this.timeline = timeline;
    }

    public void play() {
        timeline.play();
    }
}
