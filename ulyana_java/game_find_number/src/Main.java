//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Random random = new Random();
//
//        int riddle = (int) (Math.random() * 100);
//
//        while (true) {
//            System.out.print("Угадайте число: ");
//            int chisel =  scanner.nextInt();
//
//            if (chisel == riddle) {
//                System.out.println("Поздравляю! Вы угадали число!");
//                break; // Прерывание цикла, если число угадано
//            } else if (chisel > riddle) {
//                System.out.println("Загаданное число меньше");
//            } else {
//                System.out.println("Загаданное число больше");
//            }

//        for (int i = 1; i <= 10; i++){
//            for (int j = 1; j<=10; j++){
//                System.out.print(i*j + "\t");
//            }
//            System.out.println();
//        }


//        int flag = 1;
//
//        System.out.println("Простые числа: ");
//
//        for (int i = 2; i <= 100; i++){
//            for (int j = 2; j<=(int)Math.sqrt(i); j++){
//                if (i % j == 0){
//                    flag = 0;
//                    break;
//                }
//            };
//
//            if (flag != 0)  {
//                System.out.println(i);
//            }
//            flag = 1;
//        }






//        Задание 1 стр 14

        /*Средняя продолжительность жизни составляет N лет. В ближайшие
        M лет прогнозируется ее ежегодный рост на p% за счет достижений медицины и
        уменьшение на Q месяцев ежегодно из-за ухудшения экологии. Для
        каждого из указанных M лет рассчитать ожидаемую среднюю продолжительность жизни. */

//        Scanner scan = new Scanner(System.in);
//        System.out.print("Введите М: ");
//        double M = scan.nextDouble();
//        System.out.print("Введите N: ");
//        double N = scan.nextDouble();
//        System.out.print("Введите p: ");
//        double p = scan.nextDouble();
//        System.out.print("Введите Q: ");
//        double Q = scan.nextDouble();
//
//
//        for (int i = 1; i < M; i++){
//            N +=  (N * (double)(p/100)) - (double)(Q / 12);;
//            System.out.print(N + "\t");
//        }

        /* Задание 2
        На овощном складе хранилось Q т картофеля. В конце первого месяца
        было изъято В т для продажи, в конце второго – 2В т, ..., в конце N-го – NВ т.
        Процент естественных ежемесячных потерь полагается постоянным и равным р%.
         Определить количество картофеля на складе на начало (N + 1)-го
        месяца.*/


//        Scanner scan = new Scanner(System.in);
//        System.out.print("Введите Q (начальное кол-во): ");
//        double Q = scan.nextDouble();
//
//        System.out.print("Введите p (процент): ");
//        double p = scan.nextDouble();
//
//        System.out.print("Введите B (ежемесячная продажа): ");
//        double B = scan.nextDouble();
//
//        System.out.print("Введите N (кол-во месяцев): ");
//        int N = scan.nextInt();
//
//        for (int i = 1; i <= N; i++){
//            Q = Q - (Q * (p/100) + ( B*i));
//        }
//        Q = Q - (Q * (p/100) + ( B*(N+1)));
//        System.out.println(Q);


        /* Задание 3*/
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Введите x: ");
//        double x = scan.nextDouble();
//
//
//        System.out.print("Введите e: ");
//        double e = scan.nextDouble();
//
//        double sum = 0;
//        double chisl1 = 3;
//        double chisl2 = x*x;
//        double znam = 2;
//        sum += (chisl1 / znam * chisl2);
//
//
//        int i = 1;
//        double prevSum = sum;
//
//        while (Math.abs(sum - prevSum) >= e) {
//            prevSum = sum;
//            i++;
//
//            chisl1 = 2 * (i*i) + 1;
//            chisl2 *=  (x*x);
//            znam *= ((2*i) * (2* i-1));
//            sum += ((chisl1 / znam * chisl2) * -1);
//
//        }
//
//        // Вывод результата
//        System.out.println("Сумма ряда: " + sum);
//
//        // Проверка с помощью контрольной формулы
//        double controlSum = 1 + ((x/2) * Math.sin(x)) + (((x*x / 2) - 1) * Math.cos(x));
//        System.out.println("Контрольная сумма: " + controlSum);



        Scanner scan = new Scanner(System.in);
        System.out.print("Введите x: ");
        double x = scan.nextDouble();


        System.out.print("Введите e: ");
        double e = scan.nextDouble();

        double controlSum = 1 + ((x/2) * Math.sin(x)) + (((x*x / 2) - 1) * Math.cos(x));

        double sum = 0;
        double chisl1 = 3;
        double chisl2 = x*x;
        double znam = 2;
        sum += (chisl1 / znam * chisl2);


        int i = 1;


        while (Math.abs(sum - controlSum) >= e) {

            i++;

            chisl1 = 2 * (i*i) + 1;
            chisl2 *=  (x*x);
            znam *= ((2*i) * (2* i-1));
            sum += ((chisl1 / znam * chisl2) * -1);

        }

        // Вывод результата
        System.out.println("Сумма ряда: " + sum);

        // Проверка с помощью контрольной формулы
        System.out.println("Контрольная сумма: " + controlSum);



    }
}