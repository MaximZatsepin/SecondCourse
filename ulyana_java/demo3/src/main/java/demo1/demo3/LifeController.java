package demo1.demo3;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;


public class LifeController extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, KeyListener {
    private JButton randomButton;
    private JButton clearButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton personalButton;

    int panelX = 1300;
    int panelY = 700;

    int sizeCell = 10;
    int width = panelX / sizeCell;
    int height = panelY / sizeCell;
    int[][] lifeCells = new int[width][height];
    int[][] beforeLifeCells = new int[width][height];
    Timer time;

    boolean start = true;
    int initial = -1;

    public LifeController(){
        setSize(panelX, panelY);
        setLayout(null);

        randomButton = new JButton("Random generation");
        randomButton.addActionListener(this);
        randomButton.setBounds(10, 10, 150, 30);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(180, 10, 100, 30);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setBounds(300, 10, 100, 30);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        stopButton.setBounds(420, 10, 100, 30);

        personalButton = new JButton("Personal generation");
        personalButton.addActionListener(this);
        personalButton.setBounds(540, 10, 150, 30);



        add(randomButton);
        add(clearButton);
        add(startButton);
        add(stopButton);
        add(personalButton);

        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);

        setBackground(Color.BLACK);
        time = new Timer(80, this);
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        grid(g);
        display(g);
    }

    private void grid(Graphics g){
        for (int i = 0; i < lifeCells.length; i++){
            g.drawLine(0, i*sizeCell,panelX, i*sizeCell );
            g.drawLine(i*sizeCell, 0, i*sizeCell, panelY );
        }
    }

    private void startGenerate(){

        for (int x = 0; x < lifeCells.length; x++){
            for (int y = 0; y < (height); y++){
                if ((int)(Math.random() * 5) == 0 ){
                    beforeLifeCells[x][y] = 1;
                }
            }
        }
    }



    private void display (Graphics g) {
        g.setColor(Color.GREEN);

        arrayCopy();

        for (int x = 0; x < lifeCells.length; x++){
            for (int y = 0; y < (height); y++){
                if (lifeCells[x][y] == 1){
                    g.fillRect(x*sizeCell, y*sizeCell, sizeCell, sizeCell);

                }
            }
        }
    }


    private int check(int x, int y){
        int alive = 0;

        alive += lifeCells[(x + width - 1) % width][(y + height - 1) % height];
        alive += lifeCells[(x + width) % width][(y + height - 1) % height];
        alive += lifeCells[(x + width + 1) % width][(y + height - 1) % height];
        alive += lifeCells[(x + width - 1) % width][(y + height) % height];
        alive += lifeCells[(x + width + 1) % width][(y + height) % height];
        alive += lifeCells[(x + width - 1) % width][(y + height + 1) % height];
        alive += lifeCells[(x + width) % width][(y + height + 1) % height];
        alive += lifeCells[(x + width + 1) % width][(y + height + 1) % height];

        return alive;
    }

    public void arrayCopy(){
        for (int x = 0; x < lifeCells.length; x++){
            for (int y = 0; y < (height); y++){
                lifeCells[x][y] = beforeLifeCells[x][y];
            }
        }
    }

    public void actionPerformed(ActionEvent e){

        int alive;

        for (int x = 0; x < lifeCells.length; x++){
            for (int y = 0; y < (height) - 1; y++){
                alive =  check(x, y);
                if (alive == 3){
                    beforeLifeCells[x][y] = 1;

                } else if (alive == 2 && lifeCells[x][y] == 1) {
                    beforeLifeCells[x][y] = 1;
                }
                else {
                    beforeLifeCells[x][y] = 0;
                }
            }
        }

        if (e.getSource() == randomButton) {
            startGenerate();
            time.start();
        } else if (e.getSource() == clearButton) {
            clear();
            time.stop();
        }else if (e.getSource() == startButton) {
            time.start();
        }else if (e.getSource() == stopButton) {
            time.stop();
        }else if (e.getSource() == personalButton) {
            clear();
            time.stop();
        }

        repaint();
    }

    public void mouseDragged (MouseEvent e){
        int x = e.getX() / sizeCell;
        int y = e.getY() / sizeCell;
        if (lifeCells[x][y] == 0 && initial == 0){
            beforeLifeCells[x][y] = 1;
        } else if (lifeCells[x][y] == 1 && initial == 1) {
            beforeLifeCells[x][y] = 0;
        }
        repaint();
    }

    public void mouseMoved (MouseEvent e){

    }

    public void mouseClicked(MouseEvent e){

    }

    public void mousePressed(MouseEvent e){
        int x = e.getX() / sizeCell;
        int y = e.getY() / sizeCell;

        if (lifeCells[x][y] == 0) {
            initial = 0;
        }else{initial = 1;}
        repaint();
    }

    public void mouseReleased(MouseEvent e){
        initial = -1;

    }

    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e){

    }





    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if (code == e.VK_R){
            startGenerate();
            time.start();
        } else if (code == e.VK_C) {
            clear();
            time.stop();
        } else if (code == e.VK_S) {
            time.start();
        }else if (code == e.VK_A) {
            time.stop();
        }
        repaint();

    }

    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

    private void clear(){
        for (int x = 0; x < lifeCells.length; x++){
            for (int y = 0; y < (height); y++){
                beforeLifeCells[x][y] = 0;
            }
        }
    }
}
