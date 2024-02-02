import java.util.List;
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[5][5];
        matrix[2][3] = 2;
        System.out.print("Введите ваше имя: ");
        String stroke = scanner.nextLine();
        System.out.println("Hello, " + stroke );
        /*
        System.out.println(2+3);
        System.out.println(2-3);
        System.out.println(2 / 3);
        System.out.println(2 % 3);
        System.out.println(Math.pow(2,3));
        System.out.println((int)Math.sqrt(4));
        */

        String[] stroke2 = scanner.nextLine().split(" ");
        List<Integer> array = new ArrayList<>();
        for(String i : stroke2){
            array.add(Integer.parseInt(i));
        }
        System.out.print("Ваш массив:\n");
        for(Integer i : array){
            System.out.println("i = " + i);
        }

    }
}



