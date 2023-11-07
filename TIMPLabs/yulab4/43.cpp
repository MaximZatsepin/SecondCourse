#include <iostream>
#include <iomanip>
#include <time.h>

using namespace std;

void create_lake(int lake[][100],int m, int n);
void output_matrix(int matrix[][100],int m, int n);

void solution(int lake[][100],int m, int n);
bool occupied_or_not(int lake[100][100],int i, int j, int m, int n);

void stars_path(char path[][100], int lake[][100], int m, int n);

// Структура ячейки для стека
struct Cell{
    int i;
    int j;
    // Кратчайший путь до клетки
    int distance;
};

// Перебор клеток сверху/снизу/слева/справа
const int coord_i[] = {0,0,-1,1};
const int coord_j[] = {-1,1,0,0};



int main() 
{
    time_t t;
    srand(time(&t));

    int m; cout << "Enter m: "; cin >> m;
    int n; cout << "Enter n: "; cin >> n;
    cout << "\n";

    int lake[100][100];

    cout << "[Task 3 - Lake]" << endl;

    create_lake(lake,m,n);
    cout << "Lake: \n\n";
    output_matrix(lake,m,n);

    solution(lake,m,n);

    lake[0][0] = 0;
    cout << "\nPath: \n\n";
    output_matrix(lake,m,n);
}


void create_lake(int lake[][100],int m, int n)
{
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int random = rand() % 10;
            if (random <= 2) {lake[i][j] = -1;}
            else lake[i][j] = 0;
        }
    }
    lake[0][0] = 0;
    lake[m - 1][n - 1] = 0;
}



void output_matrix(int matrix[][100],int m, int n)
{
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            cout << setw(2)  << matrix[i][j] << " ";
        }
        cout << endl;
    }
}


bool occupied_or_not(int lake[][100],int  i, int j, int m, int n)
{
    return i >= 0 && i < m && j >= 0 && j < n && lake[i][j] == 0;
}

void solution(int lake[][100],int m, int n)
{
    Cell queue[m*n+1];
    int qStart = 0, qEnd = 1;
    // queue[qStart] = {0,0,0};
    // Устанавливаем начальную клетку с координатами (0,0) и ее дистанцию (0)
    queue[qStart].distance = 0;
    queue[qStart].i = 0;
    queue[qStart].j = 0;
    
    // Пока начало очереди меньше конца
    while(qStart < qEnd){
        // Извлекаем следующую клетку очереди
        Cell curr = queue[qStart++];
        // Когда мы приходим к нижней правой клетке, мы выводим результат
        if(curr.i == m - 1 && curr.j == n - 1){
            cout << "\nShortest distance is " << curr.distance << endl;
            return;
        }
        // Просматриваем 4 соседних клетки данной клетки
        for(int k = 0; k < 4; k++){
            // определяем координаты соседней клетоки
            int I = curr.i + coord_i[k], J = curr.j + coord_j[k];

            // Если клетка свободна (равна 0) и находится в пределах озера
            if(occupied_or_not(lake, I, J, m, n)){
                // Обновляем дистанцию до данной клетки
                lake[I][J] = curr.distance + 1;
                // Добавляем клетку в очередь (ее координаты и дистанцию)
                queue[qEnd].distance = curr.distance + 1;
                queue[qEnd].i = I;
                queue[qEnd++].j = J;
            }
        }
    }
    cout << "\nPath not found!\n" << endl;
}
