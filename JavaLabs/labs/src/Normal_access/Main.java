package Normal_access;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            // Выбор директории
            String working_dir = System.getProperty("user.dir");
            System.out.println("Working Directory = " + working_dir);

            // Создание файла (если его нет)
            File f1 = new File(working_dir + "\\data.txt");
            if(!f1.exists()){
                f1.createNewFile();
                System.out.println("File created!");
            } else System.out.println("File already exist!");

            // Открываю файл для чтения и записи
            RandomAccessFile file = new RandomAccessFile(f1, "rwd");
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("\nВыберите действие:" +
                                   "\n1 - Добавить информацию" +
                                   "\n2 - Прочитать файл" +
                                   "\n3 - Удалить информацию" + 
                                   "\n4 - Выйти из программы");
                switch (scanner.nextInt()) {
                    case 1:
                        add_data(scanner, file);
                        break;
                    case 2:
                        output_data(scanner, file);
                        break;
                    case 3:

                        break;
                    case 4:
                        System.out.println("Програма завершила работу.");
                        file.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введен неверный символ!");
                        break;
                }
            }

        }
        catch (IOException exception){
            System.out.println("End of file " + exception);
        }
    }

    private static void add_data(Scanner scanner, RandomAccessFile file){
        System.out.print("Введите количество записей: ");
        int n = scanner.nextInt();
        try{
            for(int i = 1; i <= n; i++){
                System.out.println("Введите данные для записи "+ i);
                System.out.println("Фамилия, Имя, Год рождения, Месяц рождения (через пробел)");
                scanner.nextLine();
                String data = scanner.nextLine();
                file.seek(file.length());
                file.writeChars(data); // тут пиздец
                }
        }
        catch(IOException exception){
            System.out.println("Ошибка добавления в файл. " + exception);
        }
    }

    private static void output_data(Scanner scanner, RandomAccessFile file){
        try{
            file.seek(0);
            String line = file.readLine();
            if(line != null) System.out.printf("| %-10s| %-10s | %-5s | %-5s |","Фамилия","Имя","Год","Месяц");
            while(line != null){
                String[] data = line.split("\s+");
                System.out.printf("| %-10s| %-10s | %-5s | %-5s |",data[0],data[1],data[2],data[3]);
            }
        }
        catch(IOException exception){
            System.out.println("Ошибка чтения файла. " + exception);
        }

    }
}
