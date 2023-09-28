#include <iostream>

using namespace std;

// Функция для сортировки вставками
void insertionSort(float arr[], int n) {
    for (int i = 1; i < n; i++) {
        float key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }

}

// Функция для корзинной сортировки
void bucketSort(float arr[], int n) {
    // Находим минимальное и максимальное значения в массиве
    float min_val = arr[0];
    float max_val = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] < min_val) {
            min_val = arr[i];
        }
        if (arr[i] > max_val) {
            max_val = arr[i];
        }
    }

    // Создаем пустые корзины
    int num_buckets = n;
    int bucket_sizes[num_buckets] = {0};
    float* buckets[num_buckets];

    for (int i = 0; i < num_buckets; i++) {
        buckets[i] = new float[n];
    }

    // Распределение элементов по корзинам
    for (int i = 0; i < n; i++) {
        int bucket_index = int((arr[i] - min_val) / (max_val - min_val) * (num_buckets - 1));
        buckets[bucket_index][bucket_sizes[bucket_index]] = arr[i];
        bucket_sizes[bucket_index]++;
    }

    // Сортировка элементов внутри каждой корзины
    for (int i = 0; i < num_buckets; i++) {
        insertionSort(buckets[i], bucket_sizes[i]);
    }

    // Объединение корзин в отсортированный массив
    int index = 0;
    for (int i = 0; i < num_buckets; i++) {
        for (int j = 0; j < bucket_sizes[i]; j++) {
            arr[index++] = buckets[i][j];
        }
        delete[] buckets[i];
    }
}

int main() {
    float arr[] = {-1000, -20, -500, 500, 10, 200};
    int n = sizeof(arr) / sizeof(arr[0]);

    cout << "Исходный массив: ";
    for (int i = 0; i < n; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;

    insertionSort(arr, n);

    
    bucketSort(arr, n);

    cout << "Отсортированный массив: ";
    for (int i = 0; i < n; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;

    return 0;
    
}
