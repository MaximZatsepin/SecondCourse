public class Task1 {
    public static void main(String[] args) {
        if(args.length != 2) {System.out.println("Не указаны числа!"); return; }

        double num1 = Double.parseDouble(args[0]);
        double num2 = Double.parseDouble(args[1]);

        if(num1 * num2 > 100){
            System.out.println("Утроенный тангенс второго числа: " + 3 * Math.tan(num2));
        } else{
            System.out.println("Первое число, умноженное на 5: " + 5 * num1);
        }
    }
}
