package demo1.demo3;

import javax.swing.JFrame;

public class Main extends JFrame{

    public Main() {

        add(new LifeController());

        setSize(1300, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Main();
    }


}
