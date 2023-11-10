// Алгоритм Дейкстры
#include <iostream>
#include <chrono>
// auto start = chrono::steady_clock::now(); // Запоминаем время начала работы программы
// auto dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
// cout << "Время выполнения программы: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " микросекунд";
// start = chrono::steady_clock::now(); // Снова запоминаем время начала работы программы
using namespace std;

int main(){
    auto start = chrono::steady_clock::now();
    int steps = 0;
    int n = 8;
    // definition for matrix of links
    int graph[100][100] = {{0,3,0,0,2,1,0,5},
                       {3,0,4,1,0,0,0,6},
                       {0,4,0,2,0,0,0,0},
                       {0,1,2,0,3,4,0,0},
                       {2,0,0,3,0,0,0,0},
                       {1,0,0,4,0,0,5,7},
                       {0,0,0,0,0,5,0,6},
                       {5,6,0,0,0,7,6,0}};
                       // Обход матрицы:
                       // ------------>
                       // ------------>
                       // ------------>...
    int ranges[n];
    bool is_visited[n];
    int currIndex = 0; // Index for chosen Node
    // Filling arrays
    for(int i = 0; i < n; i++){
        ranges[i] = 1000000;
        is_visited[i] = false;
    }
    ranges[currIndex] = 0;

    while(currIndex != -1){
        // Recount ranges if !is_visited and graph+currNode less than ranges
        for(int i = 0; i < n; i++){
            if((!is_visited[currIndex]) && (graph[currIndex][i] != 0)){
                int weight = ranges[currIndex] + graph[currIndex][i];
                if(weight < ranges[i]) ranges[i] = weight;
            }
        }
        is_visited[currIndex] = true;

        // Recount new Node
        int min = 1000000;
        currIndex = -1;
        for(int i = 0; i < n; i++){
            if((!is_visited[i]) && (ranges[i] < min)){
                min = ranges[i];
                currIndex = i;
            }
        }
        steps += 1;
        for(int i = 0; i < n; i++){
            cout << ranges[i] << " ";
        }
        cout << endl;
    }
    
    cout << "\nShortest distances: ";
    for(int i = 0; i < n; i++){
        cout << ranges[i] << " ";
    }

    auto dur = chrono::steady_clock::now() - start; // Вычисляем время выполнения
    cout << "Time delay: " << chrono::duration_cast<chrono::microseconds>(dur).count() << " microsec";
    cout << " Steps: " << steps;
}