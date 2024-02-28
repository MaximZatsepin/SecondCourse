import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Введите текст:");
        // String text = scanner.nextLine();
        String text = "Привет, как(ая солнечная погода, как твои) дела (как дела)?";

        System.out.println("Введите первый символ:");
        char startChar = scanner.next().charAt(0);

        System.out.println("Введите второй символ:");
        char endChar = scanner.next().charAt(0);

        String result = deleteTextBetweenChars(text, startChar, endChar);
        System.out.println("Результат:");
        System.out.println(result);
    }

    public static String deleteTextBetweenChars(String text, char startChar, char endChar) {
        String result = "";
        boolean is_delete = false;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentChar == startChar) {
                is_delete = true;
                continue;
            }

            if (currentChar == endChar) {
                is_delete = false;
                continue;
            }

            if (!is_delete) {
                result += currentChar;
            }
        }

        return result;
    }
}
