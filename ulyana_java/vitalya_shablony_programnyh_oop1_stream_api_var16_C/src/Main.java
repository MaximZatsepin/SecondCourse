import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        String plaintext = "Текст для шифрования";
        System.out.println("Исходный текст: " + plaintext);

        // Зашифрованный текст
        String encryptedText = encrypt(plaintext);
        System.out.println("Зашифрованный текст: " + encryptedText);
    }

    public static String encrypt(String text) {
        IntStream indicesStream = IntStream.range(0, text.length());

        // Группируем символы согласно правилам шифрования и объединяем их в строку
        return indicesStream
                .boxed()
                .collect(Collectors.groupingBy(index -> index % 3, Collectors.mapping(text::charAt, Collectors.toList())))
                .values()
                .stream()
                .flatMap(list -> list.stream().map(Object::toString))
                .collect(Collectors.joining());
    }
}
