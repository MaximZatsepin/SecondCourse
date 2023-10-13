// Алгоритм Дейкстры
#include <iostream>

using namespace std;

// Связный Неполный Неориентированный Взвешенный Граф Апостол
int main(){
    int n = 8;
    // Объявляется и инициализируется матрица связей
    int graph[n][n] = {{0,3,0,0,2,1,0,5},
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
            cout << graph[i][j] << " ";
        }
        cout << endl;
    }

    // Инициализация вершин и расстояний
    for(int i = 0; i < n; i++){
        distance[i] = 10000;
        is_visited[i] = false;
    }
    distance[begin_index] = 0;
    
    do{
        minindex = 10000;
        min = 10000;
        for(int i = 0; i < n; i++){
            // Если вершину ещё не обошли и вес меньше min
            if ((!is_visited[i]) && (distance[i] < min)){
                min = distance[i];
                minindex = i;
            }
        }
        // Добавляем найденный минимальный вес
        // к текущему весу вершины
        // и сравниваем с текущим минимальным весом вершины
        if (minindex != 10000){
            cout << "\nChosen Node: " << minindex << endl;
            for(int i = 0; i < n; i++){
                if(graph[minindex][i] > 0){
                    temp = min + graph[minindex][i];
                    if(temp < distance[i]){
                        distance[i] = temp;
                    } 
                }
            }
            is_visited[minindex] = true;
            
            cout << "\nRecount distances:\n";
            for (int i = 0; i < n; i++) {
                if (distance[i] != 10000) {
                    cout << "Distance to Node " << i+1 << " is " << distance[i] << " [" << is_visited[i] << "]" << endl;
                }
            }
        }
        
    }while(minindex < 10000);

    // Вывод кратчайших расстояний до вершин:
    cout << "\nShortest distances: ";
    for(int i = 0; i < n; i++){
        cout << distance[i] << " ";
    }

    return 0;
}







