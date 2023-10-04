/*
Вариант 6

[Task 3] Для сравнения алгоритмов сортировки составить таблицу (см. табл.3) следующего вида (данные получить экспериментально) для N=100, 200, 300, 400, 500. 
(N - количество элементов в массиве). Для этого в программе обязательно вставляется точки для замера времени для выдачи времени выполнения алгоритма.  
*/


#include <iostream>
#include <cstdlib>
#include <ctime>

#include <chrono>   // Для измерения времени выполнения
using namespace std;
 


void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n);
void output_arr(short int arr[], int n);

void basket_sort(short int arr[], int n);
void sorting_one_basket( short int basket[], int n);

int main()
{
    time_t t;
    srand(time(&t));

    // Диапозон значений массива
    int min_ch = 16060;
    int max_ch = 36706;

    cout << "\n[Task 3 (basket sort)]" << endl;
    // Количество элементов в массиве
    int n;    
    cout << "\nEnter number of series in the array: "; cin >> n;

    auto start = chrono::steady_clock::now();
    
    // Массив для быстрой сортировки
    short int arr[n];

    // Создание рандомного массива
    create_rnd_arr(arr, min_ch, max_ch, n);
    cout << "\nArray for block sort: \n";
    output_arr(arr, n); 

    basket_sort(arr, n);

    cout << "\nSorted array by block sort:\n";
    output_arr(arr, n);

    auto dur = chrono::steady_clock::now() - start;
    cout << "N=" << n <<", time: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " mcs" << endl;
    
}


// Создание массива с рандомными элементами
void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n)
{    
    for (int i = 0; i <= n; i++)    
    {
        // num = m + rand() % (n - m + 1);
        arr[i] = rand() % (max_ch - min_ch + 1) + min_ch;
        if(arr[i] < 0) {arr[i] *= -1;} 
    }
}
 
 // Функция вывода массива
void output_arr(short int arr[], int n)
{
    for (int i = 0; i < n; i++)             
        cout << arr[i] << "  ";        
    cout << "\n";    
} 


// Функция для корзинной сортировки
void basket_sort(short int arr[], int n) {
    // Находим минимальное и максимальное значения в массиве
    short int min_val = arr[0];
    short int max_val = arr[0];
    for (int i = 1; i < n; i++) 
    {
        if (arr[i] < min_val) {
            min_val = arr[i];
        }
        if (arr[i] > max_val) {
            max_val = arr[i];
        }
    }

    // Кол-во корзин = кол-во элементов исходного
    int num_baskets = n;
    // Запоминем кол-во элементов в каждой корзине
    int basket_sizes[num_baskets] = {0};
    // Сами корзины
    short int* baskets[num_baskets];

    for (int i = 0; i < num_baskets; i++) 
    {
        baskets[i] = new short int[n];
    }

    // Распределение элементов по корзинам
    for (int i = 0; i < n; i++) 
    {
        // Определяем индекс : определяем насколько число больше минимального, определяем какой
        // диапозон эл-тов, умножем на максимальный индекс -> используем int, получаем индекс корзины
        int basket_index = int((arr[i] - min_val) / (max_val - min_val) * (num_baskets - 1));

        // Заносим число в корзину под нужным индексом на последнее место
        baskets[basket_index][basket_sizes[basket_index]] = arr[i];

        // Указываем что в этой корзине теперь на 1 эл=т больше
        basket_sizes[basket_index]++;
    }

    // Сортировка элементов внутри каждой корзины
    for (int i = 0; i < num_baskets; i++) 
    {
        sorting_one_basket(baskets[i], basket_sizes[i]);
    }

    // Объединение корзин в отсортированный массив
    int index = 0;
    for (int i = 0; i < num_baskets; i++) {
        for (int j = 0; j < basket_sizes[i]; j++) {
            arr[index++] = baskets[i][j];
        }
        delete[] baskets[i];
    }
}



// Функция для сортировки вставками
void sorting_one_basket( short int basket[], int n) {
    // Начинаем с 1, нас не интересуют пустые или с одним эл-том корзины
    for (int i = 1; i < n; i++) 
    {
        // Запоминаем элемент
        short int key = basket[i];
        // Берем индекс эл-та слева
        int j = i - 1;
        // Пока слева есть эл-т и он больше, чем key
        while (j >= 0 && basket[j] > key) 
        {
            // Двигаем key вправо
            basket[j + 1] = basket[j];
            // Берем следующий левый эл-т
            j--;
        }
        // Ставим key на правильное место
        basket[j + 1] = key;
    }
}
