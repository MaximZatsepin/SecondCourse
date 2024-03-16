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
// !!!!!!!!!!!!!!!! В СТРОКЕ 16 И 107 ДЛЯ Windows использовать \\... , ДЛЯ MacOS /... !!!!!!!!!!!!!!!!!!
            File f1 = new File(working_dir + "/data.txt");
            if(!f1.exists()){
                f1.createNewFile();
                System.out.println("File created!");
            } else System.out.println("File already exist!");

            // Открываю файл для чтения и записи
            RandomAccessFile file = new RandomAccessFile(f1, "rwd");
            Scanner scanner = new Scanner(System.in, "cp1251");

            // Цикл программы
            while(true){
                System.out.println("\nВыберите действие:" +
                                   "\n1 - Добавить информацию" +
                                   "\n2 - Прочитать файл" +
                                   "\n3 - Переписать в другой файл" + 
                                   "\n4 - Выйти из программы");
                switch (scanner.nextInt()) {
                    case 1:
                        add_data(scanner, file);
                        break;
                    case 2:
                        output_data(scanner, file);
                        break;
                    case 3:
                        rewrite_to_file(scanner,file, working_dir);
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

    // Добавление данных в файл
    private static void add_data(Scanner scanner, RandomAccessFile file){
        System.out.print("Введите количество записей: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        try{
            file.seek(file.length());
            System.out.println("Введите данные для записи");
            System.out.println("Фамилия, Имя, Год рождения, Месяц рождения (через пробел)");
            for(int i = 1; i <= n; i++){
                // scanner.nextLine();
                String data = scanner.nextLine();
                file.writeUTF(data);
                }
            System.out.println("Данные добавлены!");
        }
        catch(IOException exception){
            System.out.println("Ошибка добавления в файл. " + exception);
        }
    }

    // Вывод данных
    private static void output_data(Scanner scanner, RandomAccessFile file){
        try{
            file.seek(0);
            String line = file.readUTF();
            if(line != null) System.out.printf("| %-10s| %-10s | %-5s | %-5s |","Фамилия","Имя","Год","Месяц");
            while(true){
                try{
                    String[] data = line.split("\s+");
                    System.out.printf("\n| %-10s| %-10s | %-5s | %-5s |",data[0],data[1],data[2],data[3]);
                    line = file.readUTF();
                } catch(EOFException e){ break; }
            }

        }
        catch(IOException exception){
            System.out.println("Ошибка чтения файла. " + exception);
        }

    }

    // Переписать в другой файл
    private static void rewrite_to_file(Scanner scanner, RandomAccessFile file, String working_dir){
        System.out.println("Укажите название нового файла:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        File f2 = new File(working_dir + "/" + filename);
        if(f2.exists()){
            System.out.println("Ошибка: файл уже существует!");
            System.out.println(filename);
            return;
        }
        try{
            f2.createNewFile();
            RandomAccessFile file2 = new RandomAccessFile(f2, "rwd");

            file.seek(0);
            file2.seek(file2.length());
            String line = file.readUTF();
            while(line != null){
                try{
                    if(line.split("\s+")[3].toString().equals("01")){
                        System.out.println("пизда");
                        file2.writeUTF(line);
                    }
                    line = file.readUTF();
                } catch(EOFException e){ break; }
            }
            System.out.println("\nДанные успешно перезаписаны!");
            file2.close();
        }
        catch(IOException exception){
            System.out.println("Ошибка " + exception);
        }
    }
}
