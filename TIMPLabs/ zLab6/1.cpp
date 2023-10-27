/*

Необходимость использования инкапсуляции 
На моем примере:
1) Без инкапсуляции мы можем случайно изменить тип двигателя, что влечёт за собой большую неточность
В общих чертах:
1) Прежде всего это понимание, чем больше программа/класс - тем сложнее контролировать данные. Инкапсуляция может решить это
2) Безопасность - методы представляют возможность редактировать только то, что нужно.

           -> ДВС -> Дизельный двигатель
          /
Двигатель    
          \
           -> Реактивный двигатель 
*/

#include <iostream>
#include <string.h>

using namespace std;


class Engine {

    private:
        string engineType;
        string name;
        string serialNumber;
        int weight;

    public:
        Engine(){
            name = "Undefined";
            serialNumber = "Undefined";
            weight = 0;
            setType("undefined");
        }
        void setType(string type){
            this->engineType = type;
        }
        string getType(){ return this->engineType; }
        void setParams(string name, string sr, int weight = 0){
            this->name = name;
            this->serialNumber = sr;
            this->weight = weight;
        }
        void getParams(){
            cout << "\nEngine: " << this->name;
            cout << "\nType: " << getType();
            cout << "\nSerial number: " << this->serialNumber << "\nWeight: " << this->weight << "kg" << endl;
        }
};

class JetEngine : public Engine{
    private:
        int thrust; // Тяга
        float egt; // Температура выходящих газов ExhaustGasTemperature
    public:
        JetEngine(){
            thrust = 0;//500; // H (Ньютон)
            egt = 0;//4000; // K (Кельвинов)
            setType("Jet");
        }
        void setJetParams(string name, string sr, int weight, int thrust, float egt){
            setParams(name,sr,weight);
            this->thrust = thrust;
            this->egt = egt;
        }
        void getJetParams(){
            getParams();
            cout <<"Thrust: " << this->thrust; 
            cout << "H\nExhaust Gas Temperature: " << this->egt << "*C" << endl;
        }

};

class DVS : public Engine{ // Двигатель внутреннего сгорания
    private:
        int horsepower;
        int enginecapacity; // Объем двигателя
    public:
        DVS(){
            horsepower = 0;
            enginecapacity = 0;
            setType("DVS");
        }
        void setDVSParams(string name, string sr, int weight,int hp, int engcapacity){
            setParams(name,sr,weight);
            this->horsepower = hp;
            this->enginecapacity = engcapacity;
        }
        void getDVSParams(){
            getParams();
            cout <<"Horsepower: " << this->horsepower;
            cout << "\nEngine Capacity: " << this->enginecapacity << " litres" << endl;
        }

};

class DieselEngine : public DVS{
    private:
        float efficiency; // Расход топлива на 100км
    public:
        DieselEngine(){
            efficiency = 0.0;
            setType("DVS-Diesel");
        }
        void setDieselParams(string name, string sr, int weight,int hp, int engcapacity,float efficiency){
            setDVSParams(name,sr,weight,hp,engcapacity);
            this->efficiency = efficiency;
        }
        void getDieselParams(){
            getDVSParams();
            cout << "Efficiency: " << this->efficiency << " l/100km" << endl;
        }
};



int main(){

    // Engine eng;
    // eng.setParams("Saturn", "Bk105M", 60);
    // eng.getParams();

    // JetEngine eng1;
    // eng1.setJetParams("Neptun","React12",400,500,4012.25);
    // eng1.getJetParams();

    // DVS eng2;
    // eng2.setDVSParams("Uran","AS3",72,200,60);
    // eng2.getDVSParams();

    // DieselEngine eng3;
    // eng3.setDieselParams("Byran","HDF23",152,440,65,7.4);
    // eng3.getDieselParams();
    JetEngine eng;
    
    while(true){
        cout << "Выберите действие:";
        cout << "\n1 - Заполнить новый двигатель\n2 - Заполните новый реактивный двигатель";
        cout << "\n3 - Вывести реактивный двигатель";
        cout << "\n0 - Выход из программы\n";

        

        int choise; cin >> choise;
        switch (choise)
        {
        case 1:
            break;
        case 2:{
            cout << "\nЗаполните данные для реактивного двигателя:";
            cout << "\nИмя - "; string name; cin >> name;
            cout << "Серийный номер - "; string sr; cin >> sr;
            cout << "Вес - "; int weight; cin >> weight;
            cout << "Тяга - "; int thrust; cin >> thrust;
            cout << "Температура выходящих газов - "; int tvx; cin >> tvx;
            eng.setJetParams(name,sr,weight,thrust,tvx);
            break;
        }
        case 3:{
            eng.getJetParams();
            break;
        }
            


        
        default:
            exit(0);
            break;
        }
        
    }



    cout << endl;
    return 0;
}



