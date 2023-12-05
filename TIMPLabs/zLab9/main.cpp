// NOT WORKING IN VS Code
// JUST USE AS EXAMPLE

#include "header.hpp"

int main() {

    // is_file_exist checker;

    cout << "Welcome to auto repair database! Choose a number: \
            \n1 - Output all database\n2 - Add/Delete something \
            \n3 - Exit\n";

    ifstream file("data.json");
    json data = json::parse(file);
    file.close();

    while (true) {
        int choise; cin >> choise;
        switch (choise) {
        case(0): cout << "\n\n\n\n\n\n\n\n\n\n\n\n"; break;
        case(1):
            cout << "----------------";
            workType_out(data["Work type"]);
            cout << "----------------";
            carModel_out(data["Car models"]);
            cout << "----------------";
            carOwner_out(data["Car owners"]);
            cout << "----------------";
            car_out(data["Cars"]);
            cout << "----------------";
            master_out(data["Masters"]);
            cout << "----------------";
            work_out(data["Works"]);
            cout << "----------------";
            break;
        case(2):
            editTable(data);
            break;
        case(3):
            exit(0);
        default:
            break;
        }
        cout << "\nChoose a number: \
                \n1 - Output all database\n2 - Add/Edit/Delete something \
                \n3 - Exit\n";
        ofstream file1("data.json");
        file1 << data;
        file1.close();
    }
    //file.close();
    //file1.close();
}

void editTable(json& data) {
    cout << "\nChoose part of table:\
             \n1 - Work type\
             \n2 - Car models\
             \n3 - Car owners\
             \n4 - Cars\
             \n5 - Masters\
             \n6 - Works\n";
    int choise; cin >> choise;
    switch (choise) {
    case(1):
        workType_functions(data);
        break;
    case(2):
        carModel_functions(data);
        break;
    case(3):
        carOwner_functions(data);
        break;
    case(4):
        car_functions(data);
        break;
    case(5):
        master_functions(data);
        break;
    case(6):
        work_functions(data);
        break;
    default:
        return;
    }
}

void workType_functions(json& data) {
    // output workType;
    // json data = json::parse(file);
    workType_out(data["Work type"]);
    cout << "\nChoose a type:\
             \n1 - Add\
             \n2 - Edit\
             \n3 - Delete\n";
    int choise; cin >> choise;
    WorkType a;
    // ifstream file("data.json");
    // ofstream file1("data.json");

    switch (choise) {
    case(1):
        cout << "\nInput id of work: "; cin >> a.idWork;
        cout << "Input type of work: "; cin.ignore(); getline(cin, a.typeWork);
        // check if_zapis_exist
        data["Work type"][to_string(a.idWork)] = { a.idWork, a.typeWork };
        // file1 << setw(4) << data;
        // file1 << setw(4) << data;
        cout << "Element added.";
        break;
    case(2):
        cout << "\nInput id of work: "; cin >> a.idWork;
        cout << "Input type of work: "; cin.ignore(); getline(cin, a.typeWork);
        data["Work type"][to_string(a.idWork)] = { a.idWork, a.typeWork };
        // file1 << setw(4) << data;
        // file1 << setw(4) << data;
        cout << "Element edited.";
        break;
    case(3):
        cout << "\nInput index of work: "; cin >> a.idWork;
        data["Work type"].erase(to_string(a.idWork));
        // file1 << setw(4) << data;
        cout << "Element deleted.";
        break;
    default:
        return;
    }
    // file.close();
    // file1.close();
}

void workType_out(json workTypes) {
    // ifstream file("data.json");
    // json data = json::parse(file);
    // json workTypes = data["Work type"];
    cout << endl;
    cout << "[ID, Work Type ]\n";
    for (auto work : workTypes) {
        cout << work << endl;
    }
    // file.close();
}

void carModel_functions(json& data) {

    carModel_out(data["Car models"]);
    cout << "\nChoose a type:\
             \n1 - Add\
             \n2 - Edit\
             \n3 - Delete\n";
    int choise; cin >> choise;
    CarModel a;

    switch (choise) {
    case(1):
        cout << "\nInput id of car: "; cin >> a.idCar;
        cout << "Input model of car: "; cin.ignore(); getline(cin, a.nameCar);
        // check if_zapis_exist
        data["Car models"][to_string(a.idCar)] = { a.idCar, a.nameCar };
        cout << "Element added.";
        break;
    case(2):
        cout << "\nInput id of car: "; cin >> a.idCar;
        cout << "Input model of car: "; cin.ignore(); getline(cin, a.nameCar);
        data["Car models"][to_string(a.idCar)] = { a.idCar, a.nameCar };
        cout << "Element edited.";
        break;
    case(3):
        cout << "\nInput id of car: "; cin >> a.idCar;
        data["Car models"].erase(to_string(a.idCar));
        cout << "Element deleted.";
        break;
    default:
        return;
    }
}

void carModel_out(json carModels) {
    cout << endl;
    cout << "[ID, Car Model ]\n";
    for (auto car : carModels) {
        cout << car << endl;
    }
}

void carOwner_functions(json& data) {

    carOwner_out(data["Car owners"]);
    cout << "\nChoose a type:\
             \n1 - Add\
             \n2 - Edit\
             \n3 - Delete\n";
    int choise; cin >> choise;
    CarOwner a;

    switch (choise) {
    case(1):
        cout << "\nInput id of owner: "; cin >> a.idOwner;
        cout << "Input owner name: "; cin.ignore(); getline(cin, a.nameOwner);
        cout << "Input owner passport: "; /*cin.ignore()*/; getline(cin, a.passportOwner);
        // check if_zapis_exist
        data["Car owners"][to_string(a.idOwner)] = { a.idOwner, a.nameOwner,a.passportOwner };
        cout << "Element added.";
        break;
    case(2):
        cout << "\nInput id of owner: "; cin >> a.idOwner;
        cout << "Input owner name: "; cin.ignore(); getline(cin, a.nameOwner);
        cout << "Input wner passport: "; cin.ignore(); getline(cin, a.passportOwner);
        data["Car owners"][to_string(a.idOwner)] = { a.idOwner, a.nameOwner,a.passportOwner };
        cout << "Element edited.";
        break;
    case(3):
        cout << "\nInput id of owner: "; cin >> a.idOwner;
        data["Car owners"].erase(to_string(a.idOwner));
        cout << "Element deleted.";
        break;
    default:
        return;
    }
}

void carOwner_out(json carOwners) {
    cout << endl;
    cout << "[ID, Car Owner, Passport of Owner ]\n";
    for (auto owner : carOwners) {
        cout << owner << endl;
    }
}

void car_functions(json& data) {

    car_out(data["Cars"]);
    cout << "\nChoose a type:\
             \n1 - Add\
             \n2 - Edit\
             \n3 - Delete\n";
    int choise; cin >> choise;
    Car a;

    switch (choise) {
    case(1):
        cout << "\nInput id of car: "; cin >> a.idCar;
        cout << "Input mark of car: "; cin.ignore(); getline(cin, a.markCar);
        cout << "Input owner ID: "; cin >> a.idOwner;
        cout << "Input number of car: "; cin.ignore(); getline(cin, a.numCar);
        // check if_zapis_exist
        data["Cars"][to_string(a.idCar)] = { a.idCar, a.markCar,a.idOwner,a.numCar };
        cout << "Element added.";
        break;
    case(2):
        cout << "\nInput id of car: "; cin >> a.idCar;
        cout << "Input mark of car: "; cin.ignore(); getline(cin, a.markCar);
        cout << "Input owner ID: "; cin >> a.idOwner;
        cout << "Input number of car: "; cin.ignore(); getline(cin, a.numCar);
        data["Cars"][to_string(a.idCar)] = { a.idCar, a.markCar,a.idOwner,a.numCar };
        cout << "Element edited.";
        break;
    case(3):
        cout << "\nInput id of car: "; cin >> a.idCar;
        data["Cars"].erase(to_string(a.idCar));
        cout << "Element deleted.";
        break;
    default:
        return;
    }
}

void car_out(json cars) {
    cout << endl;
    cout << "[ID, Car Mark, Owner ID, Car Number ]\n";
    for (auto car : cars) {
        cout << car << endl;
    }
}

void master_functions(json& data) {

    master_out(data["Masters"]);
    cout << "\nChoose a type:\
             \n1 - Add\
             \n2 - Edit\
             \n3 - Delete\n";
    int choise; cin >> choise;
    Master a;

    switch (choise) {
    case(1):
        cout << "\nInput id of master: "; cin >> a.idMaster;
        cout << "Input master name: "; cin.ignore(); getline(cin, a.nameMaster);
        cout << "Input master passport: "; getline(cin, a.passportMaster);
        cout << "Input master date of birth: "; getline(cin, a.dateMaster);
        // check if_zapis_exist
        data["Masters"][to_string(a.idMaster)] = { a.idMaster, a.nameMaster,a.passportMaster,a.dateMaster };
        cout << "Element added.";
        break;
    case(2):
        cout << "\nInput id of master: "; cin >> a.idMaster;
        cout << "Input master name: "; cin.ignore(); getline(cin, a.nameMaster);
        cout << "Input master passport: "; getline(cin, a.passportMaster);
        cout << "Input master date of birth: "; getline(cin, a.dateMaster);
        data["Masters"][to_string(a.idMaster)] = { a.idMaster, a.nameMaster,a.passportMaster,a.dateMaster };
        cout << "Element edited.";
        break;
    case(3):
        cout << "\nInput id of car: "; cin >> a.idMaster;
        data["Masters"].erase(to_string(a.idMaster));
        cout << "Element deleted.";
        break;
    default:
        return;
    }
}

void master_out(json masters) {
    cout << endl;
    cout << "[ID, Master Name, Master Passport, Master date of birth ]\n";
    for (auto master : masters) {
        cout << master << endl;
    }
}

void work_functions(json& data) {

    work_out(data["Works"]);
    cout << "\nChoose a type:\
             \n1 - Add\
             \n2 - Edit\
             \n3 - Delete\n";
    int choise; cin >> choise;
    Work a;

    switch (choise) {
    case(1):
        cout << "\nInput ID of work: "; cin >> a.idWork;
        cout << "Input car ID: "; cin >> a.idCar;
        cout << "Input date of start: "; cin.ignore(); getline(cin, a.startWork);
        cout << "Input master ID: "; cin >> a.idMaster;
        cout << "Input date of end: "; cin.ignore(); getline(cin, a.endWork);
        cout << "Input description: "; getline(cin, a.descriptionWork);
        // check if_zapis_exist
        data["Works"][to_string(a.idWork)] = { a.idWork, a.idCar, a.startWork, a.idMaster, a.endWork, a.descriptionWork };
        cout << "Element added.";
        break;
    case(2):
        cout << "\nInput ID of work: "; cin >> a.idWork;
        cout << "Input car ID: "; cin >> a.idCar;
        cout << "Input date of start: "; cin.ignore(); getline(cin, a.startWork);
        cout << "Input master ID: "; cin >> a.idMaster;
        cout << "Input date of end: "; cin.ignore(); getline(cin, a.endWork);
        cout << "Input description: "; getline(cin, a.descriptionWork);
        data["Works"][to_string(a.idWork)] = { a.idWork, a.idCar, a.startWork, a.idMaster, a.endWork, a.descriptionWork };
        cout << "Element edited.";
        break;
    case(3):
        cout << "\nInput ID of work: "; cin >> a.idWork;
        data["Works"].erase(to_string(a.idWork));
        cout << "Element deleted.";
        break;
    default:
        return;
    }
}

void work_out(json works) {
    cout << endl;
    cout << "[Work ID, Car ID, Start Date, Master ID, End Date, Description ]\n";
    for (auto work : works) {
        cout << work << endl;
    }
}






