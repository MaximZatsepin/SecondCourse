#include <iostream>
#include <iomanip>

using namespace std;

struct Point {
    int x;
    int y;
    int dist; // Расстояние от начальной точки
};

const int ROWS = 8;
const int COLS = 8;

int grid[ROWS][COLS] = {
    {0, 0, 0, 0, 0, 0, 0, 0},
    {0, -1, -1, 0, 0, -1, 0, 0},
    {0, 0, 0, 0, -1, -1, 0, 0},
    {0, -1, -1, 0, 0, 0, -1, 0},
    {0, -1, 0, 0, -1, 0, -1, 0},
    {0, 0, 0, 0, -1, 0, 0, 0},
    {0, -1, -1, 0, 0, 0, -1, 0},
    {0, 0, 0, 0, 0, 0, 0, 0}
};

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

bool isValid(int x, int y) {
    return x >= 0 && x < ROWS && y >= 0 && y < COLS && grid[x][y] == 0;
}

void waveAlgorithm(int startX, int startY, int endX, int endY) {
    Point queue[ROWS * COLS];
    int front = 0, rear = 1;
    queue[front] = {startX, startY, 0};

    while (front < rear) {
        Point current = queue[front++];
        
        if (current.x == endX && current.y == endY) {
            cout << "Кратчайшее расстояние: " << current.dist << endl;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newX = current.x + dx[i];
            int newY = current.y + dy[i];

            if (isValid(newX, newY)) {
                grid[newX][newY] = current.dist + 1;
                queue[rear++] = {newX, newY, current.dist + 1};
            }
        }
    }

    cout << "Путь не найден!" << endl;
}

int main() {
    int startX = 0, startY = 0;
    int endX = 7, endY = 7;

    waveAlgorithm(startX, startY, endX, endY);

    // Выводим результат на экран
    cout << "Озеро с кратчайшими расстояниями:" << endl;
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            cout << setw(2) << grid[i][j] << "\t";
        }
        cout << endl;
    }

    return 0;
}
