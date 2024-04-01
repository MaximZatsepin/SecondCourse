import java.util.Arrays;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        // Создаем экземпляр интерфейса Consumer
        Consumer<int[]> printAscendingOrder = numbers -> {
            // Сортируем массив
            Arrays.sort(numbers);

            for (int number : numbers) {
                System.out.print(number + " ");
            }

        };


        int[] array = {5, 2, 7, 1, 9, 10, 0, -1, -100, 90, 50, 70, -55, 2, 7};
        printAscendingOrder.accept(array);
    }
}
