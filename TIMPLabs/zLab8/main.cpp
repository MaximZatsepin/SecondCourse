#include <iostream>

using namespace std;

class Test{
private:
    string name_;
    int difficulty_;
    string type_;

public:
    Test(string name, int difficulty){
        name_ = name;
        difficulty_ = difficulty;
        type_ = "test";
    }

    virtual void getInfo(){   // Если сделать = 0 он станет неинициализированным -> класс абстрактным
        cout << endl << name_ << " " << type_ << ", difficulty: " << difficulty_;
    }

    void setType(string type){
        type_ = type;
    }

};

class Exam : public Test{
private:
    int duration_;

public:
    Exam(string name, int difficulty) : Test(name,difficulty){
        duration_ = 0;
        setType("exam");
    }

    void getInfo() override { // Если указать здесь final, будет смешно)
        Test::getInfo();
        cout << ", duration: " << duration_;
    }

    void setDuration(int duration){
        if(duration > 0){
            duration_ = duration;
        } else cout << "invalid duration";
    }

    int getDuration(){
        return duration_;
    }

};

class FinalExam : public Test {
private:
    bool is_passed_;

public:
    FinalExam(string name, int difficulty) : Test(name,difficulty){
        is_passed_ = false;
        setType("final exam");
    }

    void setPass(bool is_passed){
        is_passed_ = is_passed;
    }

    void getInfo() override {
        Test::getInfo();
        if(is_passed_){ cout << ", result: passed"; return; }
        cout << ", result: not passed";
    }
};

class Trial : public Exam{
private:
    int countOfTopics_;

public:
    Trial(string name, int difficulty, int duration) : Exam(name,difficulty){
        countOfTopics_ = 0;
        setDuration(duration);
        setType("trial");
    }

    void setCountOfTopics(int count){
        countOfTopics_ = count;
    }

    void getInfo() override final{
        Exam::getInfo();
        cout << ", count of questions: " << countOfTopics_;
    }

};



int main(){

    Test a = Test("Math",3);
    a.getInfo();

    Exam b = Exam("English",2);
    b.setDuration(45);
    b.getInfo();

    FinalExam c = FinalExam("Chemistry",5);
    c.setPass(1);
    c.getInfo();

    cout << "\n\n";
    cout << "You should to fill Trial test:";
    string name;
    cout << "\nChoose your subject: "; cin >> name;
    int difficulty;
    cout << "Choose difficulty for trial: "; cin >> difficulty;
    int duration = 60;
    cout << "Your trial will " << duration << " minutes long";
    int countoftopics = 0;
    cout << "\nChoose count of topics: "; cin >> countoftopics;
    while(countoftopics <= 0){
        cout << "Count of topics must be positive: "; cin >> countoftopics;
    }
    
    Trial d = Trial(name,difficulty,duration);
    d.setCountOfTopics(countoftopics);
    d.getInfo();
    
    cout << endl;

    return 0;
}