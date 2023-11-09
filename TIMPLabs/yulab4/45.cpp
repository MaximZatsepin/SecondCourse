/*
[Task 5] - Напишите программу, которая находит путь (кратчайший, если есть), в
сгенерированном с помощью датчика случайных чисел лабиринте.
Примерный лабиринт изображен на рис. 12 Для нахождения пути
используйте алгоритм волновой алгоритм или алгоритм с возвратом.
*/


#include<iostream>
#include<time.h>
#include <iomanip>

using namespace std;

void output_maze(int** maze, int height, int width);
void create_maze(int** maze, int height, int width); 

void convert(int** maze, int height, int width);
void solution(int** maze,int height, int width);
bool occupied_or_not(int** maze,int i, int j, int height, int width);

void output_matrix(int** matrix,int m, int n);



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

const int ground = 0, tunnel = 1;

int main(){
	
	time_t t;
    srand(time(&t));
	
    cout << "\n\n[Task 5] - maze\n" << endl;

    // Ввод высоты и ширины лабиринта (нечетные)
	int height; cout << "Enter the height of the maze(odd from 5 to 99): "; cin >> height;
    while (height % 2 == 0 or height > 100 or height < 5) {cout << "Enter the height of the maze(odd from 5 to 99): "; cin >> height;}
    int width; cout << "Enter the width of the maze(odd from 5 to 99): "; cin >> width;
	while (width % 2 == 0 or width > 100 or width < 5) { cout << "Enter the width of the maze(odd from 5 to 99): "; cin >> width;}

    cout << "\n"; 

    // Освобождение динамической памяти
	int** maze = new int*[height]; 
	for(int i = 0; i < height; i++)
		maze[i] = new int[width];
	
	create_maze(maze, height, width);

    // Устанавливаем ячейки - входы
    maze[0][1] = tunnel;
    maze[height-1][width-2] = tunnel;
	
	output_maze(maze,height,width);

    convert(maze, height,width);
    
	
    solution(maze,height,width);
    
    output_matrix(maze, height, width);

    // Очищение памяти
	for (int i = 0; i < height; ++i) {
        delete[] maze[i];
    }
    delete[] maze;

}


void output_maze(int** maze, int height, int width){
	for(int i = 0; i < height; i++){
		for(int j = 0; j < width; j++)
			switch(maze[i][j]){
				case ground: cout<<"0 "; break;
				case tunnel: cout<<"  "; break;
			}
		cout<<endl;
		}
	cout<<endl;	
}


void create_maze(int** maze, int height, int width){
	int x, y, c, a; 
	bool b;
	// Заполняем весь массив землей(ground), в которой будем "прорывать" тоннели
	for(int i = 0; i < height; i++) 
		for(int j = 0; j < width; j++)
			maze[i][j] = ground;
	
    // Точка начала копания и счетчик 
	x = 3; y = 3; a = 0; 
    // Счетчик, итераций достаточный для составления лабиринта 
    int count_itera = height*width;
    if (height> width){count_itera *= int(height/10);}
    else if (height < 10 and width < 10){count_itera = count_itera;}
    else count_itera *= int(width/10);


	while(a < count_itera){ 
        // Устанавливаем на этой клетке "проход"
		maze[y][x] = tunnel; a++;
        // Цикл, который будет останавливаться когда крот зайдет в тупик
		while(1){ 
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
                    // Передвигаем крота вверх на 2 клетки
					y-=2;
				}
                // Вниз
				case 1: if(y != height-2)      
					if(maze[y+2][x] == ground){
					maze[y+1][x] = tunnel;
					maze[y+2][x] = tunnel;
					y+=2;
				}
                // Влево
				case 2: if(x != 1)
					if(maze[y][x-2] == ground){ 
					maze[y][x-1] = tunnel;
					maze[y][x-2] = tunnel;
					x-=2;
				}
                // Вправо
				case 3: if(x != width-2)
					if(maze[y][x+2] == ground){
					maze[y][x+1] = tunnel;
					maze[y][x+2] = tunnel;
					x+=2;
				}
			}
			break;
		}
        do{
            x = 2*(rand()%((width-1)/2))+1;
            y = 2*(rand()%((height-1)/2))+1;
        }
        // Пока мы по рандомным координатам не переместимся в тоннель(не в землю)
        while(maze[y][x] != tunnel);
	}
}


void solution(int** maze,int height, int width)
{
    Cell queue[height*width+1];
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
        if(curr.i == height-1 && curr.j == width - 2){
            cout << "\nShortest distance is " << curr.distance << endl;
            return;
        }
        // Просматриваем 4 соседних клетки данной клетки
        for(int k = 0; k < 4; k++){
            // определяем координаты соседней клетоки
            int I = curr.i + coord_i[k], J = curr.j + coord_j[k];

            // Если клетка свободна (равна 0) и находится в пределах озера
            if(occupied_or_not(maze, I, J, height, width)){
                // Обновляем дистанцию до данной клетки
                maze[I][J] = curr.distance + 1;
                // Добавляем клетку в очередь (ее координаты и дистанцию)
                queue[qEnd].distance = curr.distance + 1;
                queue[qEnd].i = I;
                queue[qEnd++].j = J;
            }
        }
    }
    cout << "\nPath not found!\n" << endl;
}

bool occupied_or_not(int** maze,int  i, int j, int height, int width)
{
    return i >= 0 && i < height && j >= 0 && j < width && maze[i][j] == 0;
}


void convert(int** maze, int height, int width){
    for(int i = 0; i < height; i++){
		for(int j = 0; j < width; j++)
			switch(maze[i][j]){
				case ground: maze[i][j] = -1; break;
				case tunnel: maze[i][j] = 0; break;
			}
		}
}



void output_matrix(int** matrix,int m, int n){
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            cout << setw(2) << matrix[i][j] << " ";
        }
        cout << endl;
    }
}


