
import javax.swing.*;

public class Main extends JFrame{

    public Main() {

        add(new Spidometr());

        setSize(1300, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Main();
    }


}
