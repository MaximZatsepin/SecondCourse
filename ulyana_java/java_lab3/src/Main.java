/*
2 Задание
Преобразовать каждое слово в тексте, удалив из него все следующие
вхождения первой буквы этого слова.
Работает следующим образом: "выводит" -> "выодит", "точности" -> "точноси" 
 */


import java.util.*;

// Класс для представления слова в тексте
class Word {
    private String content;

    public Word(String content) {
        this.content = content;
    }

    // Метод для преобразования слова
    public String transform() {
        // Получаем первую букву слова в нижнем регистре
        char firstLetter = Character.toLowerCase(content.charAt(0));
        StringBuilder transformedWord = new StringBuilder();

        // Добавляем первую букву слова в преобразованное слово
        transformedWord.append(content.charAt(0));

        // Проходим по остальным символам слова
        for (int i = 1; i < content.length(); i++) {
            char currentChar = content.charAt(i);
            char lowercaseChar = Character.toLowerCase(currentChar);

            // Если символ не равен первой букве, добавляем его к преобразованному слову
            if (lowercaseChar != firstLetter) {
                transformedWord.append(currentChar);
            }
        }

        // Возвращаем преобразованное слово
        return transformedWord.toString();
    }

    @Override
    public String toString() {
        // Переопределение метода toString() для возврата преобразованного слова при вызове
        return transform();
    }
}

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Текст не был передан в программу.");
            return;
        }

        // Объединяем аргументы командной строки в одну строку
        String text = String.join(" ", args);

        // Заменяем табуляции и последовательности пробелов одним пробелом
        text = text.replaceAll("\\s+", " ");

        // Разделяем текст на слова
        String[] words = text.split("\\s+");

        // Создаем StringBuilder для хранения текущей строки
        StringBuilder currentLine = new StringBuilder();

        try (Formatter formatter = new Formatter()) {
            int wordCount = 0;
            // Проходим по каждому слову в тексте
            for (String word : words) {
                Word currentWord = new Word(word);
                String transformedWord = currentWord.transform();

                // Если длина текущей строки и преобразованного слова превышают 100 символов,
                // добавляем текущую строку в форматтер, начинаем новую строку и очищаем текущую строку
                if (currentLine.length() + transformedWord.length() > 100) {
                    formatter.format("%n");
                    formatter.format("%s ", currentLine.toString());
                    currentLine.setLength(0);
                }

                // Добавляем преобразованное слово к текущей строке
                currentLine.append(transformedWord).append(" ");
                wordCount++;
            }

            // Если текущая строка не пустая, добавляем ее в форматтер
            if (!currentLine.isEmpty()) {
                formatter.format("%n");
                formatter.format("%s ", currentLine.toString());
            }

            // Выводим преобразованный текст
            System.out.println(formatter);
        }
    }
}
