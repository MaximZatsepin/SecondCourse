#include <iostream>
#include <time.h>
#include <stdlib.h>

using namespace std;

// (double)(rand())/RAND_MAX*(max - min) + min;
// RAND_MAX = 32767

void first_task();
void second_task();
void third_task();
void fourth_task();
void fifth_task();
void sixth_task();
void seventh_task();


int main(){
    time_t t;
    srand(time(&t));

    cout << endl;
    cout << "[Task 3.1] " << endl;
    first_task();

    cout << "[Task 3.2]" << endl;
    second_task();

    cout << "[Task 3.3]" << endl;
    third_task();

    cout << "[Task 3.4]" << endl;
    fourth_task();

    cout << "[Task 3.5]" << endl;
    fifth_task();

    cout << "[Task 3.6]" << endl;
    sixth_task();

    cout << "[Task 3.7]" << endl;
    seventh_task();

}

void first_task()
{
    double arr[25];
    for (int i =0; i < 25; i++)
    {
        arr[i] = (double)(rand())/RAND_MAX*(50 + 50) - 50;
    }

    for (int i = 0; i < 25; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";
}

void second_task()
{
    double arr[30];
    for (int i =0; i < 30; i++)
    {
        arr[i] = (double)(rand())/RAND_MAX*(20 + 20) - 20;
    }

    for (int i = 0; i < 30; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";
}


void third_task()
{
    double arr[20];
    for (int i =0; i < 20; i++)
    {
        arr[i] = (double)(rand())/RAND_MAX*(40 - 0) - 0;
        if (arr[i] < 0) {arr[i] *= -1;}
    }

    for (int i = 0; i < 20; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";
}

void fourth_task()
{
    double arr[35];
    for (int i =0; i < 35; i++)
    {
        arr[i] = 1 + rand() % (1000 - 1 + 1);
        if (arr[i] < 0) {arr[i] *= -1;}
    }

    for (int i = 0; i < 35; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";
}

void fifth_task()
{
    double arr[27];
    for (int i =0; i < 27; i++)
    {
        arr[i] = 1 + rand() % (20 - 1 + 1);
        if (arr[i] < 0) {arr[i] *= -1;}
    }

    for (int i = 0; i < 27; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";
}


void sixth_task()
{
    int n = int(1 + rand() % (30 - 1 + 1));
    if (n < 0) {n *= -1;}
    double arr[n];
    for (int i =0; i < n; i++)
    {
        arr[i] = (double)(rand())/RAND_MAX*(100 + 100) - 100;
    }

    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";

}

void seventh_task()
{
    char arr[5];
    int rnd_ascii;
    for (int i = 0; i < 5; i++)
    {
        rnd_ascii = 97 + rand() % (122 - 97 + 1);
        if (rnd_ascii < 0) {rnd_ascii *= -1;}
        while (rnd_ascii == arr[0] || rnd_ascii == arr[1] || rnd_ascii == arr[2] || rnd_ascii == arr[3] || rnd_ascii == arr[4])
        {
            rnd_ascii = 97 + rand() % (122 - 97 + 1);
            if (rnd_ascii < 0) {rnd_ascii *= -1;}
        }
        arr[i] = rnd_ascii;
    }

    for(int i = 0; i < 5; i++){
        cout << arr[i] << " ";
    }

}