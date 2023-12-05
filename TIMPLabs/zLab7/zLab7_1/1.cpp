#include <iostream>
#include <windows.h>

using namespace std;

int w = 200;

class Path {
	void line(const HDC& dc, const int x1, const int y1, const int x2, const int y2) {
		MoveToEx(dc, x1, y1, 0);
		LineTo(dc, x2, y2);
	}
public:
	void showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y);
};

void Path::showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	HBRUSH brush = CreateHatchBrush(HS_DIAGCROSS, RGB(139, 69, 19));
	SelectObject(dc, brush);
	int s = 0;
	for (int j = -20; j < 260; j += 20) {
		for (int i = -65; i < 101; i += 30)
		{
			Rectangle(dc, X + i + s, Y - j, X + i + 30 + s, Y - j + 20);
		}
		s += 10;
	}

	for (int j = 240; j > 140; j -= 20) {
		for (int i = -10; i < 750; i += 30)
		{
			Rectangle(dc, X + i + s, Y - j, X + i + 30 + s, Y - j + 20);
		}
		s += 10;
	}

	for (int j = 240; j > -20; j -= 20) {
		for (int i = 720; i < 900; i += 30)
		{
			Rectangle(dc, X + i + s, Y - j, X + i + 30 + s, Y - j + 20);
		}
		s += 10;
	}

	for (int j = 260; j < 600; j += 20) {
		for (int i = 150; i < 350; i += 30)
		{
			Rectangle(dc, X + i + s, Y - j, X + i + 30 + s, Y - j + 20);
		}
	}


	DeleteObject(brush);

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
	void showcolor2(const HWND& hwnd, const HDC& dc, const int X, const int Y);
};

void Tree::showcolor(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	HBRUSH brush = CreateSolidBrush(RGB(0, 255, 0));
	SelectObject(dc, brush);

	Ellipse(dc, X - 80, Y - 100, X - 30, Y - 160 + 15);
	Ellipse(dc, X - 100, Y - 80, X - 50, Y - 140 + 15);
	Ellipse(dc, X - 60, Y - 80, X - 10, Y - 140 + 15);



	line(dc, X - 55, Y - 70, X - 80, Y - 85);
	line(dc, X - 55, Y - 70, X - 40, Y - 85);
	line(dc, X - 55, Y - 70, X - 55, Y - 50);


	DeleteObject(brush);

};

void Tree::showcolor2(const HWND& hwnd, const HDC& dc, const int X, const int Y) {
	SelectObject(dc, CreateSolidBrush(RGB(255, 255, 255)));

	Ellipse(dc, X - 70, Y - 110, X - 40, Y - 150 + 15);
	Ellipse(dc, X - 90, Y - 90, X - 60, Y - 130 + 15);
	Ellipse(dc, X - 50, Y - 90, X - 20, Y - 130 + 15);


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
	Arc(dc, X + 60, Y, X + 110, Y + 15, X + 100, Y + 30, X + 85, Y + 5);
	Arc(dc, X + 60, Y + 15, X + 110, Y + 30, X + 110, Y + 45, X + 100, Y + 10);

	line(dc, X + 110, Y + 30, X + 120, Y);
	line(dc, X + 120, Y, X + 130, Y + 30);
	line(dc, X + 115, Y + 20, X + 125, Y + 20);

	line(dc, X + 140, Y, X + 140, Y + 30);
	line(dc, X + 140, Y + 30, X + 160, Y + 30);
	line(dc, X + 160, Y, X + 160, Y + 30);
	line(dc, X + 160, Y + 30, X + 165, Y + 30);
	line(dc, X + 165, Y + 30, X + 165, Y + 35);

	line(dc, X + 170, Y, X + 170, Y + 30);
	line(dc, X + 170, Y, X + 190, Y);
	line(dc, X + 170, Y + 15, X + 190, Y + 15);
	line(dc, X + 170, Y + 30, X + 190, Y + 30);

	line(dc, X + 200, Y, X + 200, Y + 30);
	line(dc, X + 220, Y, X + 220, Y + 30);
	line(dc, X + 200, Y, X + 220, Y);

	line(dc, X + 230, Y, X + 230, Y + 30);
	line(dc, X + 250, Y, X + 250, Y + 30);
	line(dc, X + 230, Y + 30, X + 250, Y);

	line(dc, X + 260, Y, X + 260, Y + 30);
	line(dc, X + 280, Y, X + 280, Y + 30);
	line(dc, X + 260, Y + 15, X + 280, Y + 15);

}



int main() {
	setlocale(LC_ALL, "rus");
	HWND hwnd = GetConsoleWindow();
	HDC dc = GetDC(hwnd);
	RECT window = {};
	HBRUSH brush;

	cin.get();
	Path Path;

	Lake Lake;
	Lake.showcolor(hwnd, dc, 330, 400);

	Path.showcolor(hwnd, dc, 300, 700);
	w = 220;
	Tree tree;
	for (int j = 0; j < 3; j++)
	{
		for (int i = 1100; i <= 1700; i += 100)
		{
			tree.showcolor(hwnd, dc, i, w);
		}
		w += 120;
	}
	w = 220;
	for (int j = 0; j < 3; j++)
	{
		for (int i = 130; i <= 800; i += 100)
		{
			tree.showcolor(hwnd, dc, i, w);
			tree.showcolor2(hwnd, dc, i, w);
		}
		w += 120;
	}

	Surname surname;
	surname.show(hwnd, dc, 225, 800);

}

