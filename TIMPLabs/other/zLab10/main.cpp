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
    int a[10000]; // исходные данные
    int N;
    int minb; // искомое минимальное произведение пары чисел
    cin >> N;
    auto start = chrono::steady_clock::now();
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        // a[i] = rand() % 1000;
    }
    minb = 1001;
    for (int i = 0; i < N - 6; i++) {
        for (int j = i + 6; j < N; j++) {
            if (a[i] * a[j] < minb) {
                minb = a[i] * a[j];
            }
        }
    }
    cout << minb << endl;
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
    int h[6];
    int min, n, a;
    long long pro;
    cin >> n;
    auto start = chrono::steady_clock::now();
    min = 10001;
    pro = 10000001;
    for (int i = 0; i < 6; ++i)
        cin >> h[i];
        // h[i] = rand() % 1000;
    for (int i = 6; i < n; ++i) {
        cin >> a;
        // a = rand() % 1000;
        if (h[i % 6] < min)
            min = h[i % 6];
        if (min * a < pro)
            pro = min * a;
        h[i % 6] = a;
    }
    cout << pro << endl;
    auto dur = chrono::steady_clock::now() - start;
    cout << "\ntime: " << chrono::duration_cast<chrono::milliseconds>(dur).count() << " msec";
    //}
    return 0;
}
// #endif