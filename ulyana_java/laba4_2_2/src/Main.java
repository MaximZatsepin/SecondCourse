import java.util.Scanner;

class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private int clothingSize;

    public Person(String lastName, String firstName, String middleName, int age, int clothingSize) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.clothingSize = clothingSize;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(int clothingSize) {
        this.clothingSize = clothingSize;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод информации о людях
        System.out.print("Введите количество людей: ");
        int n = scanner.nextInt();
        Person[] people = setPeopleArray(n, scanner);

        // Определение человека с самым большим размером одежды
        Person personWithMaxClothingSize = findPersonWithMaxClothingSize(people);
        System.out.println("Человек с самым большим размером одежды:");
        showPerson(personWithMaxClothingSize);

        // Определение среднего размера одежды для людей старше 40 лет
        double averageClothingSizeOver40 = calculateAverageClothingSizeOver40(people);
        System.out.println("Средний размер одежды для людей старше 40 лет: " + averageClothingSizeOver40);

        // Сортировка массива по возрастанию размера одежды
        sortPeopleByClothingSize(people);
        System.out.println("Отсортированный массив по возрастанию размера одежды:");
        showPeopleArray(people);

        // Поиск по фамилии, исправление одного из полей и вывод полной информации
        System.out.print("Введите фамилию для поиска: ");
        String searchLastName = scanner.next();
        Person foundPerson = findPersonByLastName(people, searchLastName);
        if (foundPerson != null) {
            System.out.println("Найден человек:");
            showPerson(foundPerson);
            System.out.print("Введите новый размер одежды: ");
            int newClothingSize = scanner.nextInt();
            foundPerson.setClothingSize(newClothingSize);
            System.out.println("Информация о человеке после исправления:");
            showPerson(foundPerson);
        } else {
            System.out.println("Человек с фамилией " + searchLastName + " не найден.");
        }
    }

    // Методы для выполнения подзадач

    public static Person[] setPeopleArray(int n, Scanner scanner) {
        Person[] people = new Person[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Введите информацию о человеке " + (i + 1) + ":");
            System.out.print("Фамилия: ");
            String lastName = scanner.next();
            System.out.print("Имя: ");
            String firstName = scanner.next();
            System.out.print("Отчество: ");
            String middleName = scanner.next();
            System.out.print("Возраст: ");
            int age = scanner.nextInt();
            System.out.print("Размер одежды: ");
            int clothingSize = scanner.nextInt();

            people[i] = new Person(lastName, firstName, middleName, age, clothingSize);
        }
        return people;
    }

    public static void showPerson(Person person) {
        System.out.println(person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName()
                + ", возраст: " + person.getAge() + ", размер одежды: " + person.getClothingSize());
    }

    public static Person findPersonWithMaxClothingSize(Person[] people) {
        Person maxClothingSizePerson = people[0];
        for (int i = 1; i < people.length; i++) {
            if (people[i].getClothingSize() > maxClothingSizePerson.getClothingSize()) {
                maxClothingSizePerson = people[i];
            }
        }
        return maxClothingSizePerson;
    }

    public static double calculateAverageClothingSizeOver40(Person[] people) {
        int totalClothingSize = 0;
        int count = 0;
        for (Person person : people) {
            if (person.getAge() > 40) {
                totalClothingSize += person.getClothingSize();
                count++;
            }
        }
        return count > 0 ? (double) totalClothingSize / count : 0;
    }

    public static void sortPeopleByClothingSize(Person[] people) {
        for (int i = 0; i < people.length - 1; i++) {
            for (int j = 0; j < people.length - 1 - i; j++) {
                if (people[j].getClothingSize() > people[j + 1].getClothingSize()) {
                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
    }

    public static void showPeopleArray(Person[] people) {
        for (Person person : people) {
            showPerson(person);
        }
    }

    public static Person findPersonByLastName(Person[] people, String lastName) {
        for (Person person : people) {
            if (person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }
}
