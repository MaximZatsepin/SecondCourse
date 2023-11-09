// Алгоритм Флойда
// https://www.youtube.com/watch?v=ipWZ-d1l00s
#include <iostream>
#include <chrono>
using namespace std;

int main(){
    auto start = chrono::steady_clock::now();
    int steps = 0;
    int n = 8;
    // definition for matrix of links
    int graph[100][100] = {{10000,3,10000,10000,2,1,10000,5},
                       {3,10000,4,1,10000,10000,10000,6},
                       {10000,4,10000,2,10000,10000,10000,10000},
                       {10000,1,2,10000,3,4,10000,10000},
                       {2,10000,10000,3,10000,10000,10000,10000},
                       {1,10000,10000,4,10000,10000,5,7},
                       {10000,10000,10000,10000,10000,5,10000,6},
                       {5,6,10000,10000,10000,7,6,10000}};
    // Vij = min(Vij,Vik+Vkj), i,j,k = 1,...,N


    for(int k = 0; k < n; k++){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int newDist = graph[i][k] + graph[k][j];
                if((graph[i][j] > newDist) && ((i != j) && (j != k) && (i != k))){
                    steps++;
                    graph[i][j] = newDist;
                }
            }
        }
    }
    for(int i = 0; i < n; i++){
        graph[i][i] = 0;
    }
    
    // Output matrix
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cout << graph[i][j] << " ";
        }
    cout << endl;
    }
    
    cout << "\nShortest distances: ";
    for(int i = 0; i < n; i++){
        cout << graph[0][i] << " ";
    }

    auto dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
    cout << "Time delay: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " microsec";
    cout << " Steps: " << steps;
}