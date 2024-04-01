import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
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

        // Вывод исходного
        System.out.println("Исходный список: ");
        humans.forEach(System.out::println);

        // Увеличение веса каждого объекта на 3
        humans.forEach(human -> human.setWeight(human.getWeight() + 3));
        System.out.println("\nСписок после увеличения веса на 3:");
        humans.forEach(System.out::println);

        // Сортировка по весу в обратном порядке
        humans.sort(Comparator.comparingInt(Human::getWeight).reversed());
        System.out.println("\nСписок после сортировки по весу в обратном порядке:");
        humans.forEach(System.out::println);

        // Фильтрация по дате рождения меньшей, чем 01.01.2000
        LocalDate thresholdDate = LocalDate.of(2000, 1, 1);
        List<Human> filteredList = humans.stream()
                .filter(human -> human.getBirthDate().isBefore(thresholdDate))
                .toList();
        System.out.println("\nСписок после фильтрации по дате рождения меньшей, чем 01.01.2000:");
        filteredList.forEach(System.out::println);

        // Сумма всех весов
        int totalWeight = humans.stream().mapToInt(Human::getWeight).sum();
        System.out.println("\nСумма всех весов: " + totalWeight);
    }
}
