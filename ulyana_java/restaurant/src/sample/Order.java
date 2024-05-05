package sample;

public class Order {
    private int id;
    private int userId;
    private String dishName;
    private String userName;
    private String userSurname;
    private String dishCategory;
    private double price;


    public Order(int userId, String dishName, String userName, String userSurname, String dishCategory, double price) {
        this.userId = userId;
        this.dishName = dishName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.dishCategory = dishCategory;
        this.price = price;
    }

    public Order(int id, int userId, String dishName, String userName, String userSurname, String dishCategory, double price) {
        this.id = id;
        this.userId = userId;
        this.dishName = dishName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.dishCategory = dishCategory;
        this.price = price;
    }


    public Order() { }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDishName() {
        return dishName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getDishCategory() {
        return dishCategory;
    }

    public double getPrice() {
        return price;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setDishCategory(String dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
