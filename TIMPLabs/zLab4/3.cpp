#include <iostream>
#include <iomanip>

using namespace std;
void addFields(int lake[][100],int m, int n);
void outputArray(int m, int array[]);
void outputMatrix(int matrix[][100],int m, int n);
void waveAlgorithm(int lake[][100],int m, int n);
bool isCellConsist(int lake[100][100],int i, int j, int m, int n);
bool isCellConsist2(int lake[100][100],int i, int j, int m, int n);
void restorePath(int lake[][100], int m, int n);

struct Cell{
    int i;
    int j;
    int dist;
};
const int di[] = {0,0,-1,1};
const int dj[] = {-1,1,0,0};

int main() {
    srand(time(nullptr));

    //int m = 8, n = 8;
    int m, n; cout << "m,n is: "; cin >> m >> n;
    int lake[100][100];

    addFields(lake,m,n);

    cout << "-----------------------\n";
    outputMatrix(lake,m,n);
    cout << "-----------------------\n";

    waveAlgorithm(lake,m,n);
    

    cout << "-----------------------\n";
    lake[0][0] = 0;
    outputMatrix(lake,m,n);
    cout << "-----------------------\n";
    restorePath(lake, m, n);
    outputMatrix(lake,m,n);
    cout << "-----------------------\n";

    return 0;
}

void restorePath(int lake[][100], int m, int n) {
    // printf("1");
    int i = m - 1, j = n - 1; // Начинаем с конечной точки
    int value = lake[i][j];
    while (value != 0) {
        // cout << "New lake[i][j] is " << lake[i][j] << endl;
        // cout << "value is " << value << endl;
        // printf("2");
        for (int k = 0; k < 4; ++k) {
            // printf("3");
            int I = i + di[k], J = j + dj[k];
            // cout << "I,J is "<<  I << " " << J << endl;
            // cout << isCellConsist2(lake, I, J, m, n) << endl;
            // cout << (isCellConsist2(lake, I, J, m, n) && lake[I][J] == lake[i][j] - 1) << endl;
            if (isCellConsist2(lake, I, J, m, n) && lake[I][J] == value - 1) {
                // printf("4");
                value = lake[I][J];
                lake[I][J] = -2; // Помечаем путь звёздочками (-2)
                i = I;
                j = J;
                // cout << "break! value - " << value << endl;
                break;
            }
        }
    }
}

void addFields(int lake[][100],int m, int n){
    // adds islands
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int probability = rand() % 11;
            if (probability <= 1) {
                lake[i][j] = -1;
            }else lake[i][j] = 0;
        }
    }
    lake[0][0] = 0;
    lake[m - 1][n - 1] = 0;
}

void outputArray(int m, int array[]) {
    for (int i = 0; i < m; i++) {
        cout << array[i] << " ";
    }
    cout << endl;
}

void outputMatrix(int matrix[][100],int m, int n){
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if (matrix[i][j] == -2) cout << setw(2) << " * ";
            else cout << setw(2) << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

bool isCellConsist2(int lake[][100],int  i, int j, int m, int n){
    return i >= 0 && i < m && j >= 0 && j < n;
}

bool isCellConsist(int lake[][100],int  i, int j, int m, int n){
    return i >= 0 && i < m && j >= 0 && j < n && lake[i][j] == 0;
}

void waveAlgorithm(int lake[][100],int m, int n){
    Cell queue[m*n+1];
    int qStart = 0, qEnd = 1;
    // queue[qStart] = {0,0,0};
    queue[qStart].dist = 0;
    queue[qStart].i = 0;
    queue[qStart].j = 0;
    

    while(qStart < qEnd){
        Cell curr = queue[qStart++];

        if(curr.i == m - 1 && curr.j == n - 1){
            cout << "\nShortest distance from UpLeft to DonwRight is " << curr.dist << endl;
            return;
        }

        for(int k = 0; k < 4; k++){
            int I = curr.i + di[k], J = curr.j + dj[k];
            if(isCellConsist(lake, I, J, m, n)){
                lake[I][J] = curr.dist + 1;
                queue[qEnd].dist = curr.dist + 1;
                queue[qEnd].i = I;
                queue[qEnd++].j = J;
                // queue[qEnd++] = {I,J,curr.dist + 1};
            }
        }
    }
    cout << "\npath not found!" << endl;
}
