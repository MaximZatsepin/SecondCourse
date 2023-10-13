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
void sumOfSeries(unsigned int arr1[],unsigned int arr2[], int n);
void res_summa_and_series(unsigned int arr1[], unsigned int arr2[], int n);

int main(){
    // setlocale(LC_ALL,"rus");
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
    cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " микросекунд\n\n";
    start = chrono::steady_clock::now(); // Запоминаем время начала работы программы

    sortByChoise(n,arr2);
    cout << "\n\nВывод отсортированного массива\n";
    for(int i = 0, count = 1; i < n; i++, count++){
        cout << arr2[i] << " ";
        if(count == 10){ cout << endl; count = 0; }
    }
    cout << "\n\n";

    dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
    cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " микросекунд\n\n";

    res_summa_and_series(arr,arr2,n);

}

// Создание массива:
void createArray(int n, unsigned int arr[],unsigned int arr2[]){
    for(int i = 0; i < n; i++){
        arr[i] = 17070 + rand() % (37707 - 17070 + 1);
        arr2[i] = arr[i];
    }
}

// Сортировка расчёской
void sortByBrush(int n, unsigned int arr[]){
    double factor = 1.2473309; // фактор уменьшения
	int step = n; // шаг сортировки
    
    //Последняя итерация цикла, когда step==1 эквивалентна одному проходу сортировки пузырьком
	while (step >= 1)
	{
        // cout << "Step - " << step << endl;
		for (int i = 0; i + step < n; i++)
		{
			if (arr[i] > arr[i + step])
			{
				swap(arr[i], arr[i + step]);
			}
		}
		step /= factor;

        // // Output
        // for(int i = 0, count = 1; i < n; i++, count++){
        //     cout << arr[i] << " ";
        //     // if(count == 10){ cout << endl; count = 0; 
            
        // }
        // cout << endl;
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

// Фцнкция для подсчета контрольных сумм и серий
void res_summa_and_series(unsigned int arr1[], unsigned int arr2[], int n){
    int summa_brush = 0;
    int count_series_brush = 1;

    int summa_choise = 0;
    int count_series_choise = 1;

    for (int i = 1; i < n; i++)
    {
        summa_choise += arr2[i]; summa_brush += arr1[i];
        if (arr1[i-1] != (arr1[i]-1)) { count_series_brush++;}
        if (arr2[i-1] != (arr2[i]-1)) { count_series_choise++;}
    }
    cout << "\nBrush sort: \nSumma: " << summa_brush << "\nSeries: " << count_series_brush << endl;
    cout << "\nChoise sort: \nSumma: " << summa_brush << "\nSeries: " << count_series_brush << endl;
}