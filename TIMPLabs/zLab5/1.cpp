/*
1 Построить двоичное дерево, содержащее n = 18 узлов. Значения ключей в узлах задавать с
помощью датчика случайных чисел с диапазоном D от 0 до 160
3 Обеспечить обход деревьев «сверху вниз».
4 Выполнить поиск значения ключа по совпадению.
*/
#include <iostream>
#include <time.h>
// #include <string>
using namespace std;

struct Node{ // Node
    int data;
    struct Node *left;
    struct Node *right;
};


void fillArray(int array[], int n);
void outputArray(int array[], int n);
Node* addNode2Tree(Node *node, int data);
Node* createBT(int arr[], int n);
void outputBT(Node *node, int gen);
bool keyFind(Node* node, int key, string& path);



int main(){
    srand(time(nullptr));
    
    int n = 10 ;
    int arr[n];
    
    fillArray(arr,n);

    Node *root = createBT(arr,n);

    outputBT(root,0);

    outputArray(arr,n);

    string path;
    bool found = keyFind(root, arr[5], path);
    
    if (found) {
        cout << "Key " << arr[5] << " found! Path: " << path << endl;
    } else {
        cout << "Key " << arr[5] << " not found." << endl;
    }

    string path1;
    bool found1 = keyFind(root, arr[4]+1, path1);
    
    if (found1) {
        cout << "Key " << arr[4]+1 << " found! Path: " << path1 << endl;
    } else {
        cout << "Key " << arr[4]+1 << " not found." << endl;
    }

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

Node* addNode2Tree(Node *node, int data){

    if(!node){
        node = new Node;
        node->data = data;
        node->left = NULL;
        node->right = NULL;
        return node;
    }
    if(data < node->data){
        node->left = addNode2Tree(node->left,data);
    } else {
        node->right = addNode2Tree(node->right,data);
    }
    return node;
}

// обход дерева сверху вниз
Node* createBT(int arr[], int n){
    Node *root = nullptr;

    for(int i = 0 ; i < n; i++){
        root = addNode2Tree(root,arr[i]);
    }
    return root;
}

void outputBT(Node *node, int gen){
    if(!node){
        return;
    }
    cout << "gen: " << gen++ << ", data: " << node->data << endl;

    outputBT(node->left,gen);
    outputBT(node->right,gen);

    return;
}


bool keyFind(Node* node, int key, string& path) {
    if (!node) {
        return false;
    }
    
    if (node->data == key) {
        // path = to_string(node->data);
        path = "Key";
        return true;
    }
    
    string leftPath, rightPath;
    bool foundInLeft = keyFind(node->left, key, leftPath);
    bool foundInRight = keyFind(node->right, key, rightPath);
    
    if (foundInLeft) {
        path = to_string(node->data) + " -> " + leftPath;
        path = "Left -> " + leftPath;
        return true;
    }
    
    if (foundInRight) {
        path = to_string(node->data) + " -> " + rightPath;
        path = "Right -> " + rightPath;
        return true;
    }
    
    return false;
}


/*

                                90
                            49      96
                         45   -    81     105
                        3  -     -   -   -   119
                      -   5                 -   -

*/