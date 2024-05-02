
import javax.swing.JPanel;
import java.awt.*;

public class Draw extends JPanel {

    int panelX = 1300;
    int panelY = 700;

    public Draw(){
        setSize(panelX, panelY);
        setLayout(null);
        setFocusable(true);
        setBackground(Color.WHITE);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int[] xPoints_1 = {500, 520, 540, 520}; // x-координаты вершин треугольника
        int[] yPoints_1 = {300, 300, 250, 250};   // y-координаты вершин треугольника
        int nPoints = 4; // Количество вершин

        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(xPoints_1, yPoints_1, nPoints);



        int[] xPoints_2 = {520, 540, 560, 540}; // x-координаты вершин треугольника
        int[] yPoints_2 = {250, 250, 300, 300};   // y-координаты вершин треугольника


        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(xPoints_2, yPoints_2, nPoints);

        int[] xPoints_3 = {500, 520, 540, 520}; // x-координаты вершин треугольника
        int[] yPoints_3 = {300, 300, 350, 350};   // y-координаты вершин треугольника


        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints_3, yPoints_3, nPoints);



        int[] xPoints_4 = {540, 520, 540, 560}; // x-координаты вершин треугольника
        int[] yPoints_4 = {350, 350, 300, 300};   // y-координаты вершин треугольника


        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints_4, yPoints_4, nPoints);

        String text = "RENAULT";
        Font font = new Font("Arial", Font.BOLD, 30); // Создаем жирный шрифт размером 20

        // Устанавливаем шрифт
        g2d.setFont(font);


        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 465, 390); // Рисуем текст в точке (100, 100)


    }

}
