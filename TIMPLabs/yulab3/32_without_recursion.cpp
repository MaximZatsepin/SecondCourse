/*
Вариант 6
[Task 2 - quick sort wihout recursion]  

Отладить правильность работы сортировок на массивах с
количеством элементов N=50 сгенерированные датчиком случайных чисел в диапазоне [16060, 36706]
*/


#include <iostream>
#include <cstdlib>
#include <time.h>
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

    cout << "\n[Task 2 (quick sort without recursion)]" << endl;

    // Количество элементов в массиве
    int n = 50;
    
    // Массив для быстрой сортировки
    short int arr[n];

    // Создание рандомного массива
    create_rnd_arr(arr, min_ch, max_ch, n);
    cout << "\nArray for quick sort: \n";
    output_arr(arr,n); 

    quick_sort(arr, 0, n-1);
    
    cout << "\nSorted array by quick sort:\n";
    output_arr(arr, n);
   
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