/*
            Автомаобиль  -  Легковое авто
          /   
Транспорт
          \
            Грузовик



const = 0 в объявлении чисто виртуальной функции указывает, что функция является абстрактной (полностью виртуальной) 
и не имеет реализации в базовом классе. Это означает, что любой класс, наследующий этот интерфейс (или абстрактный класс), 
обязан предоставить свою реализацию этой функции.
*/


#include <iostream>
#include <string>
using namespace std;

// Абстрактный класс, являющийся базовым классом для всех видов транспорта
class Transport {
// Доступны только классам-наследникам
protected:
    // Значение скорости
    int speed;
    // Вместимость
    int capacity;

// Публичные
public:
    // Конструктор класса
    Transport(int speed, int capacity) : speed(speed), capacity(capacity) {}

    // Чисто виртуальная функция, делающая класс абстрактным
    virtual void move() const = 0;

    // Виртуальные методы
    virtual int getSpeed() const {
        return speed;
    }

    virtual int getCapacity() const {
        return capacity;
    }

};

// Класс Автомобиля
class Car : public Transport {
// Приватные значения
protected:
    string model;
// Публичные
public:
    // Конструктор
    Car(int speed, int capacity, string model) : Transport(speed, capacity), model(model) {}

    // Переопределение виртуального метода
    void move() const override {
        cout << "Car " << model << " moves at speed " << speed << " km/h" << endl;
    }
};

// Класс Легкового автомобиля
class PassengerCar : public Car {
// Приватные значения
private:
    string purpose;
// Публичные
public:
    // Конструктор
    PassengerCar(int speed, int capacity, string model, string purpose) : Car(speed, capacity, model), purpose(purpose) {}

    // Переопределение виртуального метода
    void move() const override {
        cout << "Passenger car " << model << " moves at speed " << speed << " km/h" << endl;
    }
};

// Класс Грузовика
class Truck : public Transport {
// Приватные
private:
    string cargoType;
// Публичные
public:
    // Конструктор
    Truck(int speed, int capacity, string cargoType) : Transport(speed, capacity), cargoType(cargoType) {}
    // Переопределение виртуального метода
    void move() const override {
        cout << "Truck carrying " << cargoType << " moves at speed " << speed << " km/h" << endl;
    }
};



int main() {
    cout << "Сreating objects of the classes Car, Truck, Passenger car" << endl;
    cout <<"\nCreating a Car class object: " << endl;
    cout << "Enter model: "; string model; cin >> model;
    cout << "Enter speed: "; int speed; cin >> speed;
    cout << "Enter capacity: "; int capacity; cin >> capacity;
    Car* car1 = new Car(speed, capacity, model);

    cout <<"\nCreating a Truck class object: " << endl;
    cout << "Enter type: "; cin >> model;
    cout << "Enter speed: "; cin >> speed;
    cout << "Enter capacity: "; cin >> capacity;
    Truck* truck1 = new Truck(speed, capacity, model);

    cout <<"\nCreating a Passenger car class object: " << endl;
    cout << "Enter type: "; cin >> model;
    cout << "Enter speed: "; cin >> speed;
    cout << "Enter capacity: "; cin >> capacity;
    cout << "Enter purpose: "; string purpose; cin >> purpose;
    PassengerCar* passCar = new PassengerCar(speed, capacity, model, purpose);
    cout << "\n\n";
    // По указателяи на созданные объекты вызываем в каждом из них переопределенную функцию move
    car1->move();
    truck1->move();
    passCar->move();


}
