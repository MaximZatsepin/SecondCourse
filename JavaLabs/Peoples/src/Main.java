import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        People person1 = new People("Иванов", "Иван", 5, 1980);
        People person2 = new People("Петров", "Петр", 8, 1975);
        People person3 = new People("Сидоров", "Алексей", 12, 1990);
        
        People[] people = {person1, person2, person3};
        

        // Определение самого старого человека
        People oldest = oldestPerson(people);
        System.out.println("Самый старый человек: " + oldest.getSurname() + " " + oldest.getName());
        System.out.println();

        // Определение среднего возраста
        int averageYear = averageYear(people);
        System.out.println("Средний год рождения: " + averageYear);
        System.out.println();
        
        // Люди старше среднего возраста
        System.out.println("Люди старше среднего возраста:");
        People[] olderThanAverage = olderThanAverage(people, averageYear);
        for (People person : olderThanAverage) {
            System.out.println(person.getSurname() + " " + person.getName());
        }
        System.out.println();

        // Упорядочить массив по фамилиям в порядке, обратном алфавитному
        reverseAlphabeticalSort(people);
        System.out.println("Упорядоченный список:");
        for (People person : people) {
            System.out.println(person.getSurname() + " " + person.getName());
        }
        System.out.println();

        // Поиск по фамилии, исправление одного из полей и вывод полной информации о человеке после редактирования
        String searchSurname = "Иванов";
        String newName = "Александр";
        editAndPrint(people, searchSurname, newName);
        System.out.println();
    }

    // Определение самого старого человека
    public static People oldestPerson(People[] people) {
        People oldest = people[0];
        for (int i = 1; i < people.length; i++) {
            if (people[i].getYear() < oldest.getYear()) {
                oldest = people[i];
            } else if (people[i].getYear() == oldest.getYear() && people[i].getMonth() < oldest.getMonth()) {
                oldest = people[i];
            }
        }
        return oldest;
    }

    // Определение среднего года рождения
    public static int averageYear(People[] people) {
        int totalYear = 0;
        for (People person : people) {
            totalYear += person.getYear();
        }
        return totalYear / people.length;
    }

    // Люди старше среднего возраста
    public static People[] olderThanAverage(People[] people, int averageYear) {
        int count = 0;
        for (People person : people) {
            if (person.getYear() > averageYear) {
                count++;
            }
        }
        People[] older = new People[count];
        int index = 0;
        for (People person : people) {
            if (person.getYear() > averageYear) {
                older[index++] = person;
            }
        }
        return older;
    }

    // Упорядочить массив по фамилиям в порядке, обратном алфавитному (через Пузырек)
    public static void reverseAlphabeticalSort(People[] people) {
        int n = people.length; 
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (people[j].getSurname().compareTo(people[j + 1].getSurname()) < 0) {
                    People temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
    }
    

    // Поиск по фамилии, исправление одного из полей и вывод полной информации о человеке после редактирования
    public static void editAndPrint(People[] people, String searchSurname, String newName) {
        for (People person : people) {
            if (person.getSurname().equals(searchSurname)) {
                person.setName(newName);
                System.out.println("Информация о человеке после редактирования: " + person.getData());
                return;
            }
        }
        System.out.println("Человек с фамилией " + searchSurname + " не найден");
    }
}
