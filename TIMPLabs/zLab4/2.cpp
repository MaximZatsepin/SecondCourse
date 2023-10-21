// Алгоритм Дейкстры
#include <iostream>
using namespace std;

int main(){
    int n = 6;
    // definition for matrix of links
    int graph[n][n] = {{0,0,0,5,6,9},
                       {0,0,7,8,5,8},
                       {0,7,0,4,5,4},
                       {5,8,4,0,0,0},
                       {6,5,5,0,0,0},
                       {9,8,4,0,0,0}};
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
    }
    
    cout << "\nShortest distances: ";
    for(int i = 0; i < n; i++){
        cout << ranges[i] << " ";
    }

}