
#include <iostream>
#include <time.h>
#include <chrono>
using namespace std;
void createArray(unsigned int arr[], int n);
void outputArray(unsigned int arr[],int n);
void quickSort(unsigned int arr[], int start, int end);
void swap(int& a, int& b);
void res_summa_and_series(unsigned int arr1[], int n);


int main(){
    auto start = chrono::steady_clock::now(); // starting time
    srand(time(nullptr));
    int n; cin >> n;
    unsigned int arr[n]; 
    createArray(arr,n);

    cout << "\nunsorted array: \n";
    outputArray(arr,n);

    res_summa_and_series(arr,n);

    quickSort(arr,0,n);

    cout << "\nsorted array: \n";
    outputArray(arr,n);
    auto dur = chrono::steady_clock::now() - start; // counting program time
    cout << "\ntime delay: " << chrono::duration_cast<chrono::microseconds>(dur).count()/1000 << " millisec" << endl;

    res_summa_and_series(arr,n);
}

void createArray(unsigned int arr[], int n){
    for(int i = 0; i < n; i++){
        arr[i] = 17070 + rand() % (37707 - 17070 + 1);
    }
}

void outputArray(unsigned int arr[],int n){
    for(int i = 0; i < n; i++){
        cout << arr[i] << " ";
        if((i+1) % 10 == 0) cout << endl;
    }
    cout << endl;
}

void quickSort(unsigned int arr[], int start, int end){

    if(end <= start) return;
    // cout << "start, end = " << start << " " << end << endl;
    unsigned int pivot = arr[end-1];
    // cout << "pivot = " << pivot << endl;
    int minIndex = start;

    for(int i = start; i < end; i++){
        if(arr[i] < pivot){
            swap(arr[i],arr[minIndex]);
            minIndex++;
        }
    }

    swap(arr[minIndex],arr[end-1]);
    // cout << "minindex = " << minIndex << endl;
    // cout << "--------------------------------" << endl;
    quickSort(arr,start,minIndex);
    quickSort(arr,minIndex + 1,end);
}

void swap(int& a, int& b) 
{
    int temp = a;
    a = b;
    b = temp;
}

void res_summa_and_series(unsigned int arr1[], int n){
    int summa = 0;
    int countSeries = 1;

    for (int i = 1; i < n; i++)
    {
        summa += arr1[i];
        if (arr1[i-1] > (arr1[i])) { countSeries++;}
    }
    cout << "\nQuickSort: \nSumma: " << summa << "\nSeries: " << countSeries << endl;
}