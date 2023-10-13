// Task 3.1
// With Recursion

#include <iostream>
#include <time.h>

using namespace std;

void createArray(int n, unsigned int arr[]);
void outputArray(int n, unsigned int arr[]);
void quickSort(unsigned int arr[], int start, int end);
int partition(unsigned int arr[], int start, int end);

int main(){
    srand(time(nullptr));
    
    int n; cout << "Input n:"; cin >> n;
    unsigned int arr[n];
    createArray(n,arr);

    cout << "\nCreated array:\n";
    outputArray(n,arr);

    quickSort(arr,0,n-1);

    cout << "\nSorted array:\n";
    outputArray(n,arr);
    return 0;
}



void createArray(int n, unsigned int arr[]){
    for(int i = 0; i < n; i++){
        arr[i] = 17070 + rand() % (37707 - 17070 + 1);
    }
}

void outputArray(int n, unsigned int arr[]){
    for(int i = 0; i < n; i++){
        cout << arr[i] << " ";
        if((i+1)%10==0) cout << endl;
    }
    cout << endl;
}

void quickSort(unsigned int arr[], int start, int end){
    
    if(end <= start) return;

    int pivot = partition(arr,start,end);
    quickSort(arr,start,pivot-1);
    quickSort(arr,pivot + 1, end);
}

int partition(unsigned int arr[], int start, int end){ // return location of pivot
    
    int pivot = arr[end];
    int i = start - 1;

    for(int j = start; j <= end - 1; j++){
        if(arr[j] < pivot){
            i++;
            swap(arr[i],arr[j]);
        }
    }
    swap(arr[++i],arr[end]);

    return i;
}