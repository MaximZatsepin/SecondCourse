
import javax.swing.JPanel;
import java.awt.*;


public class Logo extends JPanel {

    int panelX = 1300;
    int panelY = 700;

    public Logo(){
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

        g2d.setStroke(new BasicStroke(20));

        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, width, height);


        int[] xPoints = {550, 600, 575};
        int[] yPoints = {290, 290, 250};
        int nPoints = 3;

        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, nPoints);

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, nPoints);


        int[] xPoints_2 = {550, 600, 575};
        int[] yPoints_2 = {320, 320, 260};

        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(xPoints_2, yPoints_2, nPoints);


        String text = "I N F I N I T";
        Font font = new Font("Arial", Font.BOLD, 40);

        g2d.setFont(font);

        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 480, 370);
    }
}
