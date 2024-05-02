import javax.swing.*;
import java.awt.*;

public class Clock extends JPanel {
    private double hourAngle = 90; // Угол для часовой стрелки
    private double minuteAngle = 90; // Угол для минутной стрелки
    private double secondAngle = 90; // Угол для секундной стрелки
    private int timeElapsed = 0; // Переменная для отслеживания времени
    private final int panelX = 600;
    private final int panelY = 600;
    private final int centerX = panelX / 2;
    private final int centerY = panelY / 2;
    private final int radius = 250;
    private final int majorTickLength = 15;
    private final int minorTickLength = 10;
    private final int tickLabelDistance = 20;
    private final Font largeTickFont = new Font("Arial", Font.BOLD, 25);
    private final Font smallTickFont = new Font("Arial", Font.BOLD, 12);

    Timer timer;

    public Clock() {
        setSize(panelX, panelY);
        setBackground(Color.WHITE);

        timer = new Timer(1, e -> {
            timeElapsed++; // Увеличиваем время каждую секунду
            // Обновляем углы стрелок
            secondAngle = 90 - 6 * timeElapsed; // Секундная стрелка - 6 градусов в секунду
            if (timeElapsed % 60 == 0) {
                minuteAngle = 90 - 6 * (timeElapsed / 60); // Минутная стрелка - 6 градусов в минуту
            }
            if (timeElapsed % 3600 == 0) {
                hourAngle = 90 - 30 * (timeElapsed / 3600); // Часовая стрелка - 30 градусов в час
            }
            repaint(); // Перерисовываем компонент для отображения изменений
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Рисуем циферблат
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        g2d.setStroke(new BasicStroke(50));
        g2d.setColor(Color.CYAN);
        g2d.drawOval(centerX - radius + 26, centerY - radius + 27, 448, 448);
        g2d.setColor(Color.BLACK);

        // Рисуем деления
        int innerRadius = (int) (radius * 0.9);
        for (int i = 0; i < 60; i++) {
            double angle = Math.toRadians(90 - 6 * i);
            int x1 = (int) (centerX + innerRadius * Math.cos(angle));
            int y1 = (int) (centerY - innerRadius * Math.sin(angle));
            int x2, y2;

            if (i % 5 == 0) {
                x2 = (int) (centerX + (innerRadius - majorTickLength) * Math.cos(angle));
                y2 = (int) (centerY - (innerRadius - majorTickLength) * Math.sin(angle));
                g2d.setStroke(new BasicStroke(2));
                g2d.setFont(largeTickFont);
                String label = Integer.toString(i / 5 == 0 ? 12 : i / 5);
                FontMetrics fm = g2d.getFontMetrics();
                int labelWidth = fm.stringWidth(label);
                int labelHeight = fm.getHeight();
                int labelX = (int) (centerX + (innerRadius - tickLabelDistance) * Math.cos(angle)) - labelWidth / 4;
                int labelY = (int) (centerY - (innerRadius - tickLabelDistance) * Math.sin(angle)) + labelHeight / 6;
                g2d.drawString(label, labelX, labelY);
            } else {
                x2 = (int) (centerX + (innerRadius - minorTickLength) * Math.cos(angle));
                y2 = (int) (centerY - (innerRadius - minorTickLength) * Math.sin(angle));
                g2d.setStroke(new BasicStroke(1));
                g2d.setFont(smallTickFont);
            }

            g2d.drawLine(x1, y1, x2, y2);
        }

        // Рисуем стрелки
        drawHand(g2d, centerX, centerY, secondAngle, radius - 20, 2);
        drawHand(g2d, centerX, centerY, minuteAngle, radius - 50, 3);
        drawHand(g2d, centerX, centerY, hourAngle, radius - 100, 4);
    }

    // Метод для рисования стрелки
    private void drawHand(Graphics2D g2d, int x, int y, double angle, int length, int thickness) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(thickness));
        int endX = (int) (x + length * Math.cos(Math.toRadians(angle)));
        int endY = (int) (y - length * Math.sin(Math.toRadians(angle)));
        g2d.drawLine(x, y, endX, endY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Clock());
        frame.pack();
        frame.setVisible(true);
    }
}
