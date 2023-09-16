#include <iostream>
#include <time.h>
// #include <cmath>
#include <stdlib.h>

using namespace std;

void printStars(int n);

int main(){
    time_t t;
    srand(time(&t));
    // srand(time(nullptr));

    // Filling
    double arrOfVar[200];
    for(int i = 0; i < 200; i++){
        arrOfVar[i] = rand()%100000/100000.0;
    }

    // Output Variables
    int count = 1;
    cout << "\n\n[Task2]\n\n";
    for(int i = 0; i < 200; i++,count++){
        cout << arrOfVar[i] << " ";
        if (count == 10) {cout << endl; count = 0;}
    } cout << endl;

    // Fill array for Gystogram
    int arrOfCount[10] = {0,0,0,0,0,0,0,0,0,0};
    for(int i = 0; i < 200; i++){
        // cout << "switchNumber - " << int(arrOfVar[i]*10) << endl;
        arrOfCount[int(arrOfVar[i]*10)] += 1;
    }

    // Output Gystogram
    double varForPrintGyst = 0.0;
    for(int i = 0; i < 10; i++){
        if(i == 0){ cout << "0.0-" << varForPrintGyst+0.1 << ": (" << arrOfCount[i] << ") "; printStars(arrOfCount[i]); }
        else if(i == 9){ cout << varForPrintGyst << "-1.0: (" << arrOfCount[i] << ") "; printStars(arrOfCount[i]); }
        else { cout << varForPrintGyst << "-" << varForPrintGyst+0.1 << ": (" << arrOfCount[i] << ") "; printStars(arrOfCount[i]); }
        varForPrintGyst += 0.1;
    }
}

void printStars(int n){
    for(int i = 0; i < n; i++){
        cout << "*";
    }
    cout << endl;
}