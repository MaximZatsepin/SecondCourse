/*
1 Построить двоичное дерево, содержащее n = 18 узлов. Значения ключей в узлах задавать с
помощью датчика случайных чисел с диапазоном D от 0 до 160
2 Построить В+ дерево, содержащее n = 18 узлов и имеющее степень m = 5 Значения ключей в узлах
задавать с помощью датчика случайных чисел с диапазоном D от 0 до 160
3 Обеспечить обход деревьев «сверху вниз».
4 Выполнить поиск значения ключа по совпадению.
*/
#include <iostream>
#include <time.h>
// #include <string>
using namespace std;

struct Branch{ // Node
    int data;
    struct Branch *left;
    struct Branch *right;
};


void fillArray(int array[], int n);
void outputArray(int array[], int n);
Branch* addBranch2Tree(Branch *node, int data);
Branch* createBT(int arr[], int n);
void outputBT(Branch *node, int gen);
bool keyFind(Branch *node, int key);



int main(){
    srand(time(nullptr));
    
    int n = 10 ;
    int arr[n];
    
    fillArray(arr,n);


    Branch *root = createBT(arr,n);


    outputBT(root,0);

    outputArray(arr,n);

    // cout << "\nLooking for key " << arr[5];
    // bool found = keyFind(root, arr[5]);
    
    // if (found) { cout << "\nKey found!"; }
    // else cout << "\nKey not found.";

    // cout << "\n\nLooking for key " << arr[4] + 1;
    // found = keyFind(root, arr[4] + 1);
    
    // if (found) { cout << "\nKey found!"; }
    // else cout << "\nKey not found.";

    cout << endl;
}

void fillArray(int array[], int n){
    for(int i = 0; i < n; i++){
        array[i] = rand() % 160;
    }
}

void outputArray(int array[], int n){
    for(int i = 0; i < n; i++){
        cout << array[i] << " ";
    }
    cout << endl;
}

Branch* addBranch2Tree(Branch *node, int data){

    if(!node){
        node = new Branch;
        node->data = data;
        node->left = NULL;
        node->right = NULL;
        return node;
    }
    if(data < node->data){
        node->left = addBranch2Tree(node->left,data);
    } else {
        node->right = addBranch2Tree(node->right,data);
    }
    return node;
}

// обход дерева сверху вниз
Branch* createBT(int arr[], int n){
    Branch *root;

    for(int i = 0 ; i < n; i++){
        root = addBranch2Tree(root,arr[i]);
    }
    return root;
}

void outputBT(Branch *node, int gen){
    if(!node){
        return;
    }
    cout << "gen: " << gen++ << ", data: " << node->data << endl;

    outputBT(node->left,gen);
    outputBT(node->right,gen);

    return;
}

// Поиск значения по совпадению
// Если ноды нет, ключ не найден
// Если дата=ключ, ключ найден
// Вызывать рекурсивно
bool keyFind(Branch *node, int key) {
    if (!node) {
        return false; // Ключ не найден
    }

    if (node->data == key) {
        return true; // Ключ найден
    }

    bool foundInLeft = keyFind(node->left, key);
    bool foundInRight = keyFind(node->right, key);

    return foundInLeft || foundInRight; // Если ключ найден в левом или правом поддереве, вернуть true
}


/*

                                90
                            49      96
                         45   -    81     105
                        3  -     -   -   -   119
                      -   5                 -   -

*/