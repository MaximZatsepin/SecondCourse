// ProductManager.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> products;

    public ProductManager() {
        products = new ArrayList<Product>();
    }

    public void addProduct(String name, double price) {
        Product product = new Product(name, price);
        products.add(product);
    }

    public void showSortedProducts() {
        Collections.sort(products, Comparator.comparing(Product::getPrice));
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            System.out.println("\n1. Add Product\n2. Show Sorted Products\n3. Exit\nEnter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter product name:");
                    String name = scanner.next();
                    System.out.println("Enter product price:");
                    double price = scanner.nextDouble();
                    addProduct(name, price);
                    System.out.println("Product added successfully!");
                    break;
                case 2:
                    System.out.println("\nSorted Products:");
                    showSortedProducts();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
                    break;
            }
        }
        scanner.close();
    }
}