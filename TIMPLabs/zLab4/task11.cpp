// Алгоритм Флойда
#include <iostream>

using namespace std;

// Связный Неполный Неориентированный Взвешенный Граф Апостол
int main(){
    int n = 8;
    // Объявляется и инициализируется матрица расстояния A
    int A[n][n] = {{0,3,0,0,2,1,0,5},
                       {3,0,4,1,0,0,0,6},
                       {0,4,0,2,0,0,0,0},
                       {0,1,2,0,3,4,0,0},
                       {2,0,0,3,0,0,0,0},
                       {1,0,0,4,0,0,5,7},
                       {0,0,0,0,0,5,0,6},
                       {5,6,0,0,0,7,6,0}};
    // Объявляется минимальное расстояние до вершин
    int distance[n];
    // Объявляются посещенные вершины
    bool is_visited[n];

    int temp, minindex, min;
    int begin_index = 0;

    // Вывод матрицы связей
    cout << "Matrix of links:\n";
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cout << A[i][j] << " ";
        }
        cout << endl;
    }

    // Инициализация вершин и расстояний
    for(int i = 0; i < n; i++){
        distance[i] = 10000;
        is_visited[i] = false;
    }
    distance[begin_index] = 0;
    
}







