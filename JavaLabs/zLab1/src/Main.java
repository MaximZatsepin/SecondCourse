import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //game();
        //multiplicationTable();
    }

    public static void game() {
        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();

        int countOfTries = 3;
        int randomNumber = rnd.nextInt(1, 11);
        int personNumber;

        System.out.println("Компьютер загадал число от 1 до 10 включительно.\nПопробуйте угадать!");
        while (true) {
            System.out.print("Введите число: ");
            personNumber = scanner.nextInt();
            if (randomNumber == personNumber) {     // Угадано
                System.out.println("Вы угадали!");
                return;
            }
            countOfTries -= 1;  // Вычитание попытки
            if (randomNumber > personNumber) System.out.println("Загаданное число больше."); // Загаданное число больше
            if (randomNumber < personNumber) System.out.println("Загаданное число меньше."); // Загаданное число меньше
            if (countOfTries == 0) { // Если попытки закончились
                System.out.println("У вас закончились попытки!\nЗагаданное число: " + randomNumber + ". Вы проиграли!");
                return;
            }
            System.out.println("Неправильно! У вас осталось " + countOfTries + " попыток.");
        }
    }

    public static void multiplicationTable() {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(j * i + "\t");
            }
            System.out.println();
        }
    }
}