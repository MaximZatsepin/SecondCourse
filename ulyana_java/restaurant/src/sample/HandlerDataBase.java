package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HandlerDataBase {
    Connection connection;

    public HandlerDataBase(){
        try {
            this.connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String urlConnection = "jdbc:mysql://127.0.0.1:3306/Restaurant";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(urlConnection, "root", "123456");
        return connection;
    }


    public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "login VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL," +
                    "name VARCHAR(50)," +
                    "surname VARCHAR(50)," +
                    "money DOUBLE DEFAULT 0," +
                    "role VARCHAR(50) NOT NULL" +
                    ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("createUsersTable(): Table created/consist!");
        }
    }


    public void addUser(Person user) throws SQLException {
        String addUserQuery = "INSERT INTO users (login, password, name, surname, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();
            System.out.println("addUser(): User added successfully!");
        }
    }


    public List<Person> getAllUsers() throws SQLException {
        List<Person> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int money = resultSet.getInt("money");
                String role = resultSet.getString("role");
                Person user = new Person(id, login, password, name, surname, money, role);
                users.add(user);
            }
        }
        return users;
    }

    public void updateUserById(Person user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET login = ?, password = ?, name = ?, surname = ?, money = ?, role = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setDouble(5,user.getMoney());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("User with ID '" + user.getUserId() + "' has been updated successfully.");
        }
    }

    public void deleteUserByLogin(String login) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE login = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            System.out.println("User with login '" + login + "' has been deleted successfully.");
        }
    }


    public Person getUserByLogin(String login) throws SQLException {
        Person user = null;
        String getUserQuery = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserQuery)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new Person(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getDouble("money"),
                            resultSet.getString("role")
                    );
                }
            }
        }
        return user;
    }


    public void updateUserByLogin(Person newuser) throws SQLException {
        String updateUserQuery = "UPDATE users SET login = ?, password = ?, name = ?, surname = ?, money = ?, role = ? WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserQuery)) {
            preparedStatement.setString(1,newuser.getLogin());
            preparedStatement.setString(2, newuser.getPassword());
            preparedStatement.setString(3, newuser.getName());
            preparedStatement.setString(4, newuser.getSurname());
            preparedStatement.setDouble(5, newuser.getMoney());
            preparedStatement.setString(6, newuser.getRole());
            preparedStatement.setString(7, newuser.getLogin());
            preparedStatement.executeUpdate();
            System.out.println("updateUserByLogin(): User updated successfully!");
        }
    }


    public void createDishTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS dish (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "dishName VARCHAR(50)," +
                    "mainIngredient VARCHAR(50)," +
                    "category VARCHAR(50)," +
                    "cookingTime VARCHAR(50)," +
                    "price INT," +
                    "countDishes INT," +
                    "status VARCHAR(50)" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Table 'dish' created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Dish> getAllDish() {
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM dish";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Dish dish = new Dish(
                        resultSet.getInt("id"),
                        resultSet.getString("dishName"),
                        resultSet.getString("mainIngredient"),
                        resultSet.getString("category"),
                        resultSet.getString("cookingTime"),
                        resultSet.getInt("price"),
                        resultSet.getInt("countDishes"),
                        resultSet.getString("status")
                );
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishes;
    }


    public void addDishToTable(Dish dish) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO dish (dishName, mainIngredient, category, cookingTime, price, countDishes, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, dish.getDishName());
            preparedStatement.setString(2, dish.getMainIngredient());
            preparedStatement.setString(3, dish.getCategory());
            preparedStatement.setString(4, dish.getCookingTime());
            preparedStatement.setInt(5, dish.getPrice());
            preparedStatement.setInt(6, dish.getCountDishes());
            preparedStatement.setString(7, dish.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Dish added successfully to the 'dish' table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteDishtByDishName(String DishName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM dish WHERE dishName = ?")) {
            preparedStatement.setString(1, DishName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dish with dishName " + DishName+ " deleted successfully from the 'dish' table");
            } else {
                System.out.println("No dish found with dishName " + DishName + " in the 'dish' table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateDish(Dish dish) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE dish SET dishName = ?, mainIngredient = ?, category = ?, cookingTime = ?, price = ?, countDishes = ?, status = ? WHERE id = ?")) {
            preparedStatement.setString(1, dish.getDishName());
            preparedStatement.setString(2, dish.getMainIngredient());
            preparedStatement.setString(3, dish.getCategory());
            preparedStatement.setString(4, dish.getCookingTime());
            preparedStatement.setInt(5, dish.getPrice());
            preparedStatement.setInt(6, dish.getCountDishes());
            preparedStatement.setString(7, dish.getStatus());
            preparedStatement.setInt(8, dish.getId());
            preparedStatement.executeUpdate();
            System.out.println("Dish with dishName " + dish.getDishName() + " updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getCategoryByDishName(String DishName) {
        String category = null;
        String query = "SELECT category FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = resultSet.getString("category");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    public String getCookingTimeByDishName(String DishName) {
        String cookingTime = null;
        String query = "SELECT cookingTime FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cookingTime = resultSet.getString("cookingTime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cookingTime;
    }


    public String getMainIngredientByDishName(String DishName) {
        String mainIngredient = null;
        String query = "SELECT mainIngredient FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                mainIngredient = resultSet.getString("mainIngredient");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mainIngredient;
    }

    public int getPriceByDishName(String DishName) {
        int price = 0;
        String query = "SELECT price FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }


    public int getCountOfDishByDishName(String DishName) {
        int countDishes = 0;
        String query = "SELECT countDishes FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countDishes = resultSet.getInt("countDishes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countDishes;
    }


    public int getCountDishesByDishName(String DishName) {
        int countDishes= 0;
        String query = "SELECT countDishes FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countDishes = resultSet.getInt("countDishes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countDishes;
    }


    public Dish getDishByDishName(String DishName) {
        String query = "SELECT * FROM dish WHERE dishName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, DishName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String mainIngredient = resultSet.getString("mainIngredient");
                    String category = resultSet.getString("category");
                    String cookingTime = resultSet.getString("cookingTime");
                    int price = resultSet.getInt("price");
                    int countDishes = resultSet.getInt("countDishes");
                    String status = resultSet.getString("status");
                    Dish dish = new Dish(id, DishName, mainIngredient, category , cookingTime, price, countDishes, status);
                    return dish;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void createOrderTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS orders (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "userId INT," +
                    "dishName VARCHAR(50)," +
                    "userName VARCHAR(50)," +
                    "userSurname VARCHAR(50)," +
                    "dishCategory VARCHAR(50)," +
                    "price DOUBLE" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Table 'order' created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addOrder(Order newOrder) {
        String query = "INSERT INTO orders (userId, dishName, userName, userSurname, dishCategory, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, newOrder.getUserId());
            preparedStatement.setString(2, newOrder.getDishName());
            preparedStatement.setString(3, newOrder.getUserName());
            preparedStatement.setString(4, newOrder.getUserSurname());
            preparedStatement.setString(5, newOrder.getDishCategory());
            preparedStatement.setDouble(6, newOrder.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Order added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String dishName = resultSet.getString("dishName");
                String userName = resultSet.getString("userName");
                String userSurname = resultSet.getString("userSurname");
                String dishCategory = resultSet.getString("dishCategory");
                double price = resultSet.getDouble("price");
                Order order = new Order(id, userId, dishName, userName, userSurname, dishCategory, price);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    public List<Order> getAllOrdersByUserId(String userID) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int userId = resultSet.getInt("userId");
                    String dishName = resultSet.getString("dishName");
                    String userName = resultSet.getString("userName");
                    String userSurname = resultSet.getString("userSurname");
                    String dishCategory = resultSet.getString("dishCategory");
                    double price = resultSet.getDouble("price");
                    Order order= new Order(id, userId, dishName, userName, userSurname, dishCategory, price);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
