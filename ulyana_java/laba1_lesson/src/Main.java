//int[] a = {1,2,3};
//int[] a = new int[5];
//int[] c = new int[] {0, 1, 3};


//int [][] m = new int[3][5];



//
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Введите Xнач, Xкон, шаг dx и точность ε:");
//        double xBegin = scanner.nextDouble();
//        double xEnd = scanner.nextDouble();
//        double dx = scanner.nextDouble();
//        double epsilon = scanner.nextDouble();
//
//        // Вывод заголовка таблицы
//        System.out.println("+--------+--------+-----+");
//        System.out.println("|   X    |   Y    |  N  |");
//        System.out.println("+--------+--------+-----+");
//
//        // Переменная для хранения текущего значения аргумента
//        double x = xBegin;
//
//        // Пока не достигнут конец интервала
//        while (x <= xEnd) {
//            double slag = 1;
//            int n = 0;
//            double sum = slag;
//            int i = 1;
//            double pred_slag;
//
//            // Вычисление суммы ряда Тейлора с учетом точности
//            while (true) {
//                pred_slag = slag;
//                i+=2;
//                slag = slag * ((x*x) /  (i * (i-1))) * -1;
//                sum += slag;
//                n++;
//                if (Math.abs(slag-pred_slag) < epsilon) {
//                    break;
//                }
//            }
//
//            // Вывод строки таблицы с текущими значениями
//            System.out.printf("|%7.2f |%7.3f |%4d |\n", x, sum, n);
//
//            // Увеличение значения аргумента на шаг dx
//            x += dx;
//        }
//
//        // Вывод нижней границы таблицы
//        System.out.println("+--------+--------+-----+");
//
//
//
//
//
//    }
//}






//        2 задание
//        Вариант 15
//        В одномерном массиве, состоящем из п вещественных элементов, вычислить:
//        1. Количество элементов массива, больших С.
//        2. Произведение элементов, расположенных после максимального по модулю
//        элемента.
//                Преобразовать массив таким образом, чтобы сначала располагались все
//        отрицательные элементы, а потом - все положительные (элементы, равные
//                нулю, считать положительными)


//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner scan = new Scanner(System.in);
//
//        System.out.print("Введите количество элементов в массиве: ");
//        int n = scan.nextInt();
//
//        double[] arr = new double[n];
//
//        System.out.print("Введите значение константы C: ");
//        double c = scan.nextDouble();
//
//        // Ввод элементов массива и подсчет количества элементов, больших C, а также поиск максимального по модулю элемента
//        int maxIndex = 0;
//        int count = 0;
//        double maxAbs = 0;
//        for (int i = 0; i < n; i++) {
//            arr[i] = scan.nextDouble();
//            if (arr[i] > c) {
//                count++;
//            }
//            if (Math.abs(arr[i]) > maxAbs) {
//                maxAbs = Math.abs(arr[i]);
//                maxIndex = i;
//            }
//        }
//        System.out.println("Количество элементов массива, больших C: " + count);
//
//        // Вычисление произведения элементов после максимального по модулю элемента
//        double proizv = 1;
//        for (int i = maxIndex + 1; i < n; i++) {
//            proizv *= arr[i];
//        }
//        System.out.println("Произведение элементов после максимального по модулю элемента: " + proizv);
//
//        // Преобразование массива
//        int positiveIndex = 0;
//        for (int i = 0; i < n; i++) {
//            if (arr[i] <= 0) {
//                double temp = arr[i];
//                arr[i] = arr[positiveIndex];
//                arr[positiveIndex] = temp;
//                positiveIndex++;
//            }
//        }
//
//        // Вывод преобразованного массива
//        System.out.println("Преобразованный массив:");
//        for (double num : arr) {
//            System.out.print(num + " ");
//        }
//    }
//}




//        3 задние
//        Дана целочисленная прямоугольная матрица. Определить номер
//        первого из столбцов, содержащих хотя бы один нулевой элемент.
//                Переставляя строки заданной матрицы, расположить их в соответствии
//        с убыванием характеристик.
//                ПРИМЕЧАНИЕ: Характеристикой строки целочисленной матрицы
//        назовем сумму ее отрицательных четных элементов.


//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner scan = new Scanner(System.in);
//
//        // Ввод
//        System.out.print("Введите количество строк матрицы: ");
//        int rows = scan.nextInt();
//        System.out.print("Введите количество столбцов матрицы: ");
//        int cols = scan.nextInt();
//
//        // Инициализация матрицы
//        int[][] matrix = new int[rows][cols];
//
//        // Ввод элементов
//        System.out.println("Введите элементы матрицы:");
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                matrix[i][j] = scan.nextInt();
//            }
//        }
//
//
//        // Вывод исходной матрицы
//        System.out.println("Исходная матрица:");
//        printMatrix(matrix);
//
//        // Вызов функции для определения номера первого столбца с нулевым элементом
//        int firstZeroCol = findFirstZeroColumn(matrix);
//        System.out.println("Номер первого столбца, содержащего хотя бы один нулевой элемент: " + firstZeroCol);
//
//        // Вызов функции для перестановки строк в соответствии с убыванием характеристик
//        rearrangeRows(matrix);
//
//        // Вывод преобразованной матрицы
//        System.out.println("Преобразованная матрица:");
//        printMatrix(matrix);
//    }
//
//    // Функция для нахождения номера первого столбца с нулевым элементом
//    private static int findFirstZeroColumn(int[][] matrix) {
//        int cols = matrix[0].length;
//        for (int j = 0; j < cols; j++) {
//            for (int[] row : matrix) {
//                if (row[j] == 0) {
//                    return j + 1;
//                }
//            }
//        }
//        return -1; // если ни в одном столбце нет нулевого элемента
//    }
//
//    // Функция для перестановки строк в соответствии с убыванием характеристик
//    private static void rearrangeRows(int[][] matrix) {
//        // Создаем массив для хранения сумм отрицательных четных элементов в каждой строке
//        int[] characteristics = new int[matrix.length];
//
//        // Вычисляем характеристики каждой строки
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] < 0 && matrix[i][j] % 2 == 0) {
//                    characteristics[i] += matrix[i][j];
//                }
//            }
//        }
//
//        // Сортировка пузырьком
//        for (int i = 0; i < characteristics.length - 1; i++) {
//            for (int j = 0; j < characteristics.length - i - 1; j++) {
//                if (characteristics[j] < characteristics[j + 1]) {
//                    int temp = characteristics[j];
//                    characteristics[j] = characteristics[j + 1];
//                    characteristics[j + 1] = temp;
//                    // Перестановка строк в матрице
//                    int[] tempRow = matrix[j];
//                    matrix[j] = matrix[j + 1];
//                    matrix[j + 1] = tempRow;
//                }
//            }
//        }
//    }
//
//
//    // Функция для вывода матрицы
//    private static void printMatrix(int[][] matrix) {
//        for (int[] row : matrix) {
//            for (int num : row) {
//                System.out.printf("%4d ", num);
//            }
//            System.out.println();
//        }
//    }




//}