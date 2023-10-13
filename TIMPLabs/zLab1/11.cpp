#include <iostream>
#include <time.h>

using namespace std;

int main(){
    // time_t t;
    // srand(time(&t));
    srand(time(nullptr));

    unsigned long int rand_var = rand();
    cout << endl << "rand_max = " << RAND_MAX << endl;
    cout << "\n[Task1] Random variable: " << rand_var << "\n\n";
}