#include <iostream>

using namespace std;


void inputArray(int m,unsigned int array[]);
void outputArray(int m,unsigned int array[]);
void quicksort(int index_start,int index_end,unsigned int array[]);

int main(){
    int n;
    cout << "input n: "; cin >> n;
    cout << "input array of " << n << " elements: \n";
    unsigned int arr[n];
    inputArray(n,arr);

    cout << "unsorted array: " << endl;
    outputArray(n,arr);

    cout << "\nsorting process: " << endl;    
    quicksort(0,n-1,arr);

    cout << "\nsorted array: " << endl;
    outputArray(n,arr);

    return 0;
}

int part = 0;

void quicksort(int index_start,int index_end,unsigned int array[]){

    // cout << "Part " << part << ": "; outputArray(index_end+1,array);
    // part++;
    // cout << "\nStart(Curr) - " << index_start << ", End - " << index_end << endl;
    
    // Check args to stop recursion
    if(index_start >= index_end) return;

    // Sort by [min,pivot,max]
    int pivot = array[index_end];
    int index_curr = index_start; // current index
    
    for(int i = index_start; i < index_end; i++){
        if(array[i] <= pivot){
            swap(array[i],array[index_curr]);
            index_curr++;
        }
    }
    swap(array[index_end],array[index_curr]);

    // cout << "New curr index - " << index_curr << endl;

    quicksort(index_start,index_curr - 1,array); 
    quicksort(index_curr + 1, index_end, array); 

}

void inputArray(int m,unsigned int array[]){
    for(int i = 0; i < m; i++){
        cin >> array[i];
    }
}

void outputArray(int m,unsigned int array[]){
    for(int i = 0; i < m; i++){
        cout << array[i] << " ";
    }
    cout << endl;
}