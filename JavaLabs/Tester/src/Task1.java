public class Task1 {
    public static void task1(){
        // Создать массив случайных чисел, вывести максимальное, минимальное, среднее
        int n = 15;
        double[] array = new double[n];

        // Заполнение
        for(int i = 0; i < n; i++){
           array[i] = Math.round(Math.random() * 100);
        }

        // Вывод
        System.out.print("\nЗаполненный массив: ");
        for(int i = 0; i < n; i++){
           System.out.print(array[i] + " ");
        }

        // Нахождение максимального, минимального, среднего
        double max = array[0];
        double min = array[0];
        double avg = 0;
        for(int i = 0; i < n; i++){
           if(array[i] < min) min = array[i];
           if(array[i] > max) max = array[i];
           avg += array[i] / n;
        }
        System.out.println("\nМаксимальное: " + max);
        System.out.println("Минимальное: " + min);
        System.out.println("Среднее: " + avg);
        
        // Пузырьковая сортировка:
        double temp;
        for(int i = 0; i < n-1; i++){
           for(int j = i; j < n; j++){
               if(array[i] > array[j]){
                   temp = array[i];
                   array[i] = array[j];
                   array[j] = temp;
               }
           }
        }
        // Вывод
        System.out.print("Отсортированный массив: ");
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
   }
}
