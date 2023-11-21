/*

Написать программу согласно варианту, которая добавляет, удаляет и
изменяет записи данных, хранящиеся определенном формате в файле заданного
типа. Если файла нет, то необходимо его создавать, если все записи в файле
удалены, то файл должен оставаться пустым. Также мы должны выводить
информацию из файла на экран в табличном виде. Для редактирования данных
необходимо небольшое простое меню.

*/

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
// #include "nlohmann/json.hpp"

// using json = nlohmann::json;

using namespace std;

struct WorkType{
    int idWork; string typeWork;
    bool checkWorkType(){ return (!((this->idWork == 0) + (this->typeWork == ""))); }
};

struct CarModel{
    int idCar; string nameCar;
    bool checkCarModel(){ return (!((this->idCar == 0) + (this->nameCar == ""))); }
};

struct CarOwner{
    int idOwner; string nameOwner; string passportOwner;
    bool checkOwner(){ return (!((this->idOwner == 0)+(this->nameOwner == "")+(this->passportOwner == ""))); }
};

struct Car {
    int idCar; string markCar; int idOwner; string numCar;
    // bool checkCar(){return (!((this->idCar == 0)+(this->markCar == "")+(this->idOwner == 0)+(this->numCar = "")));}
};

struct Master{
    int idMaster; string nameMaster; string passportMaster; string dateMaster;
    // bool checkMaster(){return (!((this->idMaster == 0)+(this->nameMaster == "")+(this->passportMaster == "")+(this->dateMaster = "")));}
};

struct Work{
    int idCar; int idWork; string startWork; string idMaster; string endWork; string descriptionWork;
};

int main(){
    // ifstream f("data.json");
    // json data = json::parse(f);
    // cout << data;
}

