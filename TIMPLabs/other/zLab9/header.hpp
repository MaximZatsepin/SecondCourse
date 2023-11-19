#include <iostream>
#include <string>
#include <vector>

using namespace std;

/*
«Учёт работы автомастерской» Информация должна хранится в файле JSON
в следующей структуре:
1) вид работ (код вида работ, вид работ);
2) модели машин (код модели, название модели);
3) автовладелец (код автовладельца, ФИО автовладельца, серия паспорта, номер паспорта);
4) автомобиль (код автомобиля, модель, код автовладельца, номер автомобиля);
5) мастер (код мастера, ФИО мастера, паспортные данные, дата рождения);
6) работа (код автомобиля, код работ, дата начала работ, код мастера, 
   дата окончания работ,  описание проделанной работы).
*/

struct WorkType{
    int idWork; string typeWork;
    bool checkWorkType(WorkType work){ return ((work.idWork == 0) * (work.typeWork == "")); }
};

struct CarModel{
    int idCar; string nameCar;
    bool checkCarModel(CarModel model){ return ((model.idCar == 0) * (model.nameCar == "")); }
};

struct CarOwner{
    int idOwner;  
};


