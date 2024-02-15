#include <iostream>

using namespace std;

int main()
{
    int size_x, size_y;
    inputSize(size_x, size_y);
}

void inputSize(int &x, int &y)
{
    cout << "Рекомендуемые размеры 50х50\nВведите размеры поля:\n" << endl;
    while(x == NULL and y == NULL){
        cout << "x = "; cin >> x;
        cout << "y = "; cin >> y;
    }
}