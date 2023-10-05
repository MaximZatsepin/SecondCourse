/*
Вариант 6
[Task 1 - quick sort wihout recursion]  

Разработать программу быстрой сортировки массива определенного
типа данных short int без рекурсии.
*/


#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;
 


void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n);
void output_arr(short int arr[], int n);

void swap(int& a, int& b);
int rarefaction(short int arr[], int low, int hight);
void quick_sort(short int arr[], int low, int hight);


int main()
{
    
    time_t t;
    srand(time(&t));

    // Диапозон значений массива
    int min_ch = 0;
    int max_ch = 1000;

    cout << "\n[Task 1 (quick sort without recursion)]" << endl;

    // Количество элементов в массиве
    int n;    
    cout << "\nEnter number of series in the array: "; cin >> n;
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
int rarefaction(short int arr[], int low, int hight) 
{
    int opora = arr[hight]; // Опорный элемент (самый правый)
    int i = (low - 1); // Индекс для меньших элементов

    for (int j = low; j <= hight - 1; j++) {
        if (arr[j] < opora) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[hight]);
    return (i + 1);
}


// Нерекурсивная реализация быстрой сортировки
void quick_sort(short int arr[], int low, int hight) 
{
    int stack[hight - low + 1]; // Массив для хранения интервалов
    int top = -1; // Индекс, определяющий элементы массива, которые будут выбраны как границы интервала сортировки

    // Заносим границы сортировки в массив
    stack[++top] = low;
    stack[++top] = hight;

    while (top >= 0) 
    {
        // Определяем индексы-границы сортировки
        hight = stack[top--];
        low = stack[top--];

        // Сортируем определенный участок, возвращаем индекс опоры
        int opora_index = rarefaction(arr, low, hight);

        if (opora_index - 1 > low) 
        {
            stack[++top] = low;
            stack[++top] = opora_index - 1;
        }

        if (opora_index + 1 < hight) 
        {
            stack[++top] = opora_index + 1;
            stack[++top] = hight;
        }
    }
}