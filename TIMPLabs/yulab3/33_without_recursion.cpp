/*
Вариант 6
[Task 3 - quick sort wihout recursion]  

Для сравнения двух методов сортировки составить таблицу (см.
табл.3) следующего вида (данные получить экспериментально) для N=1000,
10000, 100000, 500000, 1000000 (N – количество элементов в массиве). Для
этого в программе обязательно вставляется точки для замера времени для
выдачи времени выполнения алгоритма.

*/


#include <iostream>
#include <cstdlib>
#include <time.h>
#include <chrono>   // Для измерения времени выполнения


using namespace std;

void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n);
void output_arr(short int arr[], int n);

void swap(int& a, int& b);
int rarefaction(short int arr[], int low, int high);
void quick_sort(short int arr[], int low, int high);

 
int main()
{
    
    time_t t;
    srand(time(&t));

    // Диапозон значений массива
    int min_ch = 16060;
    int max_ch = 36706;

    cout << "\n[Task 3 (quick sort without recursion)]" << endl;

    // Количество элементов в массиве
    int n;    
    cout << "\nEnter number of series in the array: "; cin >> n;

    // Массив для быстрой сортировки
    short int arr[n];
   

    // Создание рандомного массива
    create_rnd_arr(arr, min_ch, max_ch, n);
    //output_arr(arr, n);

    auto start = chrono::steady_clock::now();
    cout << "START" << endl;
    quick_sort(arr, 0, n-1);

    auto dur = chrono::steady_clock::now() - start;
    cout << "\nN=" << n <<", time: " << chrono::duration_cast<chrono::milliseconds>(dur).count() << " mcs" << endl;
    cout << "END" << endl;
   
}

// Создание массива с рандомными элементами
void create_rnd_arr(short int arr[], int min_ch, int max_ch, int l)
{    
    cout << l << endl;
    for (int i = 0; i <= l; i++)    
    {

        // num = m + rand() % (n - m + 1);
        arr[i] = rand() % (max_ch - min_ch + 1) + min_ch;
        if (i% 1000 == 0) {cout << i << " "<<  arr[i] << endl;}
        
        if(arr[i] < 0) {arr[i] *= -1;} 
        //cout << arr[i] << endl;

    }
}
 
 // Функция вывода массива
void output_arr(short int arr[], int n)
{
    for (int i = 0; i < n; i++)             
        cout << arr[i] << "  ";        
    cout << "\n";    
} 


// Функция для обмена элементов массива
void swap(int& a, int& b) 
{
    int temp = a;
    a = b;
    b = temp;
}

// Функция для разделения массива на две части
int rarefaction(short int arr[], int low, int high) 
{
    int opora = arr[high]; // Опорный элемент (самый правый)
    int i = (low - 1); // Индекс для меньших элементов

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < opora) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return (i + 1);
}


// Нерекурсивная реализация быстрой сортировки
void quick_sort(short int arr[], int low, int high) 
{
    int stack[high - low + 1]; // Массив для хранения интервалов
    int top = -1; // Индекс, определяющий элементы массива, которые будут выбраны как границы интервала сортировки

    // Заносим границы сортировки в массив
    stack[++top] = low;
    stack[++top] = high;

    while (top >= 0) 
    {
        //cout << top << endl;

        // Определяем индексы-границы сортировки
        high = stack[top--];
        low = stack[top--];

        // Сортируем определенный участок, возвращаем индекс опоры
        int opora_index = rarefaction(arr, low, high);

        if (opora_index - 1 > low) 
        {
            stack[++top] = low;
            stack[++top] = opora_index - 1;
        }

        if (opora_index + 1 < high) 
        {
            stack[++top] = opora_index + 1;
            stack[++top] = high;
        }
    }
}