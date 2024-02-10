public class Task3 {
    public static void main(String[] args) {
        double x = Float.parseFloat(args[0]);
        double eps = Float.parseFloat(args[1]);
        double formula = (1 + Math.pow(x,2)) * Math.atan(x) - x;
        if (Math.abs(x) < 1) return;
        double num = (1 * ((2 * Math.pow(x,(2 * 1 + 1)))/(4 * Math.pow(1,2) - 1)));
        double summa = num;
        System.out.println(summa);
        int i = 1;
        int flag = 1;
        while (Math.abs(num) <= eps){
            i += 1;
            flag = -flag;
            num = (flag * ((2 * Math.pow(x,(2 * i + 1)))/(4 * Math.pow(i,2) - 1)));
            summa += num;
        }
        System.out.println(summa);
        System.out.println(formula);
    }
}
