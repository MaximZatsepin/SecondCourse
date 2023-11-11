#include <iostream>
#include <Windows.h>

using namespace std;

class House {
public:
	void showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y);
	void show(const HWND& hwnd, const HDC& dc, const int X, const int Y);
};

void House::showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	/*ФАСАД ДОМА*/
	HBRUSH  brush = CreateSolidBrush(RGB(254, 220, 180));
	SelectObject(dc, brush);
	Rectangle(dc, X, Y + 260, X + 130, Y + 400);
	DeleteObject(brush);

	/*ТРЕУГОЛЬНАЯ КРЫША*/
	POINT poly[3] = { {X,Y + 260 },{ X + 130 , Y + 260 },{X + 130 / 2, Y + 200} };
	brush = CreateHatchBrush(HS_DIAGCROSS, RGB(199, 214, 255));
	SelectObject(dc, brush);
	Polygon(dc, poly, 3);
	DeleteObject(brush);

	/*БОК КРЫШИ ПАРАЛЛЕЛОГРАММ*/
	POINT poly2[4] = { {X + 65,Y + 200 },{X + 280 , Y + 200 },{X + 345,Y + 260}, {X + 130, Y + 260 } };
	brush = CreateHatchBrush(HS_HORIZONTAL, RGB(0, 0, 255));
	SelectObject(dc, brush);
	Polygon(dc, poly2, 4);
	DeleteObject(brush);

	/*БОК ДОМА*/
	brush = CreateHatchBrush(7, RGB(254, 220, 180));
	SelectObject(dc, brush);
	Rectangle(dc, X + 345, Y + 260, X + 130, Y + 400);
	DeleteObject(brush);

	/*ДВЕРЬ*/
	brush = CreateSolidBrush(RGB(175, 75, 0));
	SelectObject(dc, brush);
	Rectangle(dc, X + 70, Y + 320, X + 110, Y + 400);
	DeleteObject(brush);

	/*ОКНА*/
	brush = CreateHatchBrush(HS_BDIAGONAL, RGB(0, 255, 0));
	SelectObject(dc, brush);
	Rectangle(dc, X + 150, Y + 300, X + 190, Y + 340);
	Rectangle(dc, X + 220, Y + 300, X + 260, Y + 340);
	Rectangle(dc, X + 290, Y + 300, X + 330, Y + 340);
	DeleteObject(brush);

}

void House::show(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	/*ФАСАД ДОМА*/
	SelectObject(dc, GetStockObject(NULL_BRUSH));
	Rectangle(dc, X, Y + 260, X + 130, Y + 400);

	/*ТРЕУГОЛЬНАЯ КРЫША*/
	POINT poly[3] = { {X,Y + 260 },{ X + 130 , Y + 260 },{X + 130 / 2, Y + 200} };
	Polygon(dc, poly, 3);

	/*БОК КРЫШИ ПАРАЛЛЕЛОГРАММ*/
	POINT poly2[4] = { {X + 65,Y + 200 },{X + 280 , Y + 200 },{X + 345,Y + 260}, {X + 130, Y + 260 } };
	Polygon(dc, poly2, 4);

	/*БОК ДОМА*/
	Rectangle(dc, X + 345, Y + 260, X + 130, Y + 400);

	/*ДВЕРЬ*/
	Rectangle(dc, X + 70, Y + 320, X + 110, Y + 400);

	/*ОКНА*/
	Rectangle(dc, X + 150, Y + 300, X + 190, Y + 340);
	Rectangle(dc, X + 220, Y + 300, X + 260, Y + 340);
	Rectangle(dc, X + 290, Y + 300, X + 330, Y + 340);

}

class Lake {
public:
	void showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y);
};

void Lake::showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	HBRUSH  brush = CreateSolidBrush(RGB(20, 20, 180));
	SelectObject(dc, brush);
	Ellipse(dc, X + 300, Y + 300, X + 800, Y + 200);
	DeleteObject(brush);
}

class Tree {
	void line(const HDC& dc, const int x1, const int y1, const int x2, const int y2) {
		MoveToEx(dc, x1, y1, 0);
		LineTo(dc, x2, y2);
	}
public:
	void showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y);
	void show(const HWND& hwnd, const HDC& dc, const int X, const int Y);
};

void Tree::showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	HBRUSH brush = CreateSolidBrush(RGB(0, 255, 0));
	SelectObject(dc, brush);
	line(dc, X, Y, X, Y - 150); //Здесь 150 - это высота ствола, рисование идёт снизу к верху.

	/*Рисуем ветки*/
	line(dc, X, Y - 75, X - 50, Y - 75 - 15);
	//circle == (X,Y, X+R, Y+R)
	Ellipse(dc, X - 70, Y - 105, X - 40, Y - 105 + 30); //Подгоняем листья

	line(dc, X, Y - 95, X - 50, Y - 95 - 15);
	Ellipse(dc, X - 75, Y - 120, X - 50, Y - 120 + 15); //Подгоняем листья
	Ellipse(dc, X - 50, Y - 130, X - 35, Y - 120 + 15); //Подгоняем листья

	Ellipse(dc, X - 10, Y - 150, X + 10, Y - 150 + 15); //Подгоняем листья

	line(dc, X, Y - 60, X + 80, Y - 165);  //Ветви
	line(dc, X, Y - 90, X + 30, Y - 135);
	line(dc, X + 25, Y - 95, X + 85, Y - 100);
	line(dc, X + 50, Y - 130, X + 55, Y - 170);

	Ellipse(dc, 15 + X + 55, 15 + Y - 170, 15 + X + 75, 15 + Y - 190); //листва
	Ellipse(dc, 15 + X + 34, 15 + Y - 180, 15 + X + 46, 15 + Y - 200);
	Ellipse(dc, 25 + X + (-5), 25 + Y - 120, 25 + X + 7, 25 + Y - 144);
	Ellipse(dc, 15 + X + 55, 15 + Y - 103, 15 + X + 75, 15 + Y - 127);
	Ellipse(dc, 15 + X + 10, 15 + Y - 140, 15 + X + 28, 15 + Y - 158);


	DeleteObject(brush);

};

void Tree::show(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	SelectObject(dc, CreateSolidBrush(RGB(255,255,255)));
	line(dc, X, Y, X, Y - 150); //Здесь 150 - это высота ствола, рисование идёт снизу к верху.

	/*Рисуем ветки*/
	line(dc, X, Y - 75, X - 50, Y - 75 - 15);
	//circle == (X,Y, X+R, Y+R)
	Ellipse(dc, X - 70, Y - 105, X - 40, Y - 105 + 30); //Подгоняем листья

	line(dc, X, Y - 95, X - 50, Y - 95 - 15);
	Ellipse(dc, X - 75, Y - 120, X - 50, Y - 120 + 15); //Подгоняем листья
	Ellipse(dc, X - 50, Y - 130, X - 35, Y - 120 + 15); //Подгоняем листья

	Ellipse(dc, X - 10, Y - 150, X + 10, Y - 150 + 15); //Подгоняем листья

	line(dc, X, Y - 60, X + 80, Y - 165);  //Ветви
	line(dc, X, Y - 90, X + 30, Y - 135);
	line(dc, X + 25, Y - 95, X + 85, Y - 100);
	line(dc, X + 50, Y - 130, X + 55, Y - 170);

	Ellipse(dc, 15 + X + 55, 15 + Y - 170, 15 + X + 75, 15 + Y - 190); //листва
	Ellipse(dc, 15 + X + 34, 15 + Y - 180, 15 + X + 46, 15 + Y - 200);
	Ellipse(dc, 25 + X + (-5), 25 + Y - 120, 25 + X + 7, 25 + Y - 144);
	Ellipse(dc, 15 + X + 55, 15 + Y - 103, 15 + X + 75, 15 + Y - 127);
	Ellipse(dc, 15 + X + 10, 15 + Y - 140, 15 + X + 28, 15 + Y - 158);

};

class Surname {
	void line(const HDC& dc, const int x1, const int y1, const int x2, const int y2) {
		MoveToEx(dc, x1, y1, 0);
		LineTo(dc, x2, y2);
	}
public:
	void show(const HWND& hwnd, const HDC& dc, const int X, const int Y);
};

void Surname::show(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	SelectObject(dc, GetStockObject(NULL_BRUSH));
	line(dc, X, Y, X, Y + 30);
	line(dc, X, Y + 15, X + 10, Y + 15);
	Ellipse(dc, X + 10, Y, X + 40, Y + 30); // Ю
	line(dc, X + 45, Y, X + 45, Y + 30);
	line(dc, X + 45, Y + 15, X + 65, Y + 15);
	line(dc, X + 65, Y, X + 65, Y + 30); // Н
	line(dc, X + 70, Y, X + 75, Y + 15);
	line(dc, X + 75, Y + 15, X + 80, Y + 15);
	line(dc, X + 85, Y, X + 75, Y + 30); // У
	Arc(dc, X + 90, Y, X + 115, Y + 30, X + 115, Y, X + 115, Y + 30); // С
	Ellipse(dc, X + 115, Y, X + 145, Y + 30); // О
	line(dc, X + 150, Y, X + 150, Y + 30);
	Arc(dc, X + 150, Y, X + 160, Y + 15,  X + 150, Y+30, X + 150, Y+5);
	Arc(dc, X + 150, Y + 15, X + 170, Y + 30, X + 160, Y + 45, X + 170, Y + 10);
	line(dc, X + 150, Y + 15, X + 165, Y + 15);
	line(dc, X + 150, Y + 30, X + 160, Y + 30); // В
	line(dc, X + 175, Y + 30, X + 185, Y);
	line(dc, X + 185, Y, X + 195, Y + 30);
	line(dc, X + 177.5, Y + 20, X + 192.5, Y + 20);

	//line(dc, X, Y, X, Y);
	//line(dc, X, Y, X, Y);
	//line(dc, X, Y, X, Y);


}

int main() {
	setlocale(LC_ALL, "rus");
	HWND hwnd = GetConsoleWindow(); //Берём ориентир на консольное окно (В нём будем рисовать)
	HDC dc = GetDC(hwnd); //Цепляемся к консольному окну
	RECT window = {}; //Переменная window будет использована для получения ширины и высоты окна
	HBRUSH brush; //Переменная brush - это кисть, она будет использоваться для закрашивания
	

	//1. Рисуем фон
	
	int choise;
	while (true) {
		cout << "Выберите, \
что нарисовать:\n1. Контур деревни\n2. Закрашенное озеро\n\
3. Деревню и лес\n4. Озеро, исключая лес\n5. Фамилия\n6. Очистить\n7. Выход\n";
		cin >> choise;
		switch (choise) {
		case 1:
			House house;
			house.show(hwnd, dc, 1400, 400);
			house.show(hwnd, dc, 500, 110);
			house.show(hwnd, dc, 900, -100);
			break;
		case 2:
			Lake Lake;
			Lake.showcolor(hwnd, dc, 400, 400);
			break;
		case 3:
			house.showcolor(hwnd, dc, 1400, 400);
			house.showcolor(hwnd, dc, 500, 110);
			house.showcolor(hwnd, dc, 900, -100);
			Tree tree;
			tree.showcolor(hwnd, dc, 500, 800);
			tree.showcolor(hwnd, dc, 700, 200);
			tree.showcolor(hwnd, dc, 1600, 300);
			break;
		case 4:
			Lake.showcolor(hwnd, dc, 400, 400);
			tree.show(hwnd, dc, 800, 800);
			break;
		case 5:
			Surname surname;
			surname.show(hwnd, dc, 225, 800);
			break;
		case 6:
			SelectObject(dc, CreateSolidBrush(RGB(255, 255, 255)));
			Rectangle(dc, 220, 0, 5000, 5000);
			break;
		case 7:
			exit(0);
		}

	}	
}
