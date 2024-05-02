
// БЕЗ ВЕРИАЛИЗАЦИИ
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Создаем файл
            File file = new File("products.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            // Открываем файл для записи с произвольным доступом
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            System.out.print("Введите количество товаров для записи в файл: ");
            int numberOfProducts = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numberOfProducts; i++) {
                System.out.println("Введите информацию о товаре " + (i + 1) + ":");
                System.out.print("Наименование: ");
                String name = scanner.nextLine();
                System.out.print("Производитель: ");
                String manufacturer = scanner.nextLine();
                System.out.print("Количество единиц: ");
                int quantity = scanner.nextInt();
                System.out.print("Цена: ");
                double price = scanner.nextDouble();
                scanner.nextLine();

                randomAccessFile.writeUTF(name.trim());
                randomAccessFile.writeUTF(manufacturer.trim());
                randomAccessFile.writeInt(quantity);
                randomAccessFile.writeDouble(price);
                randomAccessFile.writeUTF("\n");
            }

            // Создаем новый файл для записи информации о товарах с ценой выше 1000 рублей
            File expensiveProductsFile = new File("expensive_products.txt");
            if (!expensiveProductsFile.exists()) {
                expensiveProductsFile.createNewFile();
            }

            RandomAccessFile expensiveProductsRandomAccessFile = new RandomAccessFile(expensiveProductsFile, "rw");
            randomAccessFile.seek(0);

            for (int i = 0; i < numberOfProducts; i++) {
                String name = randomAccessFile.readUTF();
                String manufacturer = randomAccessFile.readUTF();
                int quantity = randomAccessFile.readInt();
                double price = randomAccessFile.readDouble();
                randomAccessFile.readUTF();

                if (price > 1000) {
                    expensiveProductsRandomAccessFile.writeUTF(name.trim());
                    expensiveProductsRandomAccessFile.writeUTF(manufacturer.trim());
                    expensiveProductsRandomAccessFile.writeInt(quantity);
                    expensiveProductsRandomAccessFile.writeDouble(price);
                    expensiveProductsRandomAccessFile.writeUTF("\n");
                }
            }


            randomAccessFile.close();
            expensiveProductsRandomAccessFile.close();

            System.out.println("Информация о товарах с ценой выше 1000 рублей записана в файл 'expensive_products.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









// С ВЕРИАЛИЗАЦИЕЙ

//import java.io.*;
//import java.util.Scanner;
//
//class Product implements Serializable {
//    private String name;
//    private String manufacturer;
//    private int quantity;
//    private double price;
//
//    public Product(String name, String manufacturer, int quantity, double price) {
//        this.name = name;
//        this.manufacturer = manufacturer;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "Наименование: " + name + ", Производитель: " + manufacturer +
//                ", Количество единиц: " + quantity + ", Цена: " + price;
//    }
//
//    public boolean isExpensive() {
//        return price > 1000;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            Scanner scanner = new Scanner(System.in);
//
//            // Создаем список товаров
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("products.ser"));
//
//            System.out.print("Введите количество товаров для записи в файл: ");
//            int numberOfProducts = scanner.nextInt();
//            scanner.nextLine();
//
//            for (int i = 0; i < numberOfProducts; i++) {
//                System.out.println("Введите информацию о товаре " + (i + 1) + ":");
//                System.out.print("Наименование: ");
//                String name = scanner.nextLine();
//                System.out.print("Производитель: ");
//                String manufacturer = scanner.nextLine();
//                System.out.print("Количество единиц: ");
//                int quantity = scanner.nextInt();
//                System.out.print("Цена: ");
//                double price = scanner.nextDouble();
//                scanner.nextLine();
//
//                Product product = new Product(name, manufacturer, quantity, price);
//                objectOutputStream.writeObject(product);
//            }
//            objectOutputStream.close();
//
//            // Чтение и вывод информации о дорогих товарах
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("products.ser"));
//            ObjectOutputStream expensiveOutputStream = new ObjectOutputStream(new FileOutputStream("expensive_products.ser"));
//
//            System.out.println("Информация о товарах с ценой выше 1000 рублей:");
//            for (int i = 0; i < numberOfProducts; i++) {
//                Product product = (Product) objectInputStream.readObject();
//                if (product.isExpensive()) {
//                    System.out.println(product);
//                    expensiveOutputStream.writeObject(product);
//                }
//            }
//
//            objectInputStream.close();
//            expensiveOutputStream.close();
//
//            System.out.println("Информация о дорогих товарах записана в файл 'expensive_products.ser'.");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}

