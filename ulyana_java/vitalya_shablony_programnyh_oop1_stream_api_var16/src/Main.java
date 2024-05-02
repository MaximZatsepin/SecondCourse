import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean find = false;
        String poem = "ветра веют весело,\n" +
                "весна встречает волнами,\n" +
                "вечер волнует волной,\n" +
                "вечеринка веселит.";

        // Создаем массив слов
        List<String> words = Arrays.asList(poem.split("\\s+"));

        String firstWord = words.get(0);
        char[] firstWordLetters = firstWord.toCharArray();

        // Перебираем буквы первого слова
        for (char letter : firstWordLetters) {

            // Смотрим, есть ли очередная буква первого слова во всех остальных без исключения
            boolean allWordsContainLetter = true;
            for (String word : words) {
                if (!word.contains(String.valueOf(letter))) {
                    allWordsContainLetter = false;
                    break;
                }
            }
            // Если данная буква есть в каждом слове, то все хорошо, мы ее нашли
            if (allWordsContainLetter) {
                System.out.println("Буква, которая встречается во всех словах: " + letter);
                find = true;
            }
        }
        // Если ни одна буква первого слова не встречается во всех словах
        if (!find){
            System.out.println("Нет такой буквы, которая была бы во всех словах.");
        }

    }
}
