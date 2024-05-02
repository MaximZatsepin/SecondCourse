
import javax.swing.JFrame;

public class Main extends JFrame{

    public Main() {

        add(new Clock());

        setSize(1300, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Main();
    }


}
