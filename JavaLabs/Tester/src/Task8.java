import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task8 {
    
    public static List<String> formatText(String text, int line_lenght) {
        List<String> result = new ArrayList<>();
        String[] words = text.split("\s+"); // Разбиваем текст на слова
        String currentLine = "";
        int currentLength = 0;

        for (String word : words) {
            if (currentLength + word.length() <= line_lenght) { // Если слово помещается в текущую строку
                currentLine += word + " ";
                currentLength += word.length() + 1; // Добавляем длину слова и пробела
            } else {
                result.add(currentLine.trim()); // Добавляем текущую строку в результат
                currentLine = word + " "; // Начинаем новую строку с текущего слова
                currentLength = word.length() + 1; // Обновляем текущую длину
            }
        }

        if (!currentLine.isEmpty()) {
            result.add(currentLine.trim()); // Добавляем последнюю строку в результат, если есть
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String text = "Статья — жанр журналистики, в котором автор ставит задачу проанализировать общественные ситуации, процессы, явления, прежде всего с точки зрения закономерностей, лежащих в их основе. В статье автор рассматривает отдельные ситуации как часть более широкого явления. Автор аргументированно пишет о своей точке зрения.";
        // System.out.println("Введите текст:");
        // String text = scanner.nextLine();

        System.out.println("Введите длину разделения:");
        // int line_lenght = 40;
        int line_lenght = scanner.nextInt();

        List<String> formattedText = formatText(text, line_lenght);

        for (String line : formattedText) { // Вывод текста
            System.out.println(line);
        }
    }
}
