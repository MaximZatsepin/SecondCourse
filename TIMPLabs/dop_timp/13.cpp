/*
1.	Случайное выбранное число сохраняется в переменную. Тип переменной выбирается из таблицы типов переменных 
*/

#include <iostream>
#include <time.h>
#include <windows.h>

using namespace std;

//char
int main()
{
    // setlocale(LC_ALL, "rus");
    // SetConsoleCP(1251);
    // Штука для серии
    time_t t;
    srand(time(&t));

    char randomChar = rand();

    // static_cast<int> - используется для явного преобразования значения переменной, из одиного типа данных в другой 
    cout << "Random namber : " << static_cast<int>(randomChar) << endl;
}





