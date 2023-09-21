/*
Вариант 6

[Task 1] Разработать подпрограммы сортировки массива определенного типа данных (см. табл. 1) c помощью алгоритмов сортировки (см. табл. 2). 
Таблица 1: Одномерный массив беззнаковых коротких целых чисел типа short int

Таблица 2:
1. Быстрая сортировка 
2. Блочная сортировка

[Task 2] Отладить правильность работы сортировок на массивах c количеством элементов N=50 сгенерированные датчиком случайных чисел в диапазоне  
[1x0x0, 3x70x], где xx - это цифры номера варианта. Кроме того, контролировать правильность сортировки путем подсчета контрольной суммы и числа 
серий в массиве (оформить в виде подпрограммы).

[Task 3] Для сравнения алгоритмов сортировки составить таблицу (см. табл.3) следующего вида (данные получить экспериментально) для N=100, 200, 300, 400, 500. 
(N - количество элементов в массиве). Для этого в программе обязательно вставляется точки для замера времени для выдачи времени выполнения алгоритма.  
*/







#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;
 

void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n);
void output_arr(short int arr[], int n);

void quick_sort(short int arr[], int start, int n);
int dop_quick_sort(short int arr[], int start, int end);

int main()
{
    time_t t;
    srand(time(&t));

    int min_ch = -1000;
    int max_ch = 1000;

    // Количество элементов в массиве
    int n;    
    cout << "\nEnter number of series in the array: "; cin >> n;
    
    short int arr[n];

    // Быстрая сортировка
    create_rnd_arr(arr, min_ch, max_ch, n);
    cout << "\nFirst array: \n";
    output_arr(arr,n); 
    quick_sort(arr, 0, n-1);
    cout << "\nSorted array by quick sort:\n";
    output_arr(arr, n);

    // Блочная сортировка
    create_rnd_arr(arr, min_ch, max_ch, n);
    cout << "\nSecond array: \n";
    output_arr(arr,n); 

    cout << "\nSorted array by block sort:\n";
    output_arr(arr, n);
    
}



void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n)
{    
    for (int i = 0; i <= n; i++)    
    // num = m + rand() % (n - m + 1);
        arr[i] = min_ch + rand() % (max_ch - min_ch + 1);
}
 
 
void output_arr(short int arr[], int n)
{
    for (int i = 0; i < n; i++)             
        cout << arr[i] << "  ";        
    cout << "\n";    
} 

void quick_sort(short int arr[], int start, int end)
{ 
    // Начало не должно быть больше окончания
    if (start >= end) {
        return;
    }

    // Вызываем функцию, которая отсортирует массив: *меньше* *опорное число* *больше* и вернет индекс, 
    // который разделит последовательность для дальнейшей сортировки
    int index_opora = dop_quick_sort(arr, start, end);

    quick_sort(arr, start, index_opora-1);
    quick_sort(arr, index_opora+1, end);
}

 
int dop_quick_sort(short int arr[], int start, int end)
{
    // Выбираем опорным числом крайнее левое значение
    int opora = arr[end];
    // Вспомогательный индекс (возвращаемый индекс опоры)
    int index_sort = start;
    // Переменная для того,чтобы поменять элементы массива местами
    short int temp;

    // Цикл сортировки
    for (int i = start; i < end; i++)
    {
        // Если я нахожу число меньше опоры, то я его ставлю вперед (по индексу index_sort), а число которое 
        // было впереди, ставлю на место найденного меньшего числа (по идексу i)
        if (arr[i] <= opora)
        {
            // Меняю местами
            temp = arr[i];
            arr[i] = arr[index_sort];
            arr[index_sort] = temp;
            // Двигаю index_sort 
            index_sort ++;
        }
    } 
    // После всех найденных чисел меньше опоры, ставлюю опору
    temp = arr[end];
    arr[end] = arr[index_sort];
    arr[index_sort] = temp;

    // Возвращаю индекс опоры
    return index_sort;
}


