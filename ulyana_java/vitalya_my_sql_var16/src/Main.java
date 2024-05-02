import java.sql.*;
import java.util.Random;
import java.util.Scanner;


public class Main {
    static final String url = "jdbc:mysql://localhost:3306/Poems";
    static final String user = "root";
    static final String pass = "123456";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            // Создание таблицы для хранения стихотворений (если она еще не существует)
            createPoemsTable(connection);

            Scanner scanner = new Scanner(System.in);
            // Меню
            while (true) {
                System.out.println("\nМеню:");
                System.out.println("1 - Найти стихотворение с наибольшим количеством восклицательных предложений");
                System.out.println("2 - Найти стихотворение с наименьшим количеством повествовательных предложений");
                System.out.println("3 - Подсчитать количество сонетов в базе данных");
                System.out.println("4 - Вывести содержимое базы данных");
                System.out.println("5 - Полностью очистить базу данных");
                System.out.println("6 - Генерировать случайные стихотворения и добавить их в базу данных (если она пуста)");
                System.out.println("7 - Выход из программы");
                System.out.print("Выберите действие: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        findPoemWithMostExclamatorySentences(connection);
                        break;
                    case 2:
                        findPoemWithLeastNarrativeSentences(connection);
                        break;
                    case 3:
                        countSonets(connection);
                        break;
                    case 4:
                        displayPoems(connection);
                        break;
                    case 5:
                        clearDatabase(connection);
                        System.out.println("База данных успешно очищена!");
                        break;
                    case 6:
                        fillDatabase(connection);
                        System.out.println("Случайные стихотворения успешно добавлены в базу данных!");
                        break;
                    case 7:
                        System.out.println("Программа завершена!");
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор. Повторите попытку.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Создание таблицы для хранения стихотворений
    private static void createPoemsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Poems (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "author VARCHAR(255) NOT NULL," +
                    "year INT NOT NULL," +
                    "poem TEXT NOT NULL)";
            statement.executeUpdate(createTableQuery);
        }
    }

    // Метод вывода
    private static void displayPoems(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM Poems";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            System.out.println("Содержимое таблицы Poems:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("year");
                String poem = resultSet.getString("poem");
                String poemInfo = "ID: " + id + ", Автор: " + author + ", Год: " + year + "\n" + poem;
                System.out.println(poemInfo);
                System.out.println();
            }
        }
    }

    // Метод полной очистки базы данных
    private static void clearDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String clearTableQuery = "DELETE FROM Poems";
            statement.executeUpdate(clearTableQuery);
        }
    }

    // Метод генерации и добавления случайных стихотворений в базу данных
    private static void fillDatabase(Connection connection) throws SQLException {
        String insertPoemQuery = "INSERT INTO Poems (author, year, poem) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPoemQuery)) {

            String[] authors = {"Александр Пушкин", "Сергей Есенин", "Анна Ахматова", "Марина Цветаева", "Иосиф Бродский"};
            int[] years = {1833, 1921, 1916, 1934, 1987};

            Random random = new Random();
            boolean atLeastOneSonnetGenerated = false;


            for (int i = 0; i < 10; i++) {

                String author = authors[random.nextInt(authors.length)];
                int year = years[random.nextInt(years.length)];
                String poem;

                // Генерируем случайное стихотворение
                if (i == 9 && !atLeastOneSonnetGenerated) {
                    poem = generateSonnet(random);
                } else {
                    poem = generateRandomPoem(random);
                }

                // Проверяем, является ли сгенерированное стихотворение сонетом (чтобы в генерации был хотя бы один сонет)
                if (!atLeastOneSonnetGenerated && isSonnet(poem)) {
                    atLeastOneSonnetGenerated = true;
                }

                preparedStatement.setString(1, author);
                preparedStatement.setInt(2, year);
                preparedStatement.setString(3, poem);
                preparedStatement.executeUpdate();
            }
        }
    }


    // Метод для генерации случайного сонета
    private static String generateSonnet(Random random) {
        // Примеры сонетов
        String[] sonnets = {
                "Сравню тебя с летним днем, ты превосходней:\n" +
                "Ты прекрасней и умеренней:\n" +
                "Рвут почки майские, ветры недаром,\n" +
                "Лето коротким сроком проходит.\n" +
                "Иногда слишком горяче сияет небесный свет,\n" +
                "И часто бледнеет его золотой цвет;\n" +
                "И всякая красота из красоты иногда угасает,\n" +
                "От случая или природы изменчивой корысти.\n" +
                "Но вечное твое лето не увядет\n" +
                "Ни потеряет то прекрасное, что ты должна;\n" +
                "И не похвалит Смерть, что ты блуждаешь в его тени,\n" +
                "Когда в вечных строках к времени ты растешь:\n" +
                "Так долго, как могут дышать люди или видеть глаза,\n" +
                "Так долго это и это дает тебе жизнь.\n"
        };
        return sonnets[random.nextInt(sonnets.length)];
    }


    // Метод генерации случайного стихотворения
    private static String generateRandomPoem(Random random) {

        String[] exclamationPoems = {
                "О, как мне важен этот миг!\nСвети, солнце, ты мне дорог!\n",
                "Ура! Победа наша!\n",
                "Счастье в сердце! Будь всегда с нами!\n"
        };
        String[] narrativePoems = {
                "Я вижу туман над рекой,\nСерый, как старая печаль.\n",
                "Тихо бежит ручей сквозь луг,\nСлышно, как весело плещется вода.\n"
        };
        String[] questionPoems = {
                "Почему так темно ночью?\nЗвезды, скажите, где вы?\n",
                "Куда ушла весна?\nНеужели она уже устала?\n"
        };

        int randomIndex = random.nextInt(3);
        switch (randomIndex) {
            case 0:
                return exclamationPoems[random.nextInt(exclamationPoems.length)];
            case 1:
                return narrativePoems[random.nextInt(narrativePoems.length)];
            case 2:
                return questionPoems[random.nextInt(questionPoems.length)];
            default:
                return "";
        }
    }




    // Метод поиска стихотворения с наибольшим количеством восклицательных предложений
    private static void findPoemWithMostExclamatorySentences(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя автора для поиска восклицательных предложений: ");
        String author = scanner.nextLine();

        String selectQuery = "SELECT * FROM Poems WHERE author = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, author);

            try (ResultSet resultSet = statement.executeQuery()) {
                int maxExclamatorySentences = 0;
                String poemWithMostExclamatorySentences = "";

                while (resultSet.next()) {
                    String poem = resultSet.getString("poem");
                    int exclamatorySentences = countExclamatorySentences(poem);

                    if (exclamatorySentences > maxExclamatorySentences) {
                        maxExclamatorySentences = exclamatorySentences;
                        poemWithMostExclamatorySentences = poem;
                    }
                }

                if (maxExclamatorySentences > 0) {
                    System.out.println("Стихотворение с наибольшим количеством восклицательных предложений:");
                    System.out.println(poemWithMostExclamatorySentences);
                } else {
                    System.out.println("В стихотворениях данного автора нет восклицательных предложений.");
                }
            }
        }
    }

    // Метод подсчета количества восклицательных предложений в стихотворении
    private static int countExclamatorySentences(String poem) {
        int count = 0;
        // Подсчитываем количество восклицательных знаков
        for (int i = 0; i < poem.length(); i++) {
            if (poem.charAt(i) == '!') {
                count++;
            }
        }
        return count;
    }

    // Метод поиска стихотворения с наименьшим количеством повествовательных предложений
    private static void findPoemWithLeastNarrativeSentences(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя автора для поиска наименьшего количества повествовательных предложений: ");
        String author = scanner.nextLine();

        String selectQuery = "SELECT * FROM Poems WHERE author = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, author);

            try (ResultSet resultSet = statement.executeQuery()) {
                int minNarrativeSentences = Integer.MAX_VALUE;
                String poemWithLeastNarrativeSentences = "";

                while (resultSet.next()) {
                    String poem = resultSet.getString("poem");
                    int narrativeSentences = countNarrativeSentences(poem);

                    if (narrativeSentences < minNarrativeSentences) {
                        minNarrativeSentences = narrativeSentences;
                        poemWithLeastNarrativeSentences = poem;
                    }
                }

                if (minNarrativeSentences < Integer.MAX_VALUE) {
                    System.out.println("Стихотворение с наименьшим количеством повествовательных предложений:");
                    System.out.println(poemWithLeastNarrativeSentences);
                } else {
                    System.out.println("В стихотворениях данного автора нет повествовательных предложений.");
                }
            }
        }
    }

    // Метод подсчета количества повествовательных предложений в стихотворении
    private static int countNarrativeSentences(String poem) {
        int count = 0;
        // Подсчитываем количество точек
        for (int i = 0; i < poem.length(); i++) {
            if (poem.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }


    // Метод подсчета количества сонетов в базе данных
    private static void countSonets(Connection connection) throws SQLException {

        String selectQuery = "SELECT * FROM Poems";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            int sonnetCount = 0;

            while (resultSet.next()) {
                String poem = resultSet.getString("poem");
                if (isSonnet(poem)) {
                    sonnetCount++;
                }
            }

            // Вывод результата
            System.out.println("Количество сонетов в базе данных: " + sonnetCount);
        }
    }

    // Метод для определения, является ли стихотворение сонетом
    // Должно быть 14 строчек и 8 и 14 строчки должны заканчиваться точками
    private static boolean isSonnet(String poem) {
        // Разделяем стихотворение на строки
        String[] lines = poem.split("\n");

        // Проверяем, состоит ли стихотворение из 14 строк
        if (lines.length != 14) {
            return false;
        }
        // Проверяем точки
        if ( lines[8].endsWith(".") && lines[13].endsWith(".")) {
            return false;
        }
        return true;
    }
}
