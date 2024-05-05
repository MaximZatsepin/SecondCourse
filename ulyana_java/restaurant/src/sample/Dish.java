package sample;

public class Dish {
    private int id;
    private String dishName;
    private String mainIngredient;
    private String category;
    private String cookingTime;
    private int price;
    private int countDishes;
    private String status;

    public Dish(int id, String dishName, String mainIngredient, String category, String cookingTime, int price, int countDishes, String status) {
        this.id = id;
        this.dishName = dishName;
        this.mainIngredient = mainIngredient;
        this.category = category;
        this.cookingTime = cookingTime;
        this.price = price;
        this.countDishes = countDishes;
        this.status = status;
    }

    public Dish(String dishName, String mainIngredient, String category, String cookingTime, int price, int countDishes, String status) {
        this.dishName = dishName;
        this.mainIngredient = mainIngredient;
        this.category = category;
        this.cookingTime = cookingTime;
        this.price = price;
        this.countDishes = countDishes;
        this.status = status;
    }


    public Dish() { }


    public String getDishName() {
        return dishName;
    }

    public String getMainIngredient() { return mainIngredient; }

    public String getCategory() {
        return category;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public int getPrice() {
        return price;
    }

    public int getCountDishes() {
        return countDishes;
    }

    public String getStatus() {
        return status;
    }

    public int getId(){
        return id;
    }


    public void setDishName(String DishName) {
        this.dishName = DishName;
    }

    public void setMainIngredient(String mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCountDishes(int countDishes) {
        this.countDishes = countDishes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id){
        this.id = id;
    }
}
