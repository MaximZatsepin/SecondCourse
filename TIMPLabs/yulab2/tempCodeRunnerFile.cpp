#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;
 

void create_rnd_arr(short int arr[], int min_ch, int max_ch, int n);
void output_arr(short int arr[], int n);
void sort(short int arr[], int start, int n);

int dop_sort(short int arr[], int start, int end);

int main()
{
    time_t t;
    srand(time(&t));

    // Short in range(-32 768, 32 767)
    int min_ch = -1000;
    int max_ch = 1000;

    // Number of series in the array
    int n;    
    cout << "Enter number of series in the array: "; cin >> n;
    
    short int arr[n];
    create_rnd_arr(arr, min_ch, max_ch, n);

    cout << "Array: \n";
    output_arr(arr,n); 
    sort(arr, 0, n-1);
    cout << "4545454" << endl;
    cout << "Sorted array:\n";
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

void sort(short int arr[], int start, int end)
{ 
    // Начало не должно быть больше окончания
    if (start >= end) {
        return;
    }

    // Вызываем функцию, которая отсортирует массив: *меньше* *опорное число* *больше* и вернет индекс, 
    // который разделит последовательность для дальнейшей сортировки
    int index_opora = dop_sort(arr, start, end);

    sort(arr, start, index_opora-1);
    sort(arr, index_opora+1, end);
}

 
int dop_sort(short int arr[], int start, int end)
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