#include <iostream>
#include <time.h>

using namespace std;

/*
Задание 1.	
Случайное выбранное число сохраняется в переменную. 
Тип переменной выбирается из таблицы типов переменных. 
*/

int main(){
    // time_t t;
    // srand(time(&t));
    srand(time(nullptr));

    unsigned long int rand_var = rand();
    cout << "\n[Task1] Random variable: " << rand_var << "\n\n";
}