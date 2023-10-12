/*
Составить программу для игры в “кости”. Играющий называет любое число 
в диапазоне от 2 до 12 и ставку, которую он делает в этот ход. Программа с 
помощью генератора случайных чисел дважды выбирает числа от 1 до 6 (“бросает 
кубик”, на гранях которого цифры от 1 до 6). Если сумма выпавших очков меньше 
либо равна 7 и играющий назвал число меньше либо равное 7, он выигрывает 
ставку. Если сумма выпавших цифр больше 7 и играющий сделал ставку на число 
больше 7, он также выигрывает ставку. Если игрок угадал сумму цифр, он получает 
в 4 раза больше очков, чем сделанная ставка. Ставка проиграна, если ни одна 
из описанных ситуаций не имеет места. В начальный момент у игрока и компьютера 
по 100 очков. Игра идет до тех пор, пока у кого-либо из играющих останется 0 очков.  
*/

#include <iostream>
#include <time.h>
#include <stdlib.h>

using namespace std;


int main(){
    // Для генерации псевдорандомной последовательности
    time_t t;
    srand(time(&t));

    int n, game_bet;
    int points = 100;
    int comp_points = 100;
    
    int dice;

    cout << "[Task 4]" << endl; 

    while (points > 0 && comp_points > 0)
    {
        // Пользователь выбирает число
        cout << "\nEnter number(from 2 to 12): "; cin >> n;  
        while (n < 2 || n > 12) {cout << "The number must be in the range from 2 to 12 \nEnter number: "; cin >> n; }

        // Пользователь делает ставку
        cout << "Enter game bet (from 0 to " << points << "): "; cin >> game_bet;
        while (game_bet <=0 || game_bet > points) {cout << "The game bet must be greater than 0 and less than the number of points \nEnter game bet (from 0 to " << points << "): "; cin >> game_bet; }
        
        // Программа генерирует значения игральных костей
        dice = int(1 + rand() % (6 - 1 + 1)) + int(1 + rand() % (6 - 1 + 1));

        cout << "\nThere are " << dice << " points on the dice" << endl;

        // Проверка условий прогрыша/выигрыша
        if (dice == n) {cout << "\nYour winnings: "<< game_bet * 4 <<" points"; points += game_bet * 4; comp_points -= game_bet;}
        else if (dice <= 7 && n <= 7 || dice > 7 && n > 7 ) {cout << "\nYour winnings: "<< game_bet <<" points"; points += game_bet;comp_points -= game_bet;}
        else {cout << "\nYou lose: "<< game_bet <<" points"; points -= game_bet; comp_points += game_bet;}

        cout << "\n\nYour points: " << points << "\nComputer points: "<< comp_points;
        
        cout << "\n\n\n";
    }
    
    if (comp_points > points) {cout << "Computer won!";}
    else {cout << "You won!";}

}