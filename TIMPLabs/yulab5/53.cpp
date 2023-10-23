// #include <iostream>
// using namespace std;


// const int max_size_lake = 50; // Максимальные размеры озера
// const int INF = 1e9;  // "Бесконечность" для инициализации расстояний




// // Очередь для BFS
// int queue[max_size_lake * max_size_lake][2];
// int front, rear;

// void bfs(int lake[][max_size_lake], int n, int m);


// int main() {

//     // // Ввод размеров озера и данных о клетках
//     // cout << "Enter lake size(<50):"; cin >> n;
//     // while (n > 50){cout << "Enter lake size(<50): "; cin >> n;}
//     // for (int i = 0; i < m; i++) {
//     //     for (int j = 0; j < m; j++) {
//     //         cin >> lake[i][j];
//     //     }
//     // }
//     // cout << "Enter raft size(<5): "; cin >> m;
//     // while(m > 5){cout << "Enter raft size(<5): "; cin >> m;}

//     // Тестовые данные. Размеры озера 10*10

//     int n = 10;
//     int lake[max_size_lake][max_size_lake] = {{0,0,0,0,0,0,0,0,0,0},
//                                              {0,0,0,1,0,0,0,1,0,0},
//                                              {1,0,0,0,0,0,0,1,0,0},
//                                              {0,0,0,0,0,0,0,0,0,0},
//                                              {0,0,1,0,0,1,0,0,0,0},
//                                              {0,1,1,0,0,0,0,0,0,1},
//                                              {0,0,0,0,0,0,0,0,1,1},
//                                              {0,0,0,1,1,0,0,0,0,0},
//                                              {0,0,0,0,0,0,0,0,0,0},
//                                              {0,1,0,0,0,0,1,0,0,0}};

//     int m = 1;

   


//     // Выполняем поиск в ширину
//     bfs(lake, n, m);

//     // Выводим кратчайший путь
//     cout << "Кратчайший путь до правого нижнего угла: " << path[m - 1][n - 1] << endl;

// }

// void bfs(int lake[][max_size_lake], int n, int m) {
//     // Для просмотра соседних клеток
//     int dx[] = {1, -1, 0, 0};
//     int dy[] = {0, 0, 1, -1};

//     int path[max_size_lake][max_size_lake]; // Матрица пути
//     // Инициализация матрицы пути
//     for (int i = 0; i < m; i++) {
//         for (int j = 0; j < n; j++) {
//             path[i][j] = INF;
//         }
//     }

//     // Инициализация очереди
//     front = rear = -1;

//     path[0][0] = 0;
//     enqueue(0, 0);

//     while (front != -1) {
//         int x, y;
//         dequeue(x, y);

//         for (int i = 0; i < 4; i++) {
//             int nx = x + dx[i];
//             int ny = y + dy[i];

//             if (isValid(nx, ny) && path[nx][ny] == INF) {
//                 path[nx][ny] = path[x][y] + 1;
//                 enqueue(nx, ny);
//             }
//         }
//     }
// }


// void enqueue(int x, int y) {
//     if (front == -1) {
//         front = rear = 0;
//         queue[rear][0] = x;
//         queue[rear][1] = y;
//     } else {
//         rear++;
//         queue[rear][0] = x;
//         queue[rear][1] = y;
//     }
// }

// void dequeue(int &x, int &y) {
//     x = queue[front][0];
//     y = queue[front][1];
//     if (front == rear) {
//         front = rear = -1;
//     } else {
//         front++;
//     }
// }

// // Проверка, является ли клетка в пределах озера и не является ли островом
// bool isValid(int x, int y) {
//     return x >= 0 && x < m && y >= 0 && y < n && lake[x][y] != 1;
// }





#include <iostream>
using namespace std;

const int MAX_M = 100; // Максимальные размеры озера
const int MAX_N = 100; // Максимальные размеры озера
const int INF = 1e9;  // "Бесконечность" для инициализации расстояний

int lake[MAX_M][MAX_N]; // Матрица озера
int path[MAX_M][MAX_N]; // Матрица пути
int m, n;               // Размеры озера

// Очередь для BFS
int queue[MAX_M * MAX_M][2];
int front, rear;


// Добавление в очередь
void enqueue(int x, int y) {
    if (front == -1) {
        front = rear = 0;
        queue[rear][0] = x;
        queue[rear][1] = y;
    } else {
        rear++;
        queue[rear][0] = x;
        queue[rear][1] = y;
    }
}


// Проверка очереди (извлечение)
void dequeue(int &x, int &y) {
    // Получаем xy из ячейки с индексом front
    x = queue[front][0];
    y = queue[front][1];
    // Если front равен rear, то очередь пуста
    if (front == rear) {
        front = rear = -1;
    }
    // Иначе двигаем front на след. ячейку 
    else {
        front++;
    }
}

// Проверка, является ли клетка в пределах озера и не является ли островом
bool isValid(int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n && lake[x][y] != 1;
}

void bfs() {
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};

    // Инициализация матрицы пути
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            path[i][j] = INF;
        }
    }

    // Обозначаем что очередь пуста
    front = rear = -1;
    path[0][0] = 0;
    // Заполняем очередь первым элементом (0,0)
    enqueue(0, 0);

    while (front != -1) {
        int x, y;
        dequeue(x, y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && path[nx][ny] == INF) {
                path[nx][ny] = path[x][y] + 1;
                enqueue(nx, ny);
            }
        }
    }
}

int main() {
    // Ввод размеров озера и данных о клетках
    cin >> m >> n;
    // for (int i = 0; i < m; i++) {
    //     for (int j = 0; j < n; j++) {
    //         cin >> lake[i][j];
    //     }
    // }

    int lake[m][n] = {{0,0,0,0,0,0,0,0,0,0},
                                             {0,0,0,1,0,0,0,1,0,0},
                                             {1,0,0,0,0,0,0,1,0,0},
                                             {0,0,0,0,0,0,0,0,0,0},
                                             {0,0,1,0,0,1,0,0,0,0},
                                             {0,1,1,0,0,0,0,0,0,1},
                                             {0,0,0,0,0,0,0,0,1,1},
                                             {0,0,0,1,1,0,0,0,0,0},
                                             {0,0,0,0,0,0,0,0,0,0},
                                             {0,1,0,0,0,0,1,0,0,0}};

    // Выполняем поиск в ширину
    bfs();

    // Выводим кратчайший путь
    cout << "Кратчайший путь до правого нижнего угла: " << path[m - 1][n - 1] << endl;

    return 0;
}
