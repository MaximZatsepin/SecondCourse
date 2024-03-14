public class Task5 {
    public static void main(String[] args) {
        double[] array = {1.5, -2.0, 0.7, 3.2, -1.8, 0.9, -0.4}; // Пример входного массива

        // Минимальный
        int minIndex = findMinIndex(array);
        System.out.println("Номер минимального элемента: " + minIndex);

        // сумма между 1 и 2 отрицательным
        double sumBetweenNegatives = sumBetweenNegatives(array);
        System.out.println("Сумма элементов между первым и вторым отрицательными элементами: " + sumBetweenNegatives);

        // Преобразование и вывод
        transformArray(array);

        System.out.print("Преобразованный массив: ");
        for (double value : array) {
            System.out.print(value + " ");
        }
    }

    // Метод для нахождения номера минимального элемента
    public static int findMinIndex(double[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Метод для вычисления суммы элементов между первым и вторым отрицательными элементами
    public static double sumBetweenNegatives(double[] array) {
        int firstNegativeIndex = -1;
        int secondNegativeIndex = -1;
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                if (firstNegativeIndex == -1) {
                    firstNegativeIndex = i;
                } else {
                    secondNegativeIndex = i;
                    break;
                }
            }
        }
        if (firstNegativeIndex != -1 && secondNegativeIndex != -1) {
            for (int i = firstNegativeIndex + 1; i < secondNegativeIndex; i++) {
                sum += array[i];
            }
        }
        return sum;
    }

    // Метод для преобразования массива
    public static void transformArray(double[] array) {
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            if (Math.abs(array[i]) <= 1) {
                i++;
            } else if (Math.abs(array[j]) > 1) {
                j--;
            } else {
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
    }
}
