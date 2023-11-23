/*
Для заданной последовательности целых неотрицательных чисел необходимо найти минимальное произведение двух её элементов, различающихся порядковыми номерами не менее чем на 6. Значение каждого элемента последовательности не превышает 1000. Количество элементов последовательности не превышает 10000 и не менее 7.

Вам предлагается два задания, связанных с этой задачей: задание А и задание Б. Вы можете решать оба задания или одно из них по своему выбору. Итоговая оценка выставляется как максимальная из оценок за задания А и Б. Если решение одного из заданий не представлено, то считается, что оценка за это задание  — 0 баллов.

Задание Б является усложнённым вариантом задания А, оно содержит дополнительные требования к программе.
*/
#include <iostream>
#include <chrono>
#include <time.h>
// auto start = chrono::steady_clock::now(); // Запоминаем время начала работы программы
// auto dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
// cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " микросекунд";
// start = chrono::steady_clock::now(); // Снова запоминаем время начала работы программы
using namespace std;


// Неоптимизированная
// Count | Time (msec)
// 500   | ~500   
// 1000  | ~900
// 5000  | ~4300
// 10000 | ~9700
#if 0
int main () {
    cout << "Non-optimized\n";
    srand(time(nullptr));
    //while(true){
    int array[10000];
    int n; cin >> n;
    float minAvg = 5001.0;
    // int num1,num2;
    auto start = chrono::steady_clock::now();
    for (int i = 0; i < n; i++) {
        cin >> array[i];
        // a[i] = rand() % 1000;
    }
    int iterations = 0;
    for (int i = 0; i < n - 6; i++) {
        for (int j = i + 6; j < n; j++) {
            float temp = (array[i] + array[j]) * 0.5;
            if (temp < minAvg) {
                minAvg = temp;
                // num1 = array[i];
                // num2 = array[j];
            }
            iterations++;
        }
    }
    cout << minAvg << endl;// << iterations << endl; //<< num1 <<" "<< num2 << endl;
    auto dur = chrono::steady_clock::now() - start;
    cout << "\ntime: " << chrono::duration_cast<chrono::milliseconds>(dur).count() << " msec";
    //}
    return(0);
}
#endif

// Оптимизированная
// Count | Time (microseconds)
// 500   | ~230   
// 1000  | ~460
// 5000  | ~2280
// 10000 | ~4600
// #if 0
int main() {
    cout << "Optimized\n";
    srand(time(nullptr));
    //while(true){
    short *array = new short[6];
    short min = 1001, n, a;
    float avg = 5001.0;
    cin >> n;
    auto start = chrono::steady_clock::now();
    for (short i = 0; i < 6; ++i)
        cin >> array[i];
        // h[i] = rand() % 1000;
    for (short i = 6; i < n; ++i) {
        cin >> a;
        // a = rand() % 1000;
        if (array[i % 6] < min)
            min = array[i % 6];
        if ((min + a)/2.0 < avg)
            avg = (min + a)/2.0;
        array[i % 6] = a;
    }
    cout << avg << endl;
    auto dur = chrono::steady_clock::now() - start;
    cout << "\ntime: " << chrono::duration_cast<chrono::milliseconds>(dur).count() << " msec";
    //}
    return 0;
}
// #endif

// 347 267 749 479 185 335 477 223 981 394 230 328 662 927 23
// 347 267 749 479 185 335
// 477 - 6
// 347 < min
// min = 347
// min + 477 < avg
// avg = min + 477
// 223 - 7