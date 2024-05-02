import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Human {
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Создание списка объектов класса Human
        List<Human> humans = new ArrayList<>();
        humans.add(new Human(10, "John", "Doe", LocalDate.of(2014, 1, 20), 25));
        humans.add(new Human(22, "Alice", "Smith", LocalDate.of(2001, 6, 6), 50));
        humans.add(new Human(39, "Bob", "Johnson", LocalDate.of(1984, 9, 2), 60));
        humans.add(new Human(35, "Goy", "Link", LocalDate.of(1988, 4, 19), 65));
        humans.add(new Human(53, "Jim", "Sweet", LocalDate.of(1970, 9, 26), 60));

        // Вывод исходного списка
        System.out.println("Исходный список: ");
        humans.forEach(System.out::println);

        // Сортировка по имени
        List<Human> sortedByName = humans.stream()
                .sorted(Comparator.comparing(Human::getFirstName))
                .collect(Collectors.toList());
        System.out.println("\nСписок после сортировки по имени:");
        sortedByName.forEach(System.out::println);

        // Фильтрация по дате рождения большей, чем 24 июня 2000
        LocalDate thresholdDate = LocalDate.of(2000, 6, 24);
        List<Human> filteredByBirthDate = humans.stream()
                .filter(human -> human.getBirthDate().isAfter(thresholdDate))
                .collect(Collectors.toList());
        System.out.println("\nСписок после фильтрации по дате рождения большей, чем 24 июня 2000:");
        filteredByBirthDate.forEach(System.out::println);

        // Сортировка по фамилии
        List<Human> sortedByLastName = humans.stream()
                .sorted(Comparator.comparing(Human::getLastName))
                .collect(Collectors.toList());
        System.out.println("\nСписок после сортировки по фамилии:");
        sortedByLastName.forEach(System.out::println);

        // Нахождение суммы всех возрастов
        int totalAge = humans.stream()
                .mapToInt(Human::getAge)
                .sum();
        System.out.println("\nСумма всех возрастов: " + totalAge);
    }
}
