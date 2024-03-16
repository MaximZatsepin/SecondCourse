package Serialization;

import java.io.*;
import java.util.Scanner;

public class Main {
    static class Person implements Serializable {
        String surname;
        String name;
        int birthYear;
        int birthMonth;

        public Person(String surname, String name, int birthYear, int birthMonth) {
            this.surname = surname;
            this.name = name;
            this.birthYear = birthYear;
            this.birthMonth = birthMonth;
        }
    }

    public static void main(String[] args) {
        try {
            String working_dir = System.getProperty("user.dir");
            System.out.println("Working Directory = " + working_dir);

// !!!!!!!!!!!!!!!! В СТРОКЕ 27 И 108 ДЛЯ Windows использовать \\... , ДЛЯ MacOS /... !!!!!!!!!!!!!!!!!!
            File file = new File(working_dir + "/data.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created!");
            } else {
                System.out.println("File already exists!");
            }

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, true));
            Scanner scanner = new Scanner(System.in, "cp1251");

            while (true) {
                System.out.println("\nВыберите действие:" +
                        "\n1 - Добавить информацию" +
                        "\n2 - Прочитать файл" +
                        "\n3 - Переписать в другой файл" +
                        "\n4 - Выйти из программы");
                switch (scanner.nextInt()) {
                    case 1:
                        add_data(scanner, objectOutputStream);
                        break;
                    case 2:
                        output_data(file);
                        break;
                    case 3:
                        rewrite_to_file(scanner, file, working_dir);
                        break;
                    case 4:
                        System.out.println("Программа завершила работу.");
                        objectOutputStream.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введен неверный символ!");
                        break;
                }
            }

        } catch (IOException exception) {
            System.out.println("End of file " + exception);
        }
    }

    private static void add_data(Scanner scanner, ObjectOutputStream objectOutputStream) {
        System.out.print("Введите количество записей: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        try {
            System.out.println("Введите данные для записи");
            System.out.println("Фамилия, Имя, Год рождения, Месяц рождения (через пробел)");
            for (int i = 1; i <= n; i++) {
                String[] data = scanner.nextLine().split(" ");
                Person person = new Person(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                objectOutputStream.writeObject(person);
            }
            System.out.println("Данные добавлены!");
        } catch (IOException exception) {
            System.out.println("Ошибка добавления в файл. " + exception);
        }
    }

    private static void output_data(File file) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Person person = (Person) objectInputStream.readObject();
                    System.out.printf("| %-10s| %-10s | %-5d | %-5d |\n", person.surname, person.name, person.birthYear, person.birthMonth);
                } catch (EOFException | ClassNotFoundException e ) {
                    break;
                }
            }
        } catch (IOException exception) {
            System.out.println("Ошибка чтения файла. " + exception);
        }
    }

    private static void rewrite_to_file(Scanner scanner, File file, String working_dir) {
        System.out.println("Укажите название нового файла:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        File newFile = new File(working_dir + "/" + filename);
        if (newFile.exists()) {
            System.out.println("Ошибка: файл уже существует!");
            System.out.println(filename);
            return;
        }
        try (ObjectOutputStream newOutputStream = new ObjectOutputStream(new FileOutputStream(newFile));
             ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {

            System.out.println("Rewriting to new file:");
            while (true) {
                try {
                    Person person = (Person) objectInputStream.readObject();
                    if (person.birthMonth == 1) {
                        newOutputStream.writeObject(person);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("\nДанные успешно перезаписаны!");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Ошибка " + exception);
        }
    }
}
