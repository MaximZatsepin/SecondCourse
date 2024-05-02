
import javax.swing.JPanel;
import java.awt.*;


public class Logotip extends JPanel {

    int panelX = 1300;
    int panelY = 700;

    public Logotip(){
        setSize(panelX, panelY);
        setLayout(null);
        setFocusable(true);
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int x = 500;
        int width = 150;
        int y = 200;
        int height = 100;

        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Установка стиля закругления

//        g2d.setColor(Color.GRAY);
//    g2d.drawOval(x, y, width, height);


        int[] xPoints = {525, 525, 590, 657, 657, 590, 525};
        int[] yPoints = {200, 210, 175, 210, 199, 150, 200};
        int nPoints = 7;

        g2d.setColor(Color.GRAY);
        g2d.drawPolygon(xPoints, yPoints, nPoints);

        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(xPoints, yPoints, nPoints);

        int startAngle = 70; // Начальный угол в градусах
        int arcAngle = 40; // Длина дуги в градусах

        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Установка стиля закругления
        g2d.setColor(Color.BLACK);
        g2d.drawArc(490, 175, 200, 200, startAngle, arcAngle);

        g2d.drawArc(550, 178, 100, 100, 100, 30);
        g2d.drawArc(530, 178, 100, 100, 50, 30);

        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Установка стиля закругления
        int[] xPoints2 = {590, 590};
        int[] yPoints2 = {176, 177};
        int nPoints2 = 2;

        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints2, yPoints2, nPoints2);

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints2, yPoints2, nPoints2);




        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Установка стиля закругления

//        g2d.setColor(Color.GRAY);
//    g2d.drawOval(x, y, width, height);


        int[] xPoints3 = {525, 525, 590, 657, 657, 590, 525};
        int[] yPoints3 = {250, 260, 225, 260, 249, 210, 250};
        int nPoints3 = 7;

        g2d.setColor(Color.GRAY);
        g2d.drawPolygon(xPoints3, yPoints3, nPoints3);

        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(xPoints3, yPoints3, nPoints3);

        int startAngle2 = 70; // Начальный угол в градусах
        int arcAngle2 = 40; // Длина дуги в градусах

        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Установка стиля закругления
        g2d.setColor(Color.BLACK);
        g2d.drawArc(490, 225, 200, 200, startAngle, arcAngle);

        g2d.drawArc(550, 228, 100, 100, 100, 30);
        g2d.drawArc(530, 228, 100, 100, 50, 30);

        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Установка стиля закругления
        int[] xPoints4 = {590, 590};
        int[] yPoints4 = {226, 227};
        int nPoints4 = 2;

        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints4, yPoints4, nPoints4);

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints4, yPoints4, nPoints4);

        String text = "C I T R O Ё";
        Font font = new Font("Arial", Font.BOLD, 40);

        g2d.setFont(font);

        g2d.setColor(Color.RED);
        g2d.drawString(text, 470, 320);

        String text2 = "n";
        font = new Font("Arial", Font.BOLD, 55);
        g2d.setFont(font);
        g2d.drawString(text2, 690, 320);
    }
}
