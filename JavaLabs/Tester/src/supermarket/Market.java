package supermarket;
import java.util.*;

public class Market {
    public static void main(String[] args) {
        List<Supermarket> supermarkets = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить супермаркет");
            System.out.println("2. Вывести список супермаркетов (отсортированный по имени)");
            System.out.println("3. Вывести список супермаркетов (отсортированный по рейтингу)");
            System.out.println("4. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки после числа

            switch (choice) {
                case 1:
                    System.out.println("Введите название супермаркета:");
                    String name = scanner.nextLine();
                    System.out.println("Введите рейтинг супермаркета:");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Считываем символ новой строки после числа
                    supermarkets.add(new Supermarket(name, rating));
                    break;
                case 2:
                    Collections.sort(supermarkets, Comparator.comparing(Supermarket::getName));
                    printSupermarkets(supermarkets);
                    break;
                case 3:
                    Collections.sort(supermarkets, Comparator.comparingInt(Supermarket::getRating).reversed());
                    printSupermarkets(supermarkets);
                    break;
                case 4:
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
                    break;
            }
        }
    }

    private static void printSupermarkets(List<Supermarket> supermarkets) {
        for (Supermarket supermarket : supermarkets) {
            System.out.println(supermarket);
        }
    }
}

// Класс представляющий супермаркет
class Supermarket {
    private String name;
    private int rating;

    public Supermarket(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Supermarket{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
