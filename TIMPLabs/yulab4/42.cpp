/*
[Вариант 6]
2.	Оля (A), Маша (B), Витя (C), Дима (D), Ваня (E) и Катя (F) живут в
разных городах. Стоимость билетов из разных городов известна (рис. 10).
Добраться до городов можно разными способами. Определить
наименьшую сумму, которую нужно потратить, чтобы Оля могла
навестить каждого из своих друзей.
*/

#include <iostream>
#include <iomanip>
#include <chrono>   // Для измерения времени выполнения
using namespace std;

const int max_vertices = 6; // Количество вершин в графе
const int INF = 1e9; // "Бесконечность" для инициализации расстояний

void solution(int graph[][max_vertices], int distances[][max_vertices]);
void output_distances(int distances[][max_vertices]);
int all_city_min_cost(int distances[][max_vertices]);


int main() 
{
                                           // A B C D E F
    int graph[max_vertices][max_vertices] = {{0,0,0,5,6,9},
                                             {0,0,7,8,5,8},
                                             {0,7,0,4,5,4},
                                             {5,8,4,0,0,0},
                                             {6,5,5,0,0,0},
                                             {9,8,4,0,0,0}};
    
    // Двумерный массив, в котором будут храниться дистанции от i пункта до j
    int distances[max_vertices][max_vertices];

    cout << "\n[Task 2]\n\n" ;

    // Запускаем функцию обработки
    solution(graph, distances);

    // Выводи массива с маинимальными дистанциями
    output_distances(distances);

    // Минимальная стоимость для посещения всех городов из города А
    int res = all_city_min_cost(distances);
    cout << "\nMin cost: " << res << endl << endl;
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
            cout << setw(2)<< distances[i][j] << " ";
        }
        cout << endl;
    }
}


int all_city_min_cost(int distances[][max_vertices])
{
    
    int min_cost = 0;    // Начальное значение суммы равно 0

    // Создаем массив для отслеживания посещенных городов
    bool visited[max_vertices] = {false};
    visited[0] = true;    // Оля начинает с города A

    int current_city = 0;    // Текущий город, с которого начинаем

    for (int i = 1; i < max_vertices; ++i) 
    {
        int next_city;    // Следующий город, куда перейдет Оля
        int min_distance = INF;

        for (int j = 0; j < max_vertices; ++j) {
            // Если город не посещен и стоимость  поездки в него минимальна
            if (!visited[j] && distances[current_city][j] < min_distance) {
                next_city = j;    // Запоминаем индекс этого города
                min_distance = distances[current_city][j];     // Запоминаем его дистанцию (стоимость)
            }
        }

        visited[next_city] = true;    // Помечаем город как посещенный
        min_cost += min_distance;    // Прибавляем самый маленький по стоимость посещения город
        current_city = next_city;    // Отмечаем "текущим" городом тот, в который она поедет (в который стоимость минимальная)
    }
    return min_cost;
}