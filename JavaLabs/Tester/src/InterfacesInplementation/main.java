package InterfacesInplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        List<Human> humans = new ArrayList<>();
        humans.add(new Human(25, "Петр", "Иванов", LocalDate.of(1999, 5, 15), 70));
        humans.add(new Human(30, "Иван", "Петров", LocalDate.of(1994, 8, 21), 65));
        humans.add(new Human(20, "Дмитрий", "Кузнецов", LocalDate.of(2004, 3, 8), 80));
        humans.add(new Human(35, "Анастасия", "Булкина", LocalDate.of(1989, 12, 3), 60));

        // Выводим список людей
        System.out.println("Список людей:");
        humans.forEach(System.out::println);

        // Фильтрация по возрасту больше чем 20, сортировка по последней букве имени
        System.out.println("\nФильтрация по возрасту > 20 и сортировка по последней букве имени:");
        List<Human> filteredAndSorted = humans.stream()
                .filter(h -> h.getAge() > 20)
                .sorted((h1, h2) -> Character.compare(h1.getLastName().charAt(h1.getLastName().length() - 1),
                        h2.getLastName().charAt(h2.getLastName().length() - 1)))
                .collect(Collectors.toList());
        filteredAndSorted.forEach(System.out::println);

        // Увеличение возраста каждого на 3
        System.out.println("\nУвеличение возраста каждого на 3:");
        humans.stream()
                .peek(h -> h.setAge(h.getAge() + 3))
                .forEach(System.out::println);

        // Вычисление среднего возраста всех элементов
        double averageAge = humans.stream()
                .mapToInt(Human::getAge)
                .average()
                .orElse(0);
        System.out.println("\nСредний возраст: " + averageAge);
    }
}

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

    public void setAge(int age) {
        this.age = age;
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

