public class Task3 {
    // Составить программу нахождения суммы ряда 
    // с заданной точностью eps. Использовать рекуррентные 
    // соотношения при вычислении очередного элемента ряда. 
    // Предусмотреть вычисление по контрольной формуле.

    public static void main(String[] args) {
        if(args.length != 2) {System.out.println("Input Error"); return;}

        double x = Double.parseDouble(args[0]);
        double eps = Double.parseDouble(args[1]);

        double formula = (1 + Math.pow(x, 2)) * Math.atan(x) - x;

        int i = 1;
        double chislitel = (4 * i * i - 1);
        double num = (2 * x * x * x) / chislitel ;
        double sum = 0;
        while(true){
            if(Math.abs(num) < eps) break;
            sum += num;
            i += 1;
            chislitel = (4 * i * i - 1);
            num *= -1 * ((x * x) / chislitel);
        }
        System.out.println("Ряд - " + sum);
        System.out.println("Формула - " + formula);
    }
}