public class Task4 {
    public static void main(String[] args) {
        if(args.length != 4) {System.out.println("Введите x_beg, x_end, dx и eps\n(Все типа double)"); System.exit(0); }
        double x_beg = Double.parseDouble(args[0]);
        double x_end = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double eps = Double.parseDouble(args[3]);

        System.out.println("+----------------+----------------+-----+");
        System.out.println("|        X       |         Y      |  N  |");
        System.out.println("+----------------+----------------+-----+");

        double x_curr = x_beg;
        while(x_curr <= x_end){
            // Formula: *= (-1)/((2 * n + 1) * (x * x))
            double num = (-1)/((2 * x_curr + 1) * (x_curr * x_curr));
            double y = num;
            int n = 1;
            while(true){
                // System.out.println(y + "  " + num);
                num *= num;
                y += num;
                n += 1;
                if(Math.abs(num) < eps) break;
            }
            System.out.printf("|%1$+16.2f|%2$+16.5f|%3$5d|",x_curr,y,n);
            System.out.println();
            x_curr += dx;
        }
        System.out.println("+----------------+----------------+-----+");
    }
}
