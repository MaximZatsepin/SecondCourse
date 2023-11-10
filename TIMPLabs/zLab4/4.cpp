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

void backtrace(string str, int n);

const char Alphabet[3] = {'A','B','C'};

int main(){
    srand(time(nullptr));
    int n; cout << "n is: (n > 100) "; cin >> n;
    string str;
    for(int i = 0; i < n; i++){
        str += Alphabet[rand() % 3];
    }
    cout << "\n---------------------\n";
    cout << "Current string is: " << str;

    return 0;
}

void backtrace(string str, int n){
    return;
}































// http://kuimova.ucoz.ru/modul_7-perebor_s_vozvratom.pdf
