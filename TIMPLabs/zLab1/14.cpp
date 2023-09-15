/*
Составить программу для игры в “кости”. Играющий называет любое число в диапазоне 
от 2 до 12 и ставку, которую он делает в этот ход. Программа с помощью генератора случайных 
чисел дважды выбирает числа от 1 до 6 (“бросает кубик”, на гранях которого цифры от 1 до 6). 
Если сумма выпавших очков меньше либо равна 7 и играющий назвал число меньше либо равное 7, 
он выигрывает ставку. Если сумма выпавших цифр больше 7 и играющий сделал ставку на число 
больше 7, он также выигрывает ставку. Если игрок угадал сумму цифр, он получает в 4 раза 
больше очков, чем сделанная ставка. Ставка проиграна, если ни одна из описанных ситуаций 
не имеет места. В начальный момент у игрока и компьютера по 100 очков. Игра идет до тех пор, 
пока у кого-либо из играющих останется 0 очков.  
*/

#include <iostream>
#include <time.h>
// #include <cmath>
#include <stdlib.h>

using namespace std;

// Формула для генератора от m до n
// num = m + rand() % (n - m + 1);

int main(){
    setlocale(LC_ALL,"russian");
    time_t t;
    srand(time(&t));
    // srand(time(nullptr));
    
    int playerCoin = 100;
    int compCoin = 100;

    while((playerCoin > 0) || (compCoin > 0)){

        int number;
        cout << "\nВведите число от 2х до 12ти: ";
        cin >> number;

        while(number < 2 or number > 12){
            cout << "Число неверное, введите повторно: "; cin >> number;
        }

        int compNumber = 2 + rand() % (12 - 2 + 1);
        playerCoin -= number;
        compCoin -= compNumber;

        int dice1 = 2 + rand() % (12 - 2 + 1);
        int dice2 = 2 + rand() % (12 - 2 + 1);

        cout <<"\nЧисло игрока: " << number << "\nЧисло компьютера: " << compNumber << "\nЧисла на кубиках: " 
             << dice1 << " " << dice2 << endl;
        
        if((dice1+dice2 <= 7 and number <=7) || (dice1+dice2 > 7 and number > 7)) {
            cout << "\nВы победили! Вы получаете " << number*4 << " монет!";
            playerCoin += number*4;
        }
        else {
            cout << "\nВы проиграли. Вы потеряли " << number << " монет.";
        }

        if((dice1+dice2 <= 7 and compNumber <=7) || (dice1+dice2 > 7 and compNumber > 7)) {
            cout << "\nКомпьютер победил! Он получает " << number*4 << " монет!";
            playerCoin += number*4;
        }
        else {
            cout << "\nКомпьютер проиграл. Он потерял " << number << " монет.";
        }
        
        cout << "\nСейчас у вас: " << playerCoin << " монет.";
        cout << "\nУ компьютера: " << compCoin << " монет.";
    }

    cout << "\n\nИгра окончена!";
}