/*
2.	Получить с помощью генератора случайных чисел 200 чисел в интервале от 0 до 1. 
Оценить по ним равномерность распределения. Разбить интервал (0,1) на интервалов 
равной длины [0..0,1), [0,1..0,2), …,[0,9..1] и построить горизонтальную гистограмму 
(столбчатую диаграмму), показывающую, сколько чисел попало в каждый интервал. 

Пример гистограммы: 
0.0..0.1 *******
0.1..0.2 *********
…
0.9..1.0 *******
*/


#include <iostream>
#include <time.h>
#include <stdlib.h>

using namespace std;

void output_stars(int var);

int main()
{
    time_t t;
    srand(time(&t));

    // Создаю массив
    double arr_of_rnd[200];
    for(int i = 0; i < 200; i++){
        // 1234567%1000 = 567 / 1000.0 = 0.567
        arr_of_rnd[i] = rand()%1000/1000.0;
    }

    // Выводим массив
    cout << "\n[Task2]\n" << endl;
    cout << "Output: \n" << endl;
    for(int i = 0; i < 200; i++){
        cout << arr_of_rnd[i] << " ";
        if (i % 10 == 0) {cout << endl;}
    } cout << endl;

    int statistic[10] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    for (int i = 0; i < 200; i++)
    {
        // 0.567 * 10 = 5
        statistic[int(arr_of_rnd[i]*10)] += 1;
    }

    // Вывод гистограммы

    cout << "\n";
    for (int i = 0; i < 10; i++)
    {
        if (i == 0) {cout << "0.0" << " - "<< i/10.0 + 0.1 << ":"; output_stars(statistic[i]);}
        else if (i == 9) {cout << i/10.0 << " - "<< "1.0" << ":"; output_stars(statistic[i]);}
        else {cout << i/10.0 << " - "<< i/10.0 + 0.1 << ":"; output_stars(statistic[i]);}
    }
}


void output_stars(int var)
{
    for (int i = 0; i < var; i++)
    {
        cout << "*";
    }
    cout << endl;
}

