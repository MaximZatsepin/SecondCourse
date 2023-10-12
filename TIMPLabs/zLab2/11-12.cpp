#include <iostream>
#include <time.h>
#include <chrono>

using namespace std;

// Массив из 50ти чисел [17070,37707]
// num = m + rand() % (n - m + 1);
void createArray(int n, unsigned int arr[],unsigned int arr2[]);
void sortByBrush(int n, unsigned int arr[]);
int getNextRatio(int ratio);
void sortByChoise(int n, unsigned int arr[]);

int main(){
    setlocale(LC_ALL,"rus");
    srand(time(nullptr));
    int n;
    cout << "Введите число n: "; cin >> n;

    auto start = chrono::steady_clock::now(); // Запоминаем время начала работы программы
    unsigned int arr[500];
    unsigned int arr2[500];

    createArray(n,arr,arr2);
    cout << "\n\nВывод неотсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }

    sortByBrush(n,arr);

    cout << "\n\nВывод отсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

    auto dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
    cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " миллисекунд\n\n";
    start = chrono::steady_clock::now(); // Запоминаем время начала работы программы

    sortByChoise(n,arr2);
    cout << "\n\nВывод отсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr2[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

    dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
    cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " миллисекунд\n\n";
}

// Создание массива:
void createArray(int n, unsigned int arr[],unsigned int arr2[]){
    for(int i = 0; i < n; i++){
        arr[i] = 17070 + rand() % (37707 - 17070 + 1);
        arr2[i] = arr[i];
    }
}

// Получить следующее расстояние между элементами (Для расчёски)
int getNextRatio(int ratio){
    ratio = (ratio * 10) / 13;
    if(ratio < 1){ return 1; }
    return ratio;
}

// Сортировка расчёской
void sortByBrush(int n, unsigned int arr[]){
    int sortRatio = n;
    bool isSwapped = true;

    while(sortRatio != 1 || isSwapped){
        sortRatio = getNextRatio(sortRatio);
        isSwapped = false;

        for(int i = 0; i < n - sortRatio; i++){
            if(arr[i] > arr[i + sortRatio]){
                swap(arr[i],arr[i + sortRatio]);
                isSwapped = true;
            }
        }
    }

}

// Сортировка выбором
void sortByChoise(int n, unsigned int arr[]){

    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        
        swap(arr[i], arr[minIndex]);
    }
}


