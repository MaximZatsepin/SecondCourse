/*
Task: adding\editing\deleting data in .json file 
throught c++ using nlohmann/json.hpp

- Include
    #include "json.hpp"
    using json = nlohmann::json;

- Parsing
    ifstream file ("data.json");
    json data = json::parse(file);
    data[][]....
    cout << data;

- Add Examples
    data["123"]["1"] = {1, "privet"};
    ofstream file1("data.json");
    file1 << setw(4) << data;
    file1.close();
    ifstream file("data.json");
    json data = json::parse(file);
    auto carModels = data["Car models"];
    cout << carModels;
    carModels["3"] = { 3,"Toyota camry" };
    file.close();
    ofstream file1("data.json");
    data["Car models"] = carModels;
    file1 << setw(4) << data;
    file1.close();

- Delete Examples
    auto newData = data["Car models"];
    newData.erase("1");
    data["123"] = newData;
*/

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "json.hpp"

using json = nlohmann::json;
using namespace std;

struct WorkType {
    int idWork; string typeWork;
    bool checkWorkType() { return (!((this->idWork == 0) + (this->typeWork == ""))); }
};

struct CarModel {
    int idCar; string nameCar;
    bool checkCarModel() { return (!((this->idCar == 0) + (this->nameCar == ""))); }
};

struct CarOwner {
    int idOwner; string nameOwner; string passportOwner;
    bool checkOwner() { return (!((this->idOwner == 0) + (this->nameOwner == "") + (this->passportOwner == ""))); }
};

struct Car {
    int idCar; string markCar; int idOwner; string numCar;
    // bool checkCar(){return (!((this->idCar == 0)+(this->markCar == "")+(this->idOwner == 0)+(this->numCar = "")));}
};

struct Master {
    int idMaster; string nameMaster; string passportMaster; string dateMaster;
    // bool checkMaster(){return (!((this->idMaster == 0)+(this->nameMaster == "")+(this->passportMaster == "")+(this->dateMaster = "")));}
};

struct Work {
    int idCar; int idWork; string startWork; string idMaster; string endWork; string descriptionWork;
};

void editTable(json& data);
void workType_functions(json& data);
void workType_out(json workTypes);
void carModel_functions(json& data);
void carModel_out(json carModels);
void carOwner_functions(json& data);
void carOwner_out(json carOwners);
void car_functions(json& data);
void car_out(json cars);
void master_functions(json& data);
void master_out(json masters);
void work_functions(json& data);
void work_out(json works);

