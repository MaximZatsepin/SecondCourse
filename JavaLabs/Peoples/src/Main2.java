import java.util.ArrayList;

public class Main2{
    public static void main(String[] args) {
        SupermarketInventory inventory = new SupermarketInventory();

        Toy toy = new Toy("Игрушки", "Медведь", "Китай", 149, "ООО \"Медведи\"", "Детские игрушки", "Плюшевый");
        inventory.addNewItem(toy);

        Fruit fruit = new Fruit("Фрукты", "Яблоко", "США", 99, "ООО \"Ferma\"", 14, 4);
        inventory.addNewItem(fruit);

        BigItem BigItem = new BigItem("Товары для дома", "Книжная полка", "Швеция", 1560, "Furniture inc.", 72, 36, 12);
        inventory.addNewItem(BigItem);

        inventory.printInventory();
    }
}

// Родительский класс "Супермаркет"
class SupermarketItem {
    String name_otdela;
    String name_product;
    String strana_proizvoditel;
    double price;
    String supplier;

    public SupermarketItem(String name_otdela, String name_product, String strana_proizvoditel, double price, String supplier) {
        this.name_otdela = name_otdela;
        this.name_product = name_product;
        this.strana_proizvoditel = strana_proizvoditel;
        this.price = price;
        this.supplier = supplier;
    }

    public String getData() {
        return "Отдел: " + name_otdela + ", Товар: " + name_product + ", Страна: " + strana_proizvoditel + ", Цена: " + price + ", Производитель: " + supplier;
    }
}

// Дочерний класс "Игрушки"
class Toy extends SupermarketItem {
    String ageGroup;
    String type;

    public Toy(String name_otdela, String name_product, String strana_proizvoditel, double price, String supplier, String ageGroup, String type) {
        super(name_otdela, name_product, strana_proizvoditel, price, supplier);
        this.ageGroup = ageGroup;
        this.type = type;
    }

    public String getData() {
        return super.getData() + ",\nAge Group: " + ageGroup + ", Type: " + type;
    }
}

// Дочерний класс "Фрукты"
class Fruit extends SupermarketItem {
    int time_hraneniya;
    int temperature;

    public Fruit(String name_otdela, String name_product, String strana_proizvoditel, double price, String supplier, int time_hraneniya, int temperature) {
        super(name_otdela, name_product, strana_proizvoditel, price, supplier);
        this.time_hraneniya = time_hraneniya;
        this.temperature = temperature;
    }

    public String getData() {
        return super.getData() + ",\nMax Storage Time: " + time_hraneniya + ", Storage Temperature: " + temperature;
    }
}

// Дочерний класс "Габаритный товар"
class BigItem extends SupermarketItem {
    double height;
    double width;
    double length;

    public BigItem(String name_otdela, String name_product, String strana_proizvoditel, double price, String supplier, double height, double width, double length) {
        super(name_otdela, name_product, strana_proizvoditel, price, supplier);
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public String getData() {
        return super.getData() + ",\nHeight: " + height + ", Width: " + width + ", Length: " + length;
    }
}

// Класс для хранения списка товаров с методами добавления нового товара и печати списка товаров
class SupermarketInventory {
    ArrayList<SupermarketItem> items;

    public SupermarketInventory() {
        items = new ArrayList<>();
    }

    public void addNewItem(SupermarketItem item) {
        items.add(item);
    }

    public void printInventory() {
        for (SupermarketItem item : items) {
            System.out.println(item.getData());
            System.out.println();
        }
    }
}


