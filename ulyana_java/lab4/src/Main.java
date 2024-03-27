import java.util.ArrayList;
import java.util.Date;

// Родительский класс "Автомобили"
class Automobile {
    String brand;
    int year;
    double price;
    String configuration;
    String country;
    Date saleDate;
    String buyerName;

    public Automobile(String brand, int year, double price, String configuration, String country, Date saleDate, String buyerName) {
        this.brand = brand;
        this.year = year;
        this.price = price;
        this.configuration = configuration;
        this.country = country;
        this.saleDate = saleDate;
        this.buyerName = buyerName;
    }
}

// Дочерний класс "Поддержанные авто"
class UsedCar extends Automobile {
    String condition;
    String ownerName;
    int mileage;

    public UsedCar(String brand, int year, double price, String configuration, String country, Date saleDate, String buyerName, String condition, String ownerName, int mileage) {
        super(brand, year, price, configuration, country, saleDate, buyerName);
        this.condition = condition;
        this.ownerName = ownerName;
        this.mileage = mileage;
    }
}

// Дочерний класс "Спортивные"
class SportsCar extends Automobile {
    int secondsToSixty;
    double engineVolume;
    double power;

    public SportsCar(String brand, int year, double price, String configuration, String country, Date saleDate, String buyerName, int secondsToSixty, double engineVolume, double power) {
        super(brand, year, price, configuration, country, saleDate, buyerName);
        this.secondsToSixty = secondsToSixty;
        this.engineVolume = engineVolume;
        this.power = power;
    }
}

// Дочерний класс "Спецтехника"
class SpecialEquipment extends Automobile {
    String type;
    double weight;
    String dimensions;

    public SpecialEquipment(String brand, int year, double price, String configuration, String country, Date saleDate, String buyerName, String type, double weight, String dimensions) {
        super(brand, year, price, configuration, country, saleDate, buyerName);
        this.type = type;
        this.weight = weight;
        this.dimensions = dimensions;
    }
}

// Класс для хранения списка проданных автомобилей
class SoldAutomobiles {
    ArrayList<Automobile> automobiles = new ArrayList<>();

    // Метод для добавления нового автомобиля
    public void addAutomobile(Automobile auto) {
        automobiles.add(auto);
    }

    // Метод для печати списка автомобилей
    public void printAutomobiles() {
        for (Automobile auto : automobiles) {
            System.out.println("Brand: " + auto.brand);
            System.out.println("Year: " + auto.year);
            System.out.println("Price: " + auto.price);
            System.out.println("Configuration: " + auto.configuration);
            System.out.println("Country: " + auto.country);
            System.out.println("Sale Date: " + auto.saleDate);
            System.out.println("Buyer Name: " + auto.buyerName);

            if (auto instanceof UsedCar) {
                UsedCar usedCar = (UsedCar) auto;
                System.out.println("Condition: " + usedCar.condition);
                System.out.println("Owner Name: " + usedCar.ownerName);
                System.out.println("Mileage: " + usedCar.mileage);
            } else if (auto instanceof SportsCar) {
                SportsCar sportsCar = (SportsCar) auto;
                System.out.println("Seconds to Sixty: " + sportsCar.secondsToSixty);
                System.out.println("Engine Volume: " + sportsCar.engineVolume);
                System.out.println("Power: " + sportsCar.power);
            } else if (auto instanceof SpecialEquipment) {
                SpecialEquipment specialEquipment = (SpecialEquipment) auto;
                System.out.println("Type: " + specialEquipment.type);
                System.out.println("Weight: " + specialEquipment.weight);
                System.out.println("Dimensions: " + specialEquipment.dimensions);
            }

            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Пример использования
        SoldAutomobiles soldAutomobiles = new SoldAutomobiles();

        // Добавление новых автомобилей
        soldAutomobiles.addAutomobile(new UsedCar("Toyota", 2018, 15000, "Basic", "Japan", new Date(), "John Doe", "Good", "Alice Doe", 30000));
        soldAutomobiles.addAutomobile(new SportsCar("Ferrari", 2022, 250000, "Luxury", "Italy", new Date(), "Jane Smith", 3, 6.3, 700));
        soldAutomobiles.addAutomobile(new SpecialEquipment("Caterpillar", 2020, 100000, "Heavy Duty", "USA", new Date(), "Bob Johnson", "Construction", 50000, "10x5x5 meters"));

        // Печать списка автомобилей
        soldAutomobiles.printAutomobiles();
    }
}
