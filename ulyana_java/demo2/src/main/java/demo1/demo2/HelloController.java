package demo1.demo2;



import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HelloController {

    private static final int CELL_SIZE = 10;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    @FXML
    private Canvas canvas;

    private boolean[][] cells = new boolean[WIDTH][HEIGHT];
    private boolean[][] nextGeneration = new boolean[WIDTH][HEIGHT];

    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initialize cells randomly
        randomizeCells();

        new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100_000_000) { // Update every 100 milliseconds
                    lastUpdate = now;
                    update();
                    draw(gc);
                }
            }
        }.start();
    }

    private void randomizeCells() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                cells[x][y] = Math.random() < 0.5;
            }
        }
    }

    private void update() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int neighbors = countNeighbors(x, y);
                if (cells[x][y]) {
                    nextGeneration[x][y] = (neighbors == 2 || neighbors == 3);
                } else {
                    nextGeneration[x][y] = (neighbors == 3);
                }
            }
        }
        // Copy next generation back to current generation
        for (int x = 0; x < WIDTH; x++) {
            System.arraycopy(nextGeneration[x], 0, cells[x], 0, HEIGHT);
        }
    }

    private int countNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx >= 0 && nx < WIDTH && ny >= 0 && ny < HEIGHT && !(i == 0 && j == 0) && cells[nx][ny]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);
        gc.setFill(Color.BLACK);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (cells[x][y]) {
                    gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
}
