package InterfacesInplementation;

public class ArraysComparator {
    public static void main(String[] args) {
        ArrayComparator<Integer> comparator = (arrA, arrB) -> Integer.compare(arrA.length, arrB.length);

        Integer[] arr1 = {1, 2, 3, 4};
        Integer[] arr2 = {4, 5};
        Integer[] arr3 = {6, 7, 8, 9};

        System.out.println(comparator.compare(arr1, arr2)); // Вывод: 1 (1 > 2)
        System.out.println(comparator.compare(arr2, arr3)); // Вывод: -1 (2 < 3)
        System.out.println(comparator.compare(arr1, arr3)); // Вывод: 0 (1 = 3)
    }
}

@FunctionalInterface
interface ArrayComparator<T> {
    int compare(T[] arr1, T[] arr2);
}

