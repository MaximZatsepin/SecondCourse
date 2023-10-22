#include <iostream>
#include <time.h>
#include <chrono>

using namespace std;

// Массив из 50ти чисел [17070,37707]
// num = m + rand() % (n - m + 1);
void createArray(int n, unsigned int arr[]);
void sortByBrush(int n, unsigned int arr[]);
int getNextRatio(int ratio);

int main(){
    auto start = chrono::steady_clock::now(); // Запоминаем время начала работы программы

    srand(time(nullptr));
    int n = 10;

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

    auto dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
    cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " миллисекунд\n\n";

}

// Создание массива:
void createArray(int n, unsigned int arr[]){
    for(int i = 0; i < n; i++){
        arr[i] = 17070 + rand() % (37707 - 17070 + 1);
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

         // Output
        for(int i = 0, count = 1; i < n; i++, count++){
            cout << arr[i] << " ";
            // if(count == 10){ cout << endl; count = 0; 
            
        }
        cout << endl;
	}
}



