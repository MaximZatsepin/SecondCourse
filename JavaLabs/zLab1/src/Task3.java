import static java.lang.Math.*;

public class Task3 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Неверный аргумент!");
            return;
        }
        double[] array = Functions(Double.parseDouble(args[0]));
        System.out.println("Аргумент Y: " + array[0]);
        System.out.println("Аргумент F: " + array[1]);
    }

    public static double[] Functions(double x) {
        double[] array = new double[2];
        if (x <= -3) {
            array[0] = pow(x, 3) + 1;
            array[1] = exp(sin(x));
        } else if (-3 < x && x <= 4) {
            array[0] = x * (1 + pow(2, x));
            array[1] = pow(x, 4);
        } else {
            array[0] = tan(x);
            array[1] = pow(tan(x), (0.2));
        }
        return array;
    }
}
