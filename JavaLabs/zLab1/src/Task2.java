public class Task2 {
    public static void main(String[] args) {
        if(args.length != 2) {System.out.println("Не указаны числа!"); return; }

        double num1 = Double.parseDouble(args[0]);
        double num2 = Double.parseDouble(args[1]);

        double maxNum = Math.max(num1,num2);
        double minNum = Math.min(num1,num2);

        System.out.println("Максимальное число из введенных: " + maxNum);
        System.out.println("Минимальное число из введенных: " + minNum);

        double d = (minNum - 2 * Math.sqrt(minNum) + 4.2 * minNum)
                   / (1 + (maxNum/minNum));
        System.out.println("Число d равно " + d);
    }
}
