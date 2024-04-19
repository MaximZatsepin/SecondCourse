import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends JPanel implements KeyListener {
    private double arrowAngle = 400;
    private double rotationSpeed = Math.toRadians(5);
    private boolean clockwise = false;

    int panelX = 1300;
    int panelY = 700;
    int centerX = panelX / 2;
    int centerY = panelY / 2;
    int radius = 250;
    int minValue = 0;
    int maxValue = 160;
    int numberOfTicks = 17;
    int majorTickLength = 15;
    int minorTickLength = 10;
    int tickLabelDistance = 20;
    Font largeTickFont = new Font("Arial", Font.BOLD, 25);
    Font smallTickFont = new Font("Arial", Font.BOLD, 12);

    public Controller() {
        setSize(panelX, panelY);

        addKeyListener(this);
        setLayout(null);
        setFocusable(true);
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius , 2 * radius, 2 * radius);


        int innerRadius = (int) (radius * 1);


        int numberOfTicks = 160;


        double startAngle = Math.toRadians(-60);
        double endAngle = Math.toRadians(240);
        double angleRange = endAngle - startAngle;


        double angleIncrement = angleRange / (numberOfTicks - 1);


        for (int i = 0; i < numberOfTicks + 1; i++) {
            double angle = startAngle + i * angleIncrement;
            int x1 = (int) (centerX + innerRadius * Math.cos(angle));
            int y1 = (int) (centerY - innerRadius * Math.sin(angle));
            int x2, y2;


            int labelOffsetX = (int) (tickLabelDistance * -1 * Math.cos(angle));
            int labelOffsetY = (int) (-tickLabelDistance * -1 * Math.sin(angle));

            if (i % 10 == 0) {
                x2 = (int) (centerX + (innerRadius - majorTickLength * 2) * Math.cos(angle));
                y2 = (int) (centerY - (innerRadius - majorTickLength * 2) * Math.sin(angle));
                g2d.setStroke(new BasicStroke(4));

                if ((160 - i) % 20 == 0) {
                    g2d.setFont(largeTickFont);
                    String label = Integer.toString(160 - i);
                    FontMetrics fm = g2d.getFontMetrics();
                    int labelWidth = fm.stringWidth(label);
                    int labelHeight = fm.getHeight();
                    int labelX = (int) (x2 - labelWidth / 2) + labelOffsetX;
                    int labelY = (int) (y2 + labelHeight) + labelOffsetY - 15;
                    g2d.drawString(label, labelX, labelY);
                } else {
                    g2d.setFont(smallTickFont);
                    String label = Integer.toString(160 - i);
                    FontMetrics fm = g2d.getFontMetrics();
                    int labelWidth = fm.stringWidth(label);
                    int labelHeight = fm.getHeight();
                    int labelX = (int) (x2 - labelWidth / 2) + labelOffsetX + 5;
                    int labelY = (int) (y2 + labelHeight) + labelOffsetY - 15;
                    g2d.drawString(label, labelX, labelY);
                }

            } else if (i % 5 == 0) {
                x2 = (int) (centerX + (innerRadius - majorTickLength) * Math.cos(angle));
                y2 = (int) (centerY - (innerRadius - majorTickLength) * Math.sin(angle));
                g2d.setStroke(new BasicStroke(2));
            } else {
                x2 = (int) (centerX + (innerRadius - minorTickLength) * Math.cos(angle));
                y2 = (int) (centerY - (innerRadius - minorTickLength) * Math.sin(angle));
                g2d.setStroke(new BasicStroke(1));
                g2d.setFont(smallTickFont);
            }

            g2d.drawLine(x1, y1, x2, y2);
        }

        int arrowLength = radius - 20;
        int arrowHeadSize = 10;


        int arrowEndX = (int) (centerX + arrowLength * Math.cos(arrowAngle));
        int arrowEndY = (int) (centerY - arrowLength * Math.sin(arrowAngle));


        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(centerX, centerY, arrowEndX, arrowEndY);


        int[] arrowHeadX = {arrowEndX, (int) (arrowEndX - arrowHeadSize * Math.cos(arrowAngle - Math.PI / 8)),
                (int) (arrowEndX - arrowHeadSize * Math.cos(arrowAngle + Math.PI / 8))};
        int[] arrowHeadY = {arrowEndY, (int) (arrowEndY + arrowHeadSize * Math.sin(arrowAngle - Math.PI / 8)),
                (int) (arrowEndY + arrowHeadSize * Math.sin(arrowAngle + Math.PI / 8))};
        g2d.fillPolygon(arrowHeadX, arrowHeadY, 3);
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            clockwise = true;
            rotateArrow();
        } else if (code == KeyEvent.VK_S) {
            clockwise = false;
            rotateArrow();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            clockwise = false;
            rotateArrow();
        }
    }

    private void rotateArrow() {
        if (clockwise && arrowAngle > 100) {
            arrowAngle -= rotationSpeed;
        } else {
            arrowAngle += rotationSpeed;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spidometr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Controller());
        frame.pack();
        frame.setVisible(true);
    }
}
