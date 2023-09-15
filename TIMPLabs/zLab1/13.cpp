#include <iostream>
#include <time.h>
// #include <cmath>
#include <stdlib.h>

using namespace std;

// Формула для генератора от m до n
// num = m + rand() % (n - m + 1);

void taskA();
void taskB();
void taskC();
void taskD();
void taskE();
void taskF();
void taskG();

int main(){
    time_t t;
    srand(time(&t));
    // srand(time(nullptr));

    cout << endl;
    taskA();
    taskB();
    taskC();
    taskD();
    taskE();
    taskF();
    taskG();

}

void taskA(){

    double arrOfVar1[25];
    for(int i = 0; i < 25; i++){
        arrOfVar1[i] = -50 + rand() % (50 - (-50) + 1);
    }

    cout << "[Task 3.a] ";
    for(int i = 0; i < 25; i++){
        cout << arrOfVar1[i] << " ";
    }
    cout << "\n\n";

}

void taskB(){

    int arrOfVar2[30];
    for(int i = 0; i < 30; i++){
        arrOfVar2[i] = -20 + rand() % (20 - (-20) + 1);
    }

    cout << "[Task 3.b] ";
    for(int i = 0; i < 30; i++){
        cout << arrOfVar2[i] << " ";
    }
    cout << "\n\n";

}

void taskC(){

    double arrOfVar3[20];
    for(int i = 0; i < 20; i++){
        arrOfVar3[i] = 0 + rand() % (40 - 0 + 1);
    }

    cout << "[Task 3.c] ";
    for(int i = 0; i < 20; i++){
        cout << arrOfVar3[i] << " ";
    }
    cout << "\n\n";

}

void taskD(){

    int arrOfVar4[35];
    for(int i = 0; i < 35; i++){
        arrOfVar4[i] = 0 + rand() % (1000 - 0 + 1);
    }

    cout << "[Task 3.d] ";
    for(int i = 0; i < 35; i++){
        cout << arrOfVar4[i] << " ";
    }
    cout << "\n\n";

}

void taskE(){

    int arrOfVar5[27];
    for(int i = 0; i < 27; i++){
        arrOfVar5[i] = 0 + rand() % (20 - 0 + 1);
    }

    cout << "[Task 3.e] ";
    for(int i = 0; i < 27; i++){
        cout << arrOfVar5[i] << " ";
    }
    cout << "\n\n";

}

void taskF(){

    int n = 0 + rand() % (30 - 0 + 1);
    double arrOfVar6[n];
    for(int i = 0; i < n; i++){
        arrOfVar6[i] = -50 + rand() % (50 - (-50) + 1);
    }

    cout << "[Task 3.f] ";
    for(int i = 0; i < n; i++){
        cout << arrOfVar6[i] << " ";
    }
    cout << "\n\n";

}

void taskG(){

    char arrOfVar7[5];
    for(int i = 0; i < 5; i++){
        int switchVar = rand()%2;
        switch (switchVar)
        {
        case 1:
            arrOfVar7[i] = 65 + rand() % (90 - 65 + 1);
            break;
        default:
        arrOfVar7[i] = 97 + rand() % (122 - 97 + 1);
            break;
        }
    }

    for(int i = 0; i < 5; i++){
        cout << arrOfVar7[i] << " ";
    }

    cout << "\n\n";

}