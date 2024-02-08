// import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        //for(int i = 0; i < args.length; i++) System.out.println(args[i]);

        Point3d p1 = new Point3d(Double.parseDouble(args[0]),
                                 Double.parseDouble(args[1]),
                                 Double.parseDouble(args[2]));
        Point3d p2 = new Point3d(Double.parseDouble(args[3]),
                                 Double.parseDouble(args[4]),
                                 Double.parseDouble(args[5]));
        Point3d p3 = new Point3d(Double.parseDouble(args[6]),
                                 Double.parseDouble(args[7]),
                                 Double.parseDouble(args[8]));
        if(p1.equal2point(p2) || p2.equal2point(p3) || p3.equal2point(p1)) {
            System.out.println("Невозможно вычислить площадь: точки равны");
            return;
        }
        System.out.print("Площадь треугольника равна ");
        System.out.println(computeArea(p1,p2,p3));

    }
    public static double computeArea(Point3d p1, Point3d p2, Point3d p3){
        // S=√p (p-a) (p-b) (p-c) - Формула Герона
        double a = p1.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p3.distanceTo(p1);
        double p = (a + b + c) / 2; // Полупериметр
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
