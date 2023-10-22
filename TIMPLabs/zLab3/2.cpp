// https://www.youtube.com/watch?v=4s-aG6yGGLU
#include <iostream>
#include <time.h>
#include <chrono>
using namespace std;
void createArray(unsigned int arr[],int n);
void outputArray(unsigned int arr[],int n);
void quickSort(unsigned int arr[], int start, int end);
void swap(int& a, int& b);


int main(){
    auto start = chrono::steady_clock::now(); // starting time
    srand(time(nullptr));
    int n; cin >> n;
    unsigned int arr[n]; 
    createArray(arr,n);

    cout << "\nunsorted array: \n";
    outputArray(arr,n);

    quickSort(arr,0,n);

    cout << "\nsorted array: \n";
    outputArray(arr,n);
    auto dur = chrono::steady_clock::now() - start; // counting program time
    cout << "\ntime delay: " << chrono::duration_cast<chrono::microseconds>(dur).count()/1000 << " millisec" << endl;
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

    // stack - array of pair(sectors) ex: 0,5 is 0-5 sector
    int stack[end-start+1];
    int index = 0;
    stack[index++] = start;
    stack[index++] = end;
    // cout << "stack - "; outputArray(stack,6);

    while(index > 0){
        // cout << "index - " << index << endl;
        end = stack[--index];
        start = stack[--index];
        // cout << "start, end = " << start << " " << end<< endl;
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
        // cout << "--------------------------------" <<endl;
        if(minIndex - 1 > start){
            stack[index++] = start;
            stack[index++] = minIndex - 1;
        }
        if(minIndex + 1 < end){
            stack[index++] = minIndex + 1;
            stack[index++] = end;
        }
        // outputArray(arr,end);
        // cout << "stack - "; outputArray(stack,6);
        // cout << "index - " << index << endl;
    }
}

void swap(int& a, int& b) 
{
    int temp = a;
    a = b;
    b = temp;
}