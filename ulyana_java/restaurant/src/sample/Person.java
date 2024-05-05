package sample;

public class Person {
    private int userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private double money;
    private String role;


    public Person(int userId, String login, String password, String name, String surname, double money, String role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.role = role;
    }

    public Person(String login, String password){
        this.login = login;
        this.password = password;
        this.role = "guest";
    }


    public Person() { }


    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getMoney() {
        return money;
    }

    public String getRole() {
        return role;
    }

    public String getFullName(){
        if(name == null || surname == null) return login;
        if (!name.equals("") && !surname.equals("")){
            return surname + " " + name;
        }
        return login;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
