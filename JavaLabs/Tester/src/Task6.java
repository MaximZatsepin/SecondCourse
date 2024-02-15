import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        // int[][] matrix = {
        //     {1,2,3,4},
        //     {2,2,8,5},
        //     {3,4,5,6},
        //     {4,9,6,-7}
        // };
            int[][] matrix = inputMatrix();
        // элементы k строки совпадают с элементами k столбца
        findMatchingRowsAndColumns(matrix);

        // сумма строк с отрицательным элементом
        int sum = sumOfRowsWithNegatives(matrix);
        System.out.println("Сумма элементов в строках с отрицательными элементами: " + sum);
    }

        public static int[][] inputMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность матрицы:");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static void findMatchingRowsAndColumns(int[][] matrix) {
        int n = matrix.length;
        for (int k = 0; k < n; k++) {
            boolean match = true;
            for (int i = 0; i < n; i++) {
                if (matrix[k][i] != matrix[i][k]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                System.out.println("Строка " + k + " совпадает со столбцом " + k);
            }
        }
    }

    public static int sumOfRowsWithNegatives(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                if (element < 0) {
                    for (int value : row) {
                        sum += value;
                    }
                    break;
                }
            }
        }
        return sum;
    }
}
