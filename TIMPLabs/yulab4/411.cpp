/*
[Вариант 6]
1.	Написать программу поиска кратчайшие расстояния от 1-й вершины до
всех остальных для графа согласно своего варианта (см. Рис. 11),
используя алгоритм Флойда. Составьте таблицу
сравнения двух алгоритмов по времени или количеству шагов.
*/

#include <iostream>
#include <chrono>   // Для измерения времени выполнения
using namespace std;

const int max_vertices = 8; // Количество вершин в графе
const int INF = 1e9; // "Бесконечность" для инициализации расстояний

void solution(int graph[][max_vertices], int distances[][max_vertices]);
void output_distances(int distances[][max_vertices]);


int main() 
{
    int graph[max_vertices][max_vertices] = {{0,1,0,0,4,3,0,5},
                                             {1,0,7,6,0,0,0,2},
                                             {0,7,0,8,0,0,0,0},
                                             {0,6,8,0,3,2,0,0},
                                             {4,0,0,3,0,0,0,0},
                                             {3,0,0,2,0,0,5,1},
                                             {0,0,0,0,0,5,0,4},
                                             {5,2,0,0,0,1,4,0}};
    
    // Стартовая точка отсчета времени
    auto start = chrono::steady_clock::now();

    cout << "\n[Task 1] - Floyd's algorithm\n\n" ;

    // Двумерный массив, в котором будут храниться дистанции от i пункта до j
    int distances[max_vertices][max_vertices];

    // Запускаем функцию обработки
    solution(graph, distances);

    // Вывод результата
    output_distances(distances);

    // Конечная точка, вывод времени работы
    auto dur = chrono::steady_clock::now() - start;
    cout <<"\ntime: " << chrono::duration_cast<chrono::milliseconds>(dur).count() << " mcs\n" << endl;

}



// Функция для выполнения алгоритма Флойда
void solution(int graph[][max_vertices], int distances[][max_vertices]) 
{
    // Переносим в этот массив значения из графа, оставляя нули на главной диагонали, остальные заменяя на INF 
    for (int i = 0; i < max_vertices; ++i) 
    {
        for (int j = 0; j < max_vertices; ++j) 
        {
            if (i != j && graph[i][j] == 0) {distances[i][j] = INF;}   // Инициализируем нулевые значения "бесконечностью" если они не на глав.диагонали
            else {distances[i][j] = graph[i][j];}     // Остальные просто переносим
        }
    }

    // Основной цикл алгоритма Флойда
    for (int k = 0; k < max_vertices; ++k) {
        for (int i = 0; i < max_vertices; ++i) {
            for (int j = 0; j < max_vertices; ++j) {
                //cout << i << k << " " << k << j << " " << i<< j << endl;
                // Если (i,k) и (k,j) не INF и их сумма меньше (i,j), то наименьшей дистанцией будет (i,k) + (k,j)
                if (distances[i][k] != INF && distances[k][j] != INF && distances[i][k] + distances[k][j] < distances[i][j]) 
                {
                    distances[i][j] = distances[i][k] + distances[k][j];
                }
            }
        }
    }  
}



void output_distances(int distances[][max_vertices])
{
    for (int i = 0; i < max_vertices; i++)
    {
        for (int j = 0; j < max_vertices; j ++)
        {
            cout << distances[i][j] << " ";
        }
        cout << endl;
    }
}