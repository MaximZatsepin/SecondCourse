public class Task2 {
//    8. Начальная стоимость оборудования – А руб. За первый год эксплуатации
//    его стоимость уменьшилась на В руб., за второй год – на В/2 руб., за третий
//    год – на В/3 руб. и т. д. Вычислить среднюю стоимость оборудования за N
//    лет эксплуатации. Считать, что А > BN.
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);
        int avgprice = 0;
        System.out.println("Стоимость: " + a + ", B = " + b + ", Кол-во лет: " + n);
        if(a <= b*n) { System.out.println("Неверные аргументы"); return; }
        for(int i = 1 ; i <= n; i++){
            avgprice += (a - b/i);
            a -= b/i;
        }
        System.out.println("Средняя цена оборудования за n лет: " + avgprice/n );
    }
}
