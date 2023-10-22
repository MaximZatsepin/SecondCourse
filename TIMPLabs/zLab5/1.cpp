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

using namespace std;
void fillArray(int array[], int n);
void outputArray(int array[], int n);
Node* newNode(int data);


struct Node{
    int data;
    struct Node *left;
    struct Node *right;
};

int main(){
    srand(time(nullptr));
    
    int n = 18;
    int arr[n];
    
    fillArray(arr,n);

    outputArray(arr,n);
}

void fillArray(int array[], int n){
    for(int i = 0; i < n; i++){
        array[i] = rand() % 120;
    }
}

void outputArray(int array[], int n){
    for(int i = 0; i < n; i++){
        cout << array[i] << " ";
    }
    cout << endl;
}

Node* createNewNode(int data){ 
    Node *newNode = new Node;
    *newNode = {data,NULL,NULL};
    return newNode;
}
// 34 15 12
void createTree(int arr[],int n){
    Node* root = createNewNode(arr[0]);
    
    for(int i = 1; i < n; i++){
        if(arr[i] < root->data){
            root->left = createNewNode(arr[i]);
        }
        else{
            root->right = createNewNode(arr[i]);
        }
    }

}



