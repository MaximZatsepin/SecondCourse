/*
[Task 4] - Составьте алгоритм и напишите программу, находящую строку длиной
100 символов, состоящую только из букв "A", "B", "C", такую, что в ней
никакие две соседние подстроки не равны друг другу. Воспользуйтесь
перебором с возвратом.
*/

#include <iostream>
#include <string>
#include <random>
#include <time.h>

using namespace std;

bool isValidSubstring(const string& str) {
    for (size_t i = 0; i < str.length() - 2; ++i) {
        if (str.substr(i, 3) == str.substr(i + 3, 3)) {
            return false;
        }
    }
    return true;
}

int main() {

    string randomString;

    time_t t;
    srand(time(&t));
    int n; cout<<"Enter string lenght: "; cin >> n;
    for (int i = 0; i < n; ++i) {
        int random = rand()%3;
        if (random==0){randomString += 'A';}
        else if (random==1){randomString += 'B';}
        else if (random==2){randomString += 'C';}
        
    }
    cout << "\n[Task 3] - strings\n" << endl;

    cout << "Generated random string (lenght " << n<<  ") : " << randomString << "\n\n" << endl;

    for (int i = 0; i <= randomString.length() - 100; ++i) {
        if (isValidSubstring(randomString.substr(i, 100))) {
            cout << "Substring found at position " << i << ": " << randomString.substr(i, 100) << endl;
            return 0;
        }
    }


    cout << "No such substring exists in the generated string." << endl;
}
