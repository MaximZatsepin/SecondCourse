
public class Task1 {
//    8. Пенсионные отчисления составляют p% заработной платы.
//    В январе средняя заработная плата составляла Z руб, а далее
//    увеличивалась на q% ежемесячно. Для каждого месяца текущего
//    года вычислить пенсионные отчисления.
    public static void main(String[] args) {
        if(args.length != 2) { System.out.println("Неверный аргумент!"); return; }

        int z = Integer.parseInt(args[0]);
        float q = Float.parseFloat(args[1]);
        System.out.println("Z = " + z + ", q = " + q);
        for(int i = 0; i < 11; i++){
            System.out.print("Пенсионные отчисления в месяце " + (int)(i+1) + ": " + (float)(z * q * 0.13) +"\n");
            z += z * q;
        }
    }
}