/*
Вариант 6
[Task 3 - quick sort wih recursion] 

Для сравнения двух методов сортировки составить таблицу (см.
табл.3) следующего вида (данные получить экспериментально) для N=1000,
10000, 100000, 500000, 1000000 (N – количество элементов в массиве). Для
этого в программе обязательно вставляется точки для замера времени для
выдачи времени выполнения алгоритма.
*/


#include <iostream>
#include <cstdlib>
#include <ctime>
#include <chrono>   // Для измерения времени выполнения

using namespace std;
 


void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n);
void output_arr(short int arr[], int n);
void swap(int& a, int& b);

void quick_sort(short int arr[], int start, int n);
int dop_quick_sort(short int arr[], int start, int end);


int main()
{
    
    time_t t;
    srand(time(&t));

    // Диапозон значений массива
    int min_ch = 16060;
    int max_ch = 36706;

    cout << "\n[Task 3 (quick sort with recursion)]" << endl;

    // Количество элементов в массиве
    int n;    
    cout << "\nEnter number of series in the array: "; cin >> n;

    
    
    // Массив для быстрой сортировки
    short int arr[n];

    // Создание рандомного массива
    create_rnd_arr(arr, min_ch, max_ch, n);

    auto start = chrono::steady_clock::now();

    quick_sort(arr, 0, n-1);

    auto dur = chrono::steady_clock::now() - start;
    cout << "\nN=" << n <<", time: " << chrono::duration_cast<chrono::milliseconds>(dur).count() << " mcs" << endl;

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

// Функция, организующая быструю сортировк
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
            swap(arr[i], arr[index_sort]);
            // Двигаю index_sort 
            index_sort ++;
        }
    } 
    // После всех найденных чисел меньше опоры, ставлюю опору
    swap(arr[end], arr[index_sort]);

    // Возвращаю индекс опоры
    return index_sort;
}

// Функция для обмена элементов массива
void swap(int& a, int& b) 
{
    int temp = a;
    a = b;
    b = temp;
}