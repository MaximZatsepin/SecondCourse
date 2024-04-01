
import java.sql.*;
import java.util.Scanner;


public class Main {

    static final String url = "jdbc:mysql://localhost:3306/Dictionary";
    static final String user = "root";
    static final String pass = "123456";


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            createTablesDictionary(connection);

            Scanner scanner = new Scanner(System.in);

            // Меню
            while(true){
                System.out.println("\nМеню:" +
                        "\n1 - Добавить данные в русско-беларусский словарь" +
                        "\n2 - Добавить данные в беларусско-русский словарь" +
                        "\n3 - Удалить запись из русско-беларусского словаря по ID" +
                        "\n4 - Удалить запись из беларусско-русского словаря по ID" +
                        "\n5 - Перевести слово" +
                        "\n6 - Вывести все словари" +
                        "\n7 - Выход из программы");
                System.out.print("\nВыберите действие: " );
                switch (scanner.nextInt()) {
                    case 1:
                        addRBWord(connection, scanner);
                        break;
                    case 2:
                        addBRWord(connection, scanner);
                        break;
                    case 3:
                        System.out.print("Введите id записи: ");
                        deleteRBDictionaryWordById(connection, scanner.nextInt());
                        break;
                    case 4:
                        System.out.print("Введите id записи: ");
                        deleteBRDictionaryWordById(connection, scanner.nextInt());
                        break;
                    case 5:
                        System.out.print("\n1 - Перевод русского слова на беларусский язык \n2 - Перевод беларусского слова на русский язык\nВыберите действие: " );
                        int choice = scanner.nextInt();
                        if (choice == 1){
                            translateWordToBelarusian(connection, scanner);
                        }
                        else if (choice == 2){
                            translateWordToRussian(connection,scanner);
                        }
                        else {
                            System.out.println("Неверный ввод");
                        }
                        break;
                    case 6:
                        outputAllDictionaryTabels(connection);
                        break;
                    case 7:
                        System.out.println("Программа завершена!");
                        System.exit(0);
                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // Проверка на наличие нужных таблиц, при отсутствии создает их
    private static void createTablesDictionary(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            // Проверка существования таблицы первого (р-б) словаря
            ResultSet resultSetDictionary = connection.getMetaData().getTables(null, null, "russianBelarusianDictionary", null);
            // Если таблицы русско-беларусского словаря не существует
            if (!resultSetDictionary.next()) {
                // Создаем таблицу
                String createDictionaryTableQuery = "CREATE TABLE russianBelarusianDictionary (" +
                        "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "russianWord VARCHAR(255) NOT NULL," +
                        "belarusianTranslation VARCHAR(255) NOT NULL)";
                statement.executeUpdate(createDictionaryTableQuery);
                System.out.println("Таблица русско-беларусского словаря успешно создана");

            }
            else System.out.println("Таблица russianBelarusianDictionary загружена!");

            // Проверка существования таблицы второго (б-р) словаря
            resultSetDictionary = connection.getMetaData().getTables(null, null, "belarusianRussianDictionary", null);
            // Если таблицы беларусско-русского словаря не существует
            if (!resultSetDictionary.next()) {
                // Создание таблицы
                String createDictionaryTableQuery = "CREATE TABLE belarusianRussianDictionary (" +
                        "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "belarusianWord VARCHAR(255) NOT NULL," +
                        "russianTranslation VARCHAR(255) NOT NULL)";
                statement.executeUpdate(createDictionaryTableQuery);
                System.out.println("Таблица белорусско-русского словаря успешно создана");

            }
            else System.out.println("Таблица belarusianRussianDictionary загружена!");
        }
    }

    // Добавление записи в таблицу russianBelarusianDictionary
    private static void addRBWord(Connection connection, Scanner scanner) throws SQLException {
        String addRBWordQuery = "INSERT INTO russianBelarusianDictionary (russianWord, belarusianTranslation) VALUES (?,?)";

        scanner.nextLine();
        System.out.print("Введите русское слово: ");
        String russianWord = scanner.nextLine();

        System.out.print("Введите перевод на беларусский: ");
        String belarusianTranslation = scanner.nextLine();

        try(PreparedStatement preparedStatement = connection.prepareStatement(addRBWordQuery)){
            preparedStatement.setString(1, russianWord);
            preparedStatement.setString(2, belarusianTranslation);

            preparedStatement.executeUpdate();
        }
        System.out.println("\nСлово успешно добавлено в русско-беларсский словарь!");
    }


    // Добавление записи в таблицу belarusianRussianDictionary
    private static void addBRWord(Connection connection, Scanner scanner) throws SQLException {
        String addBRWordQuery = "INSERT INTO belarusianRussianDictionary (belarusianWord, russianTranslation) VALUES (?,?)";

        scanner.nextLine();
        System.out.print("Введите беларусское слово: ");
        String belarusianWord = scanner.nextLine();

        System.out.print("Введите перевод на русский: ");
        String russianTranslation = scanner.nextLine();

        try(PreparedStatement preparedStatement = connection.prepareStatement(addBRWordQuery)){
            preparedStatement.setString(1, belarusianWord);
            preparedStatement.setString(2, russianTranslation);

            preparedStatement.executeUpdate();
        }
        System.out.println("\nСлово успешно добавлено в беларсско-русский словарь!");
    }


    // Удаление записи из таблицы russianBelarusianDictionary по ID
    private static void deleteRBDictionaryWordById(Connection connection, int id) throws SQLException {
        String deleteDictionaryWordQuery = "DELETE FROM russianBelarusianDictionary WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDictionaryWordQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Запись успешно удалена из русско-белорусского словаря!");
            } else {
                System.out.println("Запись с указанным id не найдена в русско-белорусском словаре.");
            }
        }
    }



    // Удаление записи из таблицы belarusianRussianDictionary по ID
    private static void deleteBRDictionaryWordById(Connection connection, int id) throws SQLException {
        String deleteDictionaryWordQuery = "DELETE FROM belarusianRussianDictionary WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDictionaryWordQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("Запись успешно удалена из белорусско-русского словаря!");
            } else {
                System.out.println("Запись с указанным id не найдена в белорусско-русском словаре.");
            }
        }
    }




    // Поиск перевода на беларусский
    private static void translateWordToBelarusian(Connection connection, Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Введите слово на русском: ");
        String russianWord = scanner.nextLine();

        // SQL-запрос для поиска перевода слова на белорусский
        String translationQuery = "SELECT belarusianTranslation FROM russianBelarusianDictionary WHERE russianWord = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(translationQuery)) {
            preparedStatement.setString(1, russianWord);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String belarusianTranslation = resultSet.getString("belarusianTranslation");
                System.out.println("Перевод слова \"" + russianWord + "\" на белорусский язык: " + belarusianTranslation);
            } else {
                // Если перевод не найден, проверяем, может быть, слово уже на белорусском
                String checkBelarusianQuery = "SELECT russianWord FROM russianBelarusianDictionary WHERE belarusianTranslation = ?";
                try (PreparedStatement checkStatement = connection.prepareStatement(checkBelarusianQuery)) {
                    checkStatement.setString(1, russianWord);
                    ResultSet checkResultSet = checkStatement.executeQuery();

                    if (checkResultSet.next()) {
                        System.out.println("Слово \"" + russianWord + "\" уже на белорусском языке.");
                    } else {
                        System.out.println("Перевод слова \"" + russianWord + "\" на белорусский язык не найден.");
                    }
                }
            }
        }
    }


    // Поиск перевода на русский
    private static void translateWordToRussian(Connection connection, Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Введите слово на беларусском: ");
        String belarusianWord = scanner.nextLine();

        // SQL-запрос для поиска перевода слова на белорусский
        String translationQuery = "SELECT russianTranslation FROM belarusianRussianDictionary WHERE belarusianWord = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(translationQuery)) {
            preparedStatement.setString(1, belarusianWord);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String russianTranslation = resultSet.getString("russianTranslation");
                System.out.println("Перевод слова \"" + belarusianWord + "\" на русский язык: " + russianTranslation);
            } else {
                // Если перевод не найден, проверяем, может быть, слово уже на белорусском
                String checkRussianQuery = "SELECT belarusianWord FROM belarusianRussianDictionary WHERE russianTranslation = ?";
                try (PreparedStatement checkStatement = connection.prepareStatement(checkRussianQuery)) {
                    checkStatement.setString(1, belarusianWord);
                    ResultSet checkResultSet = checkStatement.executeQuery();

                    if (checkResultSet.next()) {
                        System.out.println("Слово \"" + belarusianWord + "\" уже на русском языке.");
                    } else {
                        System.out.println("Перевод слова \"" + belarusianWord + "\" на русский язык не найден.");
                    }
                }
            }
        }
    }





    // Вывод всех таблиц
    private static void outputAllDictionaryTabels(Connection connection) throws SQLException {
        // Запросы для выборки данных из каждой таблицы
        String selectRussianBelarusianDictionaryQuery = "SELECT * FROM russianBelarusianDictionary";
        String selectBelarusianRussianDictionaryQuery = "SELECT * FROM belarusianRussianDictionary";


        try (Statement statement = connection.createStatement()) {
            // Выборка данных из таблицы russianBelarusianDictionary
            System.out.println("Таблица 'русско-беларусского словаря':");
            ResultSet RussianBelarusianDictionaryResultSet = statement.executeQuery(selectRussianBelarusianDictionaryQuery);
            while (RussianBelarusianDictionaryResultSet.next()) {
                System.out.println(RussianBelarusianDictionaryResultSet.getLong("id") + ". " +
                        RussianBelarusianDictionaryResultSet.getString("russianWord") + " : " +
                        RussianBelarusianDictionaryResultSet.getString("belarusianTranslation"));
            }
            System.out.println();

            // Выборка данных из таблицы belarusianRussianDictionary
            System.out.println("Таблица 'беларусско-русского словаря':");
            ResultSet belarusianRussianDictionaryResultSet = statement.executeQuery(selectBelarusianRussianDictionaryQuery);
            while (belarusianRussianDictionaryResultSet.next()) {
                System.out.println(belarusianRussianDictionaryResultSet.getLong("id") + ". " +
                        belarusianRussianDictionaryResultSet.getString("belarusianWord") + " : " +
                        belarusianRussianDictionaryResultSet.getString("russianTranslation"));
            }
            System.out.println();
        }
    }
}