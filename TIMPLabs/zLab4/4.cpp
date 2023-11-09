/*
Составьте алгоритм и напишите программу, находящую строку длиной
100 символов, состоящую только из букв "A", "B", "C", такую, что в ней
никакие две соседние подстроки не равны друг другу. Воспользуйтесь
перебором с возвратом.
*/
#include <iostream>
#include <time.h>
#include <string>

using namespace std;


#if 0
const char Alphabet[3] = {'A','B','C'};

int main(){
    srand(time(nullptr));
    int n; cout << "n is: (n > 100)"; cin >> n;
    string rndstr;
    for(int i = 0; i < n; i++){
        rndstr += Alphabet[rand() % 3];
    }
    cout << "\n---------------------\n";
    cout << "Current string is: " << rndstr;

    return 0;
}
#endif

// Задачу невозможно решить, поскльку элемента всего 3, а строку нужно найти длиной 100
// Подстрока - один и более непустых символов, взятых из строки
/* ------------------
    ABCA ACBA BACA BCAA CABA CBAA
    ABCB ACBB BACB BCAB CABB CBAB
    ABCC ACBC BACC BCAC CABC CBAC
    
    Ни одна строка уже не подходит под условие, т.к даже 1 символ - подстрока

    Пример: str = 'ABCA', Возьмём подстроку str[0] и str[-1]. Они уже равны, как
    и все другие => любая строка больше 3х символов не подходит под условие.
*/ 































// http://kuimova.ucoz.ru/modul_7-perebor_s_vozvratom.pdf
