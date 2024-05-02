import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину заменяемого слова: ");
        int lengthToReplace = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите слово, на которое будет заменено: ");
        String replacement = scanner.nextLine();

        // Генерируем рандомный текстиз 100 слов, слова в тексте длины минимум 5, максимум 15
        String text = generateText(100, 5, 15);
        System.out.println("\nИсходный текст:");
        printFormattedText(text);

        // Замена слов заданной длины подстрокой
        String newText = Arrays.stream(text.split("\\s+"))
                .map(word -> word.length() == lengthToReplace ? replacement : word)
                .collect(Collectors.joining(" "));

        // Вывод
        System.out.println("\nТекст после замены:");
        printFormattedText(newText);
    }

    // Функция для генерации рандомного текста
    public static String generateText(int wordCount, int minLength, int maxLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            int length = (int) (Math.random() * (maxLength - minLength + 1) + minLength);
            String word = generateRandomWord(length);
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }

    // Функция для генерации слова рандомной длины
    public static String generateRandomWord(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + Math.random() * 26);
            sb.append(c);
        }
        return sb.toString();
    }

    // Функция для вывода 10 слов в строке
    public static void printFormattedText(String text) {
        List<String> words = Arrays.asList(text.split("\\s+"));
        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i) + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}
