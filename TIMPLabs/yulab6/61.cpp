/*
            Автомаобиль
          /   
Транспорт
          \
            Поезд  -  Экспресс

Атрибуты speed, capacity, model, type, и number_of_seats_in_the_carriage объявлены как члены классов и защищены от прямого доступа извне классов. Они объявлены с модификатором доступа private или protected.

Методы move() в каждом классе предоставляют интерфейс для взаимодействия с объектами классов. Они используют данные атрибутов классов для вывода информации, но сами атрибуты остаются скрытыми.

Класс TransportVehicle служит базовым классом, и его атрибуты и методы ограничены доступом через наследование.

В функции main(), объекты классов создаются и используются через указатели на базовый класс TransportVehicle, что демонстрирует полиморфизм и инкапсуляцию.

Таким образом, код демонстрирует принцип инкапсуляции, так как он ограничивает доступ к данным и методам классов, предоставляя интерфейс для взаимодействия с объектами.


for (int i = 0; i < 3; i++) { ... }: В цикле проходим по массиву vehicles. На каждой итерации вызываем метод move() для объекта, на который указывает указатель vehicles[i]. Это демонстрирует принцип полиморфизма, так как метод move() будет вызван в соответствии с типом объекта (класса) в массиве.


*/


#include <iostream>
#include <string>
using namespace std;

class TransportVehicle {
public:
    // Конструктор класса, указываем что класс принимает два параметра
    TransportVehicle(int speed, int capacity) : speed(speed), capacity(capacity) {}

    // Виртуальная функция базового класса, может быть переопределена в классах-наследниках 
    virtual void move() {
        cout << "The vehicle is moving." << endl;
    }

// Параметры, доступные наследникам
protected:
    int speed;
    int capacity;
};


// Класс автомобиля, наследует классу транспорта
class Car : public TransportVehicle {
public:
    // Конструктор класса                               speed и capacity из додителя, model по передаваемому значению
    Car(int speed, int capacity, string model) : TransportVehicle(speed, capacity), model(model) {}

    // Переопределение функции move из родительского класса
    void move() override {
        cout << "Car " << model << " moves at speed" << speed << " km/h" << endl;
    }

// Параметры, доступные только для этого класса
private:
    string model;
};


// Класс поезда, наследует классу транспорта
class Train : public TransportVehicle {
public:
    // Конструктор класса                                speed и capacity из родителя, type из передаваемых значений
    Train(int speed, int capacity, string type) : TransportVehicle(speed, capacity), type(type) {}
    
    // Прееопределение функции move из родителя
    void move() override {
        cout << "Train type" << type << "  moves at speed" << speed << " km/h" << endl;
    }

// Параметры, доступные наследникам
protected: 
    string type;
};



// Класс экспресса, наследует поезду
class Express : public Train {
public:
    // Конструктор класса                                                          speed, capacity и trainType из родителя, number_of_seats_in_the_carriage передается по значению
    Express(int speed, int capacity,  string trainType, int number_of_seats_in_the_carriage): Train(speed, capacity, trainType), number_of_seats_in_the_carriage(number_of_seats_in_the_carriage) {}


    void move() override {
        cout << "Express type " << type << " with " << number_of_seats_in_the_carriage << " ... moves at speed " << speed << " km/h" << endl;
    }

private:
    int number_of_seats_in_the_carriage;
};



int main() {
    cout << "Сreating objects of the classes car, train, express" << endl;
    cout <<"\nCreating a car class object: " << endl;
    cout << "Enter car make: "; string model; cin >> model;
    cout << "Enter car speed: "; int speed; cin >> speed;
    cout << "Enter car capacity: "; int capacity; cin >> capacity;
    Car car(speed, capacity, model);

    cout <<"\nCreating a train class object: " << endl;
    cout << "Enter train type: "; string type; cin >> type;
    cout << "Enter train speed: "; cin >> speed;
    cout << "Enter train capacity: "; cin >> capacity;
    Train train(speed, capacity, type);

    cout <<"\nCreating a exspress class object: " << endl;
    cout << "Enter exsprees type: "; type; cin >> type;
    cout << "Enter number of seats in the carriage: "; int number_of_seats_in_the_carriage; cin >>number_of_seats_in_the_carriage;
    cout << "Enter train speed: "; cin >> speed;
    cout << "Enter train capacity: "; cin >> capacity;
    Express express(speed, capacity, type, number_of_seats_in_the_carriage);

    // Массив указателей на классы
    TransportVehicle* vehicles[] = {&car, &train, &express};

    for (int i = 0; i < 3; i++) {
        vehicles[i]->move();
    }

}
