/*

2 Построить В+ дерево, содержащее n = 18 узлов и имеющее степень m = 5 Значения ключей в узлах
задавать с помощью датчика случайных чисел с диапазоном D от 0 до 160
3 Обеспечить обход деревьев «сверху вниз».
4 Выполнить поиск значения ключа по совпадению.


Правила построения:
Каждый узел содержит строго меньше 5 потомков
Каждый узел содержит не менее 3х потомков
Корень может содержать меньше 3х потомков
У корня есть хотябы 2 потомка, если он не лист
Все листья находятся на одном уровне и содержат только ключи

maxChildren = 5
minChildren = 5/2 = 3
maxKeys = 4
minKeys = 5/2 - 1 = 2
*/

#include <iostream>

using namespace std;

int m = 5; // Степень

struct Node{
  // int m;
  bool is_leaf = false;  // Проверка на лист
  int key_num = 4;  // Количество ключей
  int key[4]; // Ключи
  // Node *parent = NULL;  // Родитель
  Node *child[5]; // Дети
  // Node *left = NULL; // Левый брат
  // Node *right = NULL;  // Правый брат

  // void insertNonFull(int k);
  void insertNonFull(int data);

  void splitChild(int i, Node *oldNode){
    Node *tempNode = new Node;
    tempNode->is_leaf = oldNode->is_leaf;

    // --------------
  }

  void display(){
    for(int i = 0; i < key_num; i++){
      if (is_leaf == false) child[i]->display();
      cout << " " << key[i];
    }
    if (is_leaf == false) child[key_num]->display();
  }
};

struct BTree{
  int m = 5; // Степень дерева
  Node *root = NULL; // Корень дерева
};

void Node::display(){

}