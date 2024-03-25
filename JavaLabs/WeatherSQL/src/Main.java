
import java.sql.*;
// import java.util.Scanner;
import java.util.Scanner;

public class Main {

    static final String url = "jdbc:mysql://localhost:3306/Weather";
    static final String dbUser = "*****";
    static final String password = "*********";

    
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            createTablesIfNotExist(connection);

            Scanner scanner = new Scanner(System.in);

            // Цикл программы
            while(true){
                System.out.println("\nВыберите действие:" +
                                   "\n1 - Добавить данные о погоде" +
                                   "\n2 - Добавить данные о регионе" +
                                   "\n3 - Добавить данные о типе жителей" +
                                   "\n4 - Вывести сведения о погоде в заданном регионе" +
                                   "\n5 - Вывести даты, когда в заданном регионе шел снег\n" + 
                                     "    и температура была ниже заданной отрицательной." + 
                                   "\n6 - Вывести информацию о погоде в регионах,\n" + 
                                     "    жители которых общаются на заданном языке." +
                                   "\n7 - Вывести среднюю температуру\n" +
                                     "    в регионах с площадью больше заданной." +
                                   "\n8 - Вывод всех таблиц" + 
                                   "\n9 - Выход из программы");
                switch (scanner.nextInt()) {
                    case 1:
                        addWeather(connection, scanner);
                        break;
                    case 2:
                        addRegion(connection, scanner);
                        break;
                    case 3:
                        addVillagersType(connection, scanner);
                        break;
                    case 4:
                        outputWeatherForRegion(connection, scanner);
                        break;
                    case 5:
                        outputSnowDates(connection, scanner);
                        break;
                    case 6:
                        outputWeatherForLanguage(connection, scanner);
                        break;
                    case 7:
                        outputAverageTemperatureForRegionWithAreaGreaterThan(connection, scanner);
                        break;
                    case 8:
                        displayTables(connection);
                        break; 
                    case 9:
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

    private static void createTablesIfNotExist(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Проверка существования таблицы погоды
            ResultSet resultSetProducts = connection.getMetaData().getTables(null, null, "weather", null);
            if (!resultSetProducts.next()) { // Если таблицы не существует
                // Создание таблицы продуктов
                String createProductTableQuery = "CREATE TABLE weather (" +
                        "idweather BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "idregion INT NOT NULL," +
                        "data VARCHAR(255) NOT NULL," +
                        "temperature DOUBLE NOT NULL," +
                        "rainfall VARCHAR(255) NOT NULL)";
                statement.executeUpdate(createProductTableQuery);
                System.out.println("Таблица weather успешно создана");
            }
            else System.out.println("Таблица weather загружена!");

            // Проверка существования таблицы регионов
            resultSetProducts = connection.getMetaData().getTables(null, null, "regions", null);
            if (!resultSetProducts.next()) { // Если таблицы не существует
                // Создание таблицы продуктов
                String createProductTableQuery = "CREATE TABLE regions (" +
                        "idregion BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "square DOUBLE NOT NULL," +
                        "idvillagerstype INT NOT NULL)";
                statement.executeUpdate(createProductTableQuery);
                System.out.println("Таблица regions успешно создана");
            }
            else System.out.println("Таблица regions загружена!");

            // Проверка существования таблицы типов жителей
            resultSetProducts = connection.getMetaData().getTables(null, null, "villagerstype", null);
            if (!resultSetProducts.next()) { // Если таблицы не существует
                // Создание таблицы продуктов
                String createProductTableQuery = "CREATE TABLE villagerstype (" +
                        "idvillagerstype BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "language VARCHAR(255) NOT NULL)";
                statement.executeUpdate(createProductTableQuery);
                System.out.println("Таблица villagerstype успешно создана");
            }
            else System.out.println("Таблица villagerstype загружена!");
        }
    }
    
    // Добавление записи в таблицу погоды
    private static void addWeather(Connection connection, Scanner scanner) throws SQLException {
        String addWeatherQuery = "INSERT INTO weather (idregion, data, temperature, rainfall) VALUES (?,?,?,?)";
        scanner.nextLine();
        System.out.print("Введите id региона: ");
        int weather_idregion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите дату: ");
        String weather_data = scanner.nextLine();
        System.out.print("Введите температуру: ");
        Double weather_temperature = scanner.nextDouble();
        System.out.print("Введите погоду, которая была в регионе: ");
        scanner.nextLine();
        String weather_rainfall = scanner.nextLine();
        try(PreparedStatement preparedStatement = connection.prepareStatement(addWeatherQuery)){
            preparedStatement.setInt(1, weather_idregion);
            preparedStatement.setString(2, weather_data);
            preparedStatement.setDouble(3, weather_temperature);
            preparedStatement.setString(4, weather_rainfall);
            preparedStatement.executeUpdate();
        }
        System.out.println("\nЗапись добавлена!");
    }

    // Добавление записи в таблицу регионов
    private static void addRegion(Connection connection, Scanner scanner) throws SQLException {
        String addRegionQuery = "INSERT INTO regions (name, square, idvillagerstype) VALUES (?,?,?)";
        scanner.nextLine();
        System.out.print("Введите имя региона: ");
        String region_name = scanner.nextLine();
        System.out.print("Введите площадь региона: ");
        Double region_square = scanner.nextDouble();
        System.out.print("Введите id типа жителей: ");
        int region_idvillagerstype = scanner.nextInt();
        try(PreparedStatement preparedStatement = connection.prepareStatement(addRegionQuery)){
            preparedStatement.setString(1, region_name);
            preparedStatement.setDouble(2, region_square);
            preparedStatement.setInt(3, region_idvillagerstype);
            preparedStatement.executeUpdate();
        }
        System.out.println("\nЗапись добавлена!");
    }
    
    // Добавление записи в таблицу типов жителей
    private static void addVillagersType(Connection connection, Scanner scanner) throws SQLException {
        String addVillagersTypeQuery = "INSERT INTO villagerstype (name, language) VALUES (?, ?)";
    
        scanner.nextLine();
        System.out.print("Введите тип жителей: ");
        String name = scanner.nextLine();
        System.out.print("Введите язык, которым общаются жители этого типа: ");
        String language = scanner.nextLine();
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(addVillagersTypeQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, language);
            preparedStatement.executeUpdate();
        }
    
        System.out.println("Тип жителей успешно добавлен в таблицу 'villagerstype'.");
    }
    
    // Вывод метода №4
    private static void outputWeatherForRegion(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Введите название региона: ");
        scanner.nextLine();
        String regionName = scanner.nextLine();
    
        // SQL-запрос для получения idregion по имени региона
        String getRegionIdQuery = "SELECT idregion FROM regions WHERE name = ?";
        // SQL-запрос для получения информации о погоде в заданном регионе
        String getWeatherForRegionQuery = "SELECT data, temperature, rainfall " +
                                          "FROM weather " +
                                          "WHERE idregion = ?";
    
        try (PreparedStatement getRegionIdStatement = connection.prepareStatement(getRegionIdQuery);
             PreparedStatement getWeatherForRegionStatement = connection.prepareStatement(getWeatherForRegionQuery)) {
    
            // Получаем idregion по имени региона
            getRegionIdStatement.setString(1, regionName);
            ResultSet regionIdResultSet = getRegionIdStatement.executeQuery();
    
            if (regionIdResultSet.next()) {
                int regionId = regionIdResultSet.getInt("idregion");
    
                // Получаем информацию о погоде в заданном регионе
                getWeatherForRegionStatement.setInt(1, regionId);
                ResultSet weatherResultSet = getWeatherForRegionStatement.executeQuery();
    
                if (weatherResultSet.next()) {
                    System.out.println("Сведения о погоде в регионе " + regionName + ":");
                    do {
                        System.out.println("Дата: " + weatherResultSet.getString("data") + ", " +
                                           "Температура: " + weatherResultSet.getDouble("temperature") + ", " +
                                           "Погода: " + weatherResultSet.getString("rainfall"));
                    } while (weatherResultSet.next());
                } else {
                    System.out.println("Нет информации о погоде в регионе " + regionName + ".");
                }
            } else {
                System.out.println("Регион с именем " + regionName + " не найден.");
            }
        }
    }    

    // Вывод метода №5
    private static void outputSnowDates(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Введите название региона: ");
        scanner.nextLine();
        String region_name = scanner.nextLine();
        System.out.print("Введите отрицательную температуру: ");
        double negative_temperature = scanner.nextDouble();
    
        String getRegionIdQuery = "SELECT idregion FROM regions WHERE name = ?";
        String getSnowDatesQuery = "SELECT data FROM weather WHERE idregion = ? AND rainfall = 'Снег' AND temperature < ?";
        
        try (PreparedStatement getRegionStatement = connection.prepareStatement(getRegionIdQuery);
             PreparedStatement getSnowDatesStatement = connection.prepareStatement(getSnowDatesQuery)) {
            
            getRegionStatement.setString(1, region_name);
            ResultSet resultSet = getRegionStatement.executeQuery(); // Находим id региона по его названию из таблицы regions
    
            if (resultSet.next()) {
                int regionId = resultSet.getInt("idregion");
                getSnowDatesStatement.setInt(1, regionId);
                getSnowDatesStatement.setDouble(2, negative_temperature);
                ResultSet snowDatesResultSet = getSnowDatesStatement.executeQuery(); // Находим даты, когда шел снег и температура была ниже заданной
    
                if (snowDatesResultSet.next()) {
                    System.out.println("Дата(ы), когда в регионе '" + region_name + "' шел снег и температура была ниже " + negative_temperature + " градусов:");
                    do {
                        String snowDate = snowDatesResultSet.getString("data");
                        System.out.println(snowDate);
                    } while (snowDatesResultSet.next());
                } else {
                    System.out.println("\nВ заданном регионе не найдено дат, когда шел снег и температура была ниже " + negative_temperature + " градусов.");
                }
            } else {
                System.out.println("\nРегион не найден / не добавлен в regions!");
            }
        }
    }

    // Вывод метода №6
    private static void outputWeatherForLanguage(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Введите язык, по которому общаются жители регионов: ");
        scanner.nextLine();
        String language = scanner.nextLine();
    
        // SQL-запрос для получения id типов жителей по заданному языку
        String getVillagersTypeIdQuery = "SELECT idvillagerstype FROM villagerstype WHERE language = ?";
        // SQL-запрос для получения idregion по id типов жителей
        String getRegionIdQuery = "SELECT idregion FROM regions WHERE idvillagerstype = ?";
        // SQL-запрос для получения информации о погоде по idregion
        String getWeatherInfoQuery = "SELECT r.name AS region_name, w.data, w.temperature, w.rainfall " +
                                      "FROM weather w " +
                                      "INNER JOIN regions r ON w.idregion = r.idregion " +
                                      "WHERE w.idregion = ?";
    
        try (PreparedStatement getVillagersTypeIdStatement = connection.prepareStatement(getVillagersTypeIdQuery);
             PreparedStatement getRegionIdStatement = connection.prepareStatement(getRegionIdQuery);
             PreparedStatement getWeatherInfoStatement = connection.prepareStatement(getWeatherInfoQuery)) {
    
            // Получение id типов жителей по заданному языку
            getVillagersTypeIdStatement.setString(1, language);
            ResultSet villagersTypeIdResultSet = getVillagersTypeIdStatement.executeQuery();
    
            while (villagersTypeIdResultSet.next()) {
                int villagersTypeId = villagersTypeIdResultSet.getInt("idvillagerstype");
    
                // Получение idregion по id типов жителей
                getRegionIdStatement.setInt(1, villagersTypeId);
                ResultSet regionIdResultSet = getRegionIdStatement.executeQuery();
    
                while (regionIdResultSet.next()) {
                    int regionId = regionIdResultSet.getInt("idregion");
    
                    // Получение информации о погоде по idregion
                    getWeatherInfoStatement.setInt(1, regionId);
                    ResultSet weatherInfoResultSet = getWeatherInfoStatement.executeQuery();
    
                    if (weatherInfoResultSet.next()) {
                        System.out.println("Информация о погоде в регионах, жители которых общаются на " + language + " языке:");
                        do {
                            System.out.println("Регион: " + weatherInfoResultSet.getString("region_name") + ", " +
                                               "Дата: " + weatherInfoResultSet.getString("data") + ", " +
                                               "Температура: " + weatherInfoResultSet.getDouble("temperature") + ", " +
                                               "Погода: " + weatherInfoResultSet.getString("rainfall"));
                        } while (weatherInfoResultSet.next());
                    } else {
                        System.out.println("Нет информации о погоде в регионах, жители которых общаются на " + language + " языке.");
                    }
                }
            }
        }
    }
    
    // Вывод метода №7
    private static void outputAverageTemperatureForRegionWithAreaGreaterThan(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Введите минимальную площадь региона: ");
        double minArea = scanner.nextDouble();
    
        // SQL-запрос для получения id регионов с площадью больше заданной
        String getRegionIdsQuery = "SELECT idregion FROM regions WHERE square > ?";
        // SQL-запрос для получения средней температуры в регионе
        String getAverageTemperatureQuery = "SELECT AVG(temperature) AS average_temperature FROM weather WHERE idregion = ?";
    
        try (PreparedStatement getRegionIdsStatement = connection.prepareStatement(getRegionIdsQuery);
             PreparedStatement getAverageTemperatureStatement = connection.prepareStatement(getAverageTemperatureQuery)) {
    
            getRegionIdsStatement.setDouble(1, minArea);
            ResultSet regionIdsResultSet = getRegionIdsStatement.executeQuery();
    
            while (regionIdsResultSet.next()) {
                int regionId = regionIdsResultSet.getInt("idregion");
    
                getAverageTemperatureStatement.setInt(1, regionId);
                ResultSet averageTemperatureResultSet = getAverageTemperatureStatement.executeQuery();
    
                if (averageTemperatureResultSet.next()) {
                    double averageTemperature = averageTemperatureResultSet.getDouble("average_temperature");
                    System.out.println("Средняя температура в регионе с id " + regionId + " (площадь > " + minArea + "): " + averageTemperature);
                } else {
                    System.out.println("Нет данных о погоде в регионе с id " + regionId + ".");
                }
            }
        }
    }    

    // Вывод всех таблиц
    private static void displayTables(Connection connection) throws SQLException {
        // Запросы для выборки данных из каждой таблицы
        String selectWeatherQuery = "SELECT * FROM weather";
        String selectRegionsQuery = "SELECT * FROM regions";
        String selectVillagersTypeQuery = "SELECT * FROM villagerstype";
        
        try (Statement statement = connection.createStatement()) {
            // Выборка данных из таблицы weather
            System.out.println("Таблица 'weather':");
            ResultSet weatherResultSet = statement.executeQuery(selectWeatherQuery);
            while (weatherResultSet.next()) {
                System.out.println("idweather: " + weatherResultSet.getLong("idweather") + ", " +
                                   "idregion: " + weatherResultSet.getInt("idregion") + ", " +
                                   "data: " + weatherResultSet.getString("data") + ", " +
                                   "temperature: " + weatherResultSet.getDouble("temperature") + ", " +
                                   "rainfall: " + weatherResultSet.getString("rainfall"));
            }
            System.out.println();
    
            // Выборка данных из таблицы regions
            System.out.println("Таблица 'regions':");
            ResultSet regionsResultSet = statement.executeQuery(selectRegionsQuery);
            while (regionsResultSet.next()) {
                System.out.println("idregion: " + regionsResultSet.getLong("idregion") + ", " +
                                   "name: " + regionsResultSet.getString("name") + ", " +
                                   "square: " + regionsResultSet.getDouble("square") + ", " +
                                   "idvillagerstype: " + regionsResultSet.getInt("idvillagerstype"));
            }
            System.out.println();
    
            // Выборка данных из таблицы villagerstype
            System.out.println("Таблица 'villagerstype':");
            ResultSet villagersTypeResultSet = statement.executeQuery(selectVillagersTypeQuery);
            while (villagersTypeResultSet.next()) {
                System.out.println("idvillagerstype: " + villagersTypeResultSet.getLong("idvillagerstype") + ", " +
                                   "name: " + villagersTypeResultSet.getString("name") + ", " +
                                   "language: " + villagersTypeResultSet.getString("language"));
            }
        }
    }
    
}