#include <iostream>
#include <time.h>

using namespace std;

// Массив из 50ти чисел [17070,37707]
// num = m + rand() % (n - m + 1);
void createArray(int n, unsigned int arr[]);
void sortByBrush(int n, unsigned int arr[]);
int getNextRatio(int ratio);

int main(){
    // Запоминаем время начала работы программы
    clock_t start = clock();

    srand(time(nullptr));
    int n = 50;

    unsigned int arr[500];
    createArray(n,arr);

    cout << "\n\nВывод неотсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

    sortByBrush(n,arr);

    cout << "\n\nВывод отсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

    // Вычисляем
    double duration = (clock() - start) / (double) CLOCKS_PER_SEC;
    cout << "Время выполнения программы: " << duration << " миллисекунд\n\n";

}

// Создание массива:
void createArray(int n, unsigned int arr[]){
    for(int i = 0; i < n; i++){
        arr[i] = 17070 + rand() % (37707 - 17070 + 1);
    }
}

// Получить следующее расстояние между элементами
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


