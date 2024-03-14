package computers;

import java.io.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

class ComputerUsage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Integer, List<ComputerRecord>> records;

    public ComputerUsage() {
        records = new HashMap<>();
    }

    public void addRecord(int computerNumber, ComputerRecord record) {
        records.computeIfAbsent(computerNumber, k -> new ArrayList<>()).add(record);
    }

    public Map<Integer, List<ComputerRecord>> getRecords() {
        return records;
    }
}

class ComputerRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String clientName;
    private String date;
    private String startTime;
    private String endTime;

    public ComputerRecord(String clientName, String date, String startTime, String endTime) {
        this.clientName = clientName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String name){
        clientName = name;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String data){
        date = data;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String time){
        startTime = time;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String time){
        endTime = time;
    }
}

public class Main {
    private static final String FILENAME = "computer_usage.bin";

    public static void main(String[] args) {
        ComputerUsage computerUsage = loadFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Просмотреть данные");
            System.out.println("2. Добавить новую запись");
            System.out.println("3. Редактировать существующую запись");
            System.out.println("4. Удалить запись");
            System.out.println("5. Получить отчёт о времени использования каждого компьютера на указанную дату");
            System.out.println("6. Получить информацию об использовании выбранного компьютера каждым пользователем на выбранную дату");
            System.out.println("7. Выйти из программы");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewData(computerUsage);
                    break;
                case 2:
                    addRecord(scanner, computerUsage);
                    break;
                case 3:
                    editRecord(scanner, computerUsage);
                    break;
                case 4:
                    deleteRecord(scanner, computerUsage);
                    break;
                case 5:
                    reportByDate(computerUsage, scanner);
                    break;
                case 6:
                    reportByComputerAndDate(computerUsage, scanner);
                    break;
                case 7:
                    saveToFile(computerUsage);
                    System.out.println("Данные сохранены. Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите действие из списка.");
            }
        }
    }

    private static ComputerUsage loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (ComputerUsage) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Создан новый объект для хранения данных.");
            return new ComputerUsage();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке данных из файла. Создан новый объект для хранения данных.");
            return new ComputerUsage();
        }
    }

    private static void saveToFile(ComputerUsage computerUsage) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(computerUsage);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в файл.");
            e.printStackTrace();
        }
    }

    private static void viewData(ComputerUsage computerUsage) {
        for (Map.Entry<Integer, List<ComputerRecord>> entry : computerUsage.getRecords().entrySet()) {
            int computerNumber = entry.getKey();
            List<ComputerRecord> records = entry.getValue();
            System.out.println("Компьютер №" + computerNumber + ":");
            for (ComputerRecord record : records) {
                System.out.println("Фамилия и инициалы клиента: " + record.getClientName());
                System.out.println("Дата: " + record.getDate());
                System.out.println("Время начала работы: " + record.getStartTime());
                System.out.println("Время окончания работы: " + record.getEndTime());
                System.out.println();
            }
        }
    }

    private static void addRecord(Scanner scanner, ComputerUsage computerUsage) {
        System.out.print("Введите номер компьютера: ");
        int computerNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Введите фамилию и инициалы клиента: ");
        String clientName = scanner.nextLine();

        System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
        String date = scanner.nextLine();

        System.out.print("Введите время начала работы (ЧЧ:ММ:СС): ");
        String startTime = scanner.nextLine();

        System.out.print("Введите время окончания работы (ЧЧ:ММ:СС): ");
        String endTime = scanner.nextLine();

        ComputerRecord record = new ComputerRecord(clientName, date, startTime, endTime);
        computerUsage.addRecord(computerNumber, record);
        saveToFile(computerUsage);
        System.out.println("Запись добавлена.");
    }

    private static void editRecord(Scanner scanner, ComputerUsage computerUsage) {
        System.out.print("Введите номер компьютера: ");
        int computerNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        if (!computerUsage.getRecords().containsKey(computerNumber)) {
            System.out.println("Записей для компьютера с номером " + computerNumber + " не найдено.");
            return;
        }
    
        List<ComputerRecord> records = computerUsage.getRecords().get(computerNumber);
        System.out.println("Список записей для компьютера №" + computerNumber + ":");
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i).getClientName() + ", " +
                    records.get(i).getDate() + ", " +
                    records.get(i).getStartTime() + " - " +
                    records.get(i).getEndTime());
        }
    
        System.out.print("Выберите номер записи для редактирования: ");
        int recordIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        if (recordIndex < 1 || recordIndex > records.size()) {
            System.out.println("Некорректный номер записи.");
            return;
        }
    
        System.out.print("Введите новую фамилию и инициалы клиента: ");
        String clientName = scanner.nextLine();
    
        System.out.print("Введите новую дату (ГГГГ-ММ-ДД): ");
        String date = scanner.nextLine();
    
        System.out.print("Введите новое время начала работы (ЧЧ:ММ:СС): ");
        String startTime = scanner.nextLine();
    
        System.out.print("Введите новое время окончания работы (ЧЧ:ММ:СС): ");
        String endTime = scanner.nextLine();
    
        records.get(recordIndex - 1).setClientName(clientName);
        records.get(recordIndex - 1).setDate(date);
        records.get(recordIndex - 1).setStartTime(startTime);
        records.get(recordIndex - 1).setEndTime(endTime);
    
        saveToFile(computerUsage);
        System.out.println("Запись отредактирована.");
    }
    

    private static void deleteRecord(Scanner scanner, ComputerUsage computerUsage) {
        System.out.print("Введите номер компьютера: ");
        int computerNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        if (!computerUsage.getRecords().containsKey(computerNumber)) {
            System.out.println("Записей для компьютера с номером " + computerNumber + " не найдено.");
            return;
        }
    
        List<ComputerRecord> records = computerUsage.getRecords().get(computerNumber);
        System.out.println("Список записей для компьютера №" + computerNumber + ":");
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i).getClientName() + ", " +
                    records.get(i).getDate() + ", " +
                    records.get(i).getStartTime() + " - " +
                    records.get(i).getEndTime());
        }
    
        System.out.print("Выберите номер записи для удаления: ");
        int recordIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        if (recordIndex < 1 || recordIndex > records.size()) {
            System.out.println("Некорректный номер записи.");
            return;
        }
    
        records.remove(recordIndex - 1);
    
        saveToFile(computerUsage);
        System.out.println("Запись удалена.");
    }
    

    private static void reportByDate(ComputerUsage computerUsage, Scanner scanner) {
        System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
        String targetDate = scanner.nextLine();

        System.out.println("Отчет о времени использования компьютеров на дату " + targetDate + ":");
        for (Map.Entry<Integer, List<ComputerRecord>> entry : computerUsage.getRecords().entrySet()) {
            int computerNumber = entry.getKey();
            List<ComputerRecord> records = entry.getValue();
            int usageMinutes = 0;
            for (ComputerRecord record : records) {
                if (record.getDate().equals(targetDate)) {
                    LocalTime startTime = LocalTime.parse(record.getStartTime());
                    LocalTime endTime = LocalTime.parse(record.getEndTime());
                    long minutes = startTime.until(endTime, ChronoUnit.MINUTES);
                    usageMinutes += minutes;
                }
            }
            System.out.println("Компьютер №" + computerNumber + ": " + usageMinutes + " минут");
        }
    }


    private static void reportByComputerAndDate(ComputerUsage computerUsage, Scanner scanner) {
        System.out.print("Введите номер компьютера: ");
        int computerNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        if (!computerUsage.getRecords().containsKey(computerNumber)) {
            System.out.println("Записей для компьютера с номером " + computerNumber + " не найдено.");
            return;
        }
    
        System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
        String targetDate = scanner.nextLine();
    
        System.out.println("Информация об использовании компьютера №" + computerNumber + " на дату " + targetDate + ":");
        List<ComputerRecord> records = computerUsage.getRecords().get(computerNumber);
        for (ComputerRecord record : records) {
            if (record.getDate().equals(targetDate)) {
                System.out.println(record.getClientName() + ", " +
                        record.getStartTime() + " - " +
                        record.getEndTime());
            }
        }
    }    
}
