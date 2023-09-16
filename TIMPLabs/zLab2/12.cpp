#include <iostream>
#include <time.h>

using namespace std;

// Массив из 50ти чисел [17070,37707]
// num = m + rand() % (n - m + 1);
void createArray(int n, unsigned int arr[]);
void sortByChoise(int n, unsigned int arr[]);
int getNextRatio(int ratio);

int main(){
    clock_t start = clock();

    srand(time(nullptr));
    int n = 500;

    unsigned int arr[n];
    createArray(n,arr);

    cout << "\n\nВывод неотсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

    sortByChoise(n,arr);

    cout << "\n\nВывод отсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

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


