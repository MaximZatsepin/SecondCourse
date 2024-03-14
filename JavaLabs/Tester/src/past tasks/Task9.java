import java.util.*;

public class Task9 {
    public static void main(String[] args) {
        String text = "Пример текста для обработки. В нем содержатся слова с гласными и согласными буквами, например Аптека, ёжик. Также есть знаки препинания, а также восклицательный!";

        List<String> words = extractWords(text);
        List<String> glasnie_words = filter_glasnix(words);
        filter_2soglasnaya(glasnie_words);

        for (String word : glasnie_words) {
            System.out.println(word);
        }
    }

    // Извлечение слов из текста
    public static List<String> extractWords(String text) {
        String[] wordsArray = text.replaceAll("[!-0]", " ").split("\s+");
        return Arrays.asList(wordsArray);
    }

    // Фильтрация слов, начинающихся с гласной буквы
    public static List<String> filter_glasnix(List<String> words) {
        List<String> glasnie_words = new ArrayList<>();
        for (String word : words) {
            char firstChar = Character.toLowerCase(word.charAt(0));
            if (is_glasnaya(firstChar)) {
                glasnie_words.add(word);
            }
        }
        return glasnie_words;
    }

    // Проверка, является ли символ гласной буквой
    public static boolean is_glasnaya(char c) {
        return "аеёиоуыэюя".indexOf(c) != -1;
    }

    // Сортировка слов по первой согласной букве
    public static void filter_2soglasnaya(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String word1 = words.get(i);
                String word2 = words.get(j);
                char firstConsonant1 = find_first_soglasnaya(word1);
                char firstConsonant2 = find_first_soglasnaya(word2);
                if (Character.toLowerCase(firstConsonant1) > Character.toLowerCase(firstConsonant2)) {
                    String temp = words.get(i);
                    words.set(i, words.get(j));
                    words.set(j, temp);
                }
            }
        }
    }

    // Поиск первой согласной буквы в слове
    public static char find_first_soglasnaya(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            if (!is_glasnaya(c)) {
                return c;
            }
        }
        return Character.MIN_VALUE; // Если в слове нет согласных
    }
}
