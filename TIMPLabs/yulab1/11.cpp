/*
1.	Случайное выбранное число сохраняется в переменную. Тип переменной выбирается из таблицы типов переменных (float)
*/

#include <iostream>
#include <time.h>

using namespace std;

int main(){
    // Штука для серии
    time_t t;
    srand(time(&t));

    // Сохранениеy в переменную
    float rnd = rand();
    cout << "\n[Task1] Random variable: " << rnd << "\n\n";
}