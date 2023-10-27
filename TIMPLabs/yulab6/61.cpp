#include <iostream>
#include <string>

class TransportVehicle {
public:
    TransportVehicle(int speed, int capacity) : speed(speed), capacity(capacity) {}

    virtual void move() {
        std::cout << "The vehicle is moving." << std::endl;
    }

protected:
    int speed;
    int capacity;
};

class Car : public TransportVehicle {
public:
    Car(int speed, int capacity, const std::string& model) : TransportVehicle(speed, capacity), model(model) {}

    void move() override {
        std::cout << "Car " << model << " moves at speed" << speed << " km/h" << std::endl;
    }

private:
    std::string model;
};

class Train : public TransportVehicle {
public:
    Train(int speed, int capacity, const std::string& type) : TransportVehicle(speed, capacity), type(type) {}

    void move() override {
        std::cout << "Train type" << type << "  moves at speed" << speed << " km/h" << std::endl;
    }

protected: // Изменили доступ на protected
    std::string type;
};



class Express : public Train {
public:
    Express(int speed, int capacity, const std::string& trainType, int passengerCount)
        : Train(speed, capacity, trainType), passengerCount(passengerCount) {}

    void move() override {
        std::cout << "Express type " << type << " with " << passengerCount << " ... moves at speed " << speed << " km/h" << std::endl;
    }

private:
    int passengerCount;
};

int main() {
    Car car(120, 5, "Sedan");
    Train train(100, 200, "Passazhirskiy");
    Express express(150, 100, "Exspress train", 300);

    TransportVehicle* vehicles[] = {&car, &train, &express};

    for (int i = 0; i < 3; i++) {
        vehicles[i]->move();
    }

    return 0;
}
