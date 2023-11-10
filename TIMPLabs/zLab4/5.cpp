// https://habr.com/ru/articles/319532/
/*
Напишите программу, которая находит путь (кратчайший, если есть), в
сгенерированном с помощью датчика случайных чисел лабиринте.
Примерный лабиринт изображен на рис. 12 Для нахождения пути
используйте алгоритм волновой алгоритм или алгоритм с возвратом.
*/


#include<iostream>
#include<time.h>
#include <iomanip>

using namespace std;

void output(int** maze, int m, int n);
void create_maze(int** maze, int m, int n); 

void waveAlgorithm(int** maze,int m, int n);
bool isCellConsist(int** maze,int i, int j, int m, int n);
void restorePath(int **maze, int m, int n);
bool isCellConsist2(int** maze,int  i, int j, int m, int n);

struct Cell{
    int i;
    int j;
    int dist;
};

const int di[] = {0,0,-1,1};
const int dj[] = {-1,1,0,0};
const int ground = -1, tunnel = 0;

int main(){
	time_t t;
    srand(time(&t));
	int m,n;
    cout << "m,n is: "; cin >> m >> n;

	int** maze = new int*[m]; 
	for(int i = 0; i < m; i++)
		maze[i] = new int[n];
	create_maze(maze, m, n);

    maze[0][1] = tunnel;
    maze[m-1][n-2] = tunnel;
	// output(maze,m,n);
    output(maze, m, n);
    //convert(maze, m,n);
    
    waveAlgorithm(maze,m,n);
    output(maze, m, n);

    restorePath(maze,m,n);
    output(maze,m,n);
    // output_matrix(maze, m, n);
}


void output(int** maze, int m, int n){
	for(int i = 0; i < m; i++){
		for(int j = 0; j < n; j++){
            if(maze[i][j] == ground) cout << setw(2) << "■■";
            else if(maze[i][j] == tunnel) cout << setw(2) << "  ";
            else if(maze[i][j] == -2) cout << setw(2) << ". ";
            else cout << setw(2) << maze[i][j];
        }
        cout << endl;
	}
	cout<<endl;	
}

void create_maze(int** maze, int m, int n){
	int x, y, c, a; 
	bool b;
	// Заполняем весь массив землей(ground), в которой будем "прорывать" тоннели
	for(int i = 0; i < m; i++) 
		for(int j = 0; j < n; j++)
			maze[i][j] = ground;
	
    // Точка начала копания и счетчик 
	x = 3; y = 3; a = 0; 
    // Счетчик, итераций достаточный для составления лабиринта 
    int count_itera = m*n;
    if (m> n){count_itera *= int(m/10);}
    else if (m < 10 and n < 10){count_itera = count_itera;}
    else count_itera *= int(n/10);


	while(a < count_itera){ 
        // Устанавливаем на этой клетке "проход"
		maze[y][x] = tunnel; a++;
        // Цикл, который будет останавливаться когда крот зайдет в тупик
		// while(1){ 
            // Рандомно выбираем одно из 4 направлений (крот двигается на две клетки за раз!!!)
			c = rand()%4; 
			switch(c){ 
                // Вверх
				case 0: if(y != 1)    // Проверяем что мы не на верхней границе
                    // Если на две клетки вверх земля, то мы прорываем туда тоннель 
					if(maze[y-2][x] == ground)
                    {
                    // Прорываем тоннель
                    maze[y-1][x] = tunnel;
					maze[y-2][x] = tunnel;
                    // output(maze, m, n);
                    // Передвигаем крота вверх на 2 клетки
					y-=2;
				}
                // Вниз
				case 1: if(y != m-2)      
					if(maze[y+2][x] == ground){
					maze[y+1][x] = tunnel;
					maze[y+2][x] = tunnel;
                    // output(maze, m, n);
					y+=2;
				}
                // Влево
				case 2: if(x != 1)
					if(maze[y][x-2] == ground){ 
					maze[y][x-1] = tunnel;
					maze[y][x-2] = tunnel;
                    // output(maze, m, n);
					x-=2;
				}
                // Вправо
				case 3: if(x != n-2)
					if(maze[y][x+2] == ground){
					maze[y][x+1] = tunnel;
					maze[y][x+2] = tunnel;
                    // output(maze, m, n);
					x+=2;
				}
			}
			// break;
		// }
        
        do{
            x = 2*(rand()%((n-1)/2))+1;
            y = 2*(rand()%((m-1)/2))+1;
        }
        // Пока мы по рандомным координатам не переместимся в тоннель(не в землю)
        while(maze[y][x] != tunnel);

        
	}

}

void waveAlgorithm(int** maze,int m, int n){
    Cell queue[m*n+1];
    int qStart = 0, qEnd = 1;
    // queue[qStart] = {0,0,0};
    queue[qStart].dist = 0;
    queue[qStart].i = 0;
    queue[qStart].j = 0;
    

    while(qStart < qEnd){
        Cell curr = queue[qStart++];

        if(curr.i == m - 1 && curr.j == n - 1){
            return;
        }

        for(int k = 0; k < 4; k++){
            int I = curr.i + di[k], J = curr.j + dj[k];
            if(isCellConsist(maze, I, J, m, n)){
                maze[I][J] = curr.dist + 1;
                queue[qEnd].dist = curr.dist + 1;
                queue[qEnd].i = I;
                queue[qEnd++].j = J;
                // queue[qEnd++] = {I,J,curr.dist + 1};
            }
        }
    }
}

bool isCellConsist(int** maze,int  i, int j, int m, int n){
    return i >= 0 && i < m && j >= 0 && j < n && maze[i][j] == 0;
}

bool isCellConsist2(int** maze,int  i, int j, int m, int n){
    return i >= 0 && i < m && j >= 0 && j < n;
}

void restorePath(int **maze, int m, int n) {
    // printf("1");
    int i = m - 1, j = n - 2; // Начинаем с конечной точки
    int value = maze[i][j];
    while (value != 1) {
        // cout << "New lake[i][j] is " << maze[i][j] << endl;
        // cout << "value is " << value << endl;
        // printf("2");
        for (int k = 0; k < 4; ++k) {
            // printf("3");
            int I = i + di[k], J = j + dj[k];
            // cout << "I,J is "<<  I << " " << J << endl;
            // cout << isCellConsist2(lake, I, J, m, n) << endl;
            // cout << (isCellConsist2(lake, I, J, m, n) && lake[I][J] == lake[i][j] - 1) << endl;
            if (isCellConsist2(maze, I, J, m, n) && maze[I][J] == value - 1) {
                // printf("4");
                value = maze[I][J];
                maze[I][J] = -2; // Помечаем путь звёздочками (-2)
                i = I;
                j = J;
                // cout << "break! value - " << value << endl;
                break;
            }
        }
    }
}


