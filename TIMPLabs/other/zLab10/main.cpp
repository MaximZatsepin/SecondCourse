#include <iostream>
using namespace std;
int main() {
    short n; cin >> n;
    short arr[10000];
    for (short i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    float avg = 1001.0;
    short index = 6;
    short num;
    while(index < n){
        for(short i = 0; i < n-index; i++){
            num = (arr[i]+arr[i+index]) * 0.5;
            if (num < avg){ avg = num; }
        }
        ++index;
    }
    cout << avg << endl;
    cin.ignore();
    cin.get();
    return 0;
}