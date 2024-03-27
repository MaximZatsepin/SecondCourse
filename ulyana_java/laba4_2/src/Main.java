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

        // Определение человека с самым большим размером одежды
        int maxClothingSizeIndex = 0;
        for (int i = 1; i < n; i++) {
            if (people[i].getClothingSize() > people[maxClothingSizeIndex].getClothingSize()) {
                maxClothingSizeIndex = i;
            }
        }
        System.out.println("Человек с самым большим размером одежды:");
        System.out.println(people[maxClothingSizeIndex].getLastName() + " " + people[maxClothingSizeIndex].getFirstName()
                + " " + people[maxClothingSizeIndex].getMiddleName() + ", возраст: " + people[maxClothingSizeIndex].getAge()
                + ", размер одежды: " + people[maxClothingSizeIndex].getClothingSize());

        // Определение среднего размера одежды для людей старше 40 лет
        int totalClothingSize = 0;
        int count = 0;
        for (Person person : people) {
            if (person.getAge() > 40) {
                totalClothingSize += person.getClothingSize();
                count++;
            }
        }
        double averageClothingSize = count > 0 ? (double) totalClothingSize / count : 0;
        System.out.println("Средний размер одежды для людей старше 40 лет: " + averageClothingSize);

        // Сортировка массива по возрастанию размера одежды
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (people[j].getClothingSize() > people[j + 1].getClothingSize()) {
                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }

        // Вывод отсортированного массива
        System.out.println("Отсортированный массив по возрастанию размера одежды:");
        for (Person person : people) {
            System.out.println(person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName()
                    + ", возраст: " + person.getAge() + ", размер одежды: " + person.getClothingSize());
        }

        // Поиск по фамилии, исправление одного из полей и вывод полной информации
        System.out.print("Введите фамилию для поиска: ");
        String searchLastName = scanner.next();
        boolean found = false;
        for (Person person : people) {
            if (person.getLastName().equals(searchLastName)) {
                found = true;
                System.out.println("Найден человек:");
                System.out.println(person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName()
                        + ", возраст: " + person.getAge() + ", размер одежды: " + person.getClothingSize());
                System.out.print("Введите новый размер одежды: ");
                int newClothingSize = scanner.nextInt();
                person.setClothingSize(newClothingSize);
                System.out.println("Информация о человеке после исправления:");
                System.out.println(person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName()
                        + ", возраст: " + person.getAge() + ", новый размер одежды: " + person.getClothingSize());
            }
        }
        if (!found) {
            System.out.println("Человек с фамилией " + searchLastName + " не найден.");
        }
    }
}
