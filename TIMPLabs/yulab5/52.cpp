/*

2 Построить В+ дерево, содержащее n = 14 узлов и имеющее степень m = 5 Значения ключей в узлах
задавать с помощью датчика случайных чисел с диапазоном D от 0 до 120
3 Обеспечить обход деревьев «снизу вверх».
4 Выполнить поиск значения ключа по близости снизу.

*/

#include <iostream>
#include <string>
#include <iomanip>
#include <string.h>
 
using namespace std;

int random();
 
 // Максимум 4 ребенка, минимум 2
const int MAX = 4;
const int MIN = 2;
int count_node = 0;
 
struct btreeNode {
    int value[MAX + 1];    // Массив знайений (5)
    int count;    // Счетчик ключей в узле
    btreeNode *link[MAX + 1];    // Массив ссылок на дочерние узлы
};
 
btreeNode *root;
string path;
 
/* creating new node */
btreeNode * createNode(int value, btreeNode *child) {
    btreeNode *newNode = new btreeNode;
    newNode->value[1] = value;
    newNode->count = 1;
    newNode->link[0] = root;
    newNode->link[1] = child;
    return newNode;
}
 

void outputArray(int arr[],int n){
  for(int i = 0; i < n; i++){
    cout << arr[i] << " ";
  }
  cout << endl;
}



/* Places the valueue in appropriate position */
void addvalueToNode(int value, int pos, btreeNode *node, btreeNode *child) {
    int j = node->count;
    while (j > pos) {
        node->value[j + 1] = node->value[j];
        node->link[j + 1] = node->link[j];
        j--;
    }
    node->value[j + 1] = value;
    node->link[j + 1] = child;
    node->count++;
}
 
/* split the node */
void splitNode(int value, int *pvalue, int pos, btreeNode *node,btreeNode *child, btreeNode **newNode) {
    int median, j;
 
    if (pos > MIN)
        median = MIN + 1;
    else
        median = MIN;
 
    *newNode = new btreeNode;
    j = median + 1;
    while (j <= MAX) {
        (*newNode)->value[j - median] = node->value[j];
        (*newNode)->link[j - median] = node->link[j];
        j++;
    }
    node->count = median;
    (*newNode)->count = MAX - median;
 
    if (pos <= MIN) {
        addvalueToNode(value, pos, node, child);
    }
    else {
        addvalueToNode(value, pos - median, *newNode, child);
    }
    *pvalue = node->value[node->count];
    (*newNode)->link[0] = node->link[node->count];
    node->count--;
}
 
/* sets the valueue value in the node */
int setvalueueInNode(int value, int *pvalue,btreeNode *node, btreeNode **child) {
 
    int pos;
    if (!node) {
        *pvalue = value;
        *child = NULL;
        return 1;
    }
 
    if (value < node->value[1]) {
        pos = 0;
    }
    else {    // Проверка на дубликат
        for (pos = node->count; (value < node->value[pos] && pos > 1); pos--);
        // if (value == node->value[pos]) {
            // cout<<"Duplicate\n";
            // return 0;
        // }
    }
    if (setvalueueInNode(value, pvalue, node->link[pos], child)) {
        if (node->count < MAX) {
            addvalueToNode(*pvalue, pos, node, *child);
        }
        else {
            splitNode(*pvalue, pvalue, pos, node, *child, child);
            return 1;
        }
    }
    return 0;
}
 
/* insert value in B-Tree */
void insertion(int value) {
    int flag, valueue;
    btreeNode *child;
 
    flag = setvalueueInNode(value, &valueue, root, &child);
    if (flag)
        root = createNode(valueue, child);
}

/* search value in B-Tree */
void searching(int value, int *pos,btreeNode *myNode) {
    if (value < myNode->value[1]) {
        *pos = 0;
    }
    else {
        for (*pos = myNode->count;
            (value < myNode->value[*pos] && *pos > 1); (*pos)--);
        if (value == myNode->value[*pos]) {
            cout << "Given data is Found\n";
            path += " Key " + to_string(*pos);
            return;
        }
    }
    path += "Leaf " + to_string(*pos) + " -> ";
    searching(value, pos, myNode->link[*pos]);
    return;
}
 
/* B-Tree print */
void print(btreeNode *myNode,int gen) {
    int i;

    if (myNode) {
        // Выводим ген
        cout  << setw(2) << count_node << " Gen " << gen << " | "; 
        count_node++;
        // Просматриваем все значения этого гена
        for(i = 0; i < myNode->count; i++){
            // Вывпоим все его значения
            cout << myNode->value[i + 1]<<' '; 
        }
        cout << endl;
        // Рекурсивно запускаем вывод наследников кроме последнего
        for (i = 0; i < myNode->count; i++) {
            print(myNode->link[i],gen+1);
        }
        // Запускаем вывод последнего наследника
        print(myNode->link[i],gen+1);
    }
}
 

void create_tree(){
  int rand;
  for(int i = 0; i < 40; i++){
    // Создаем рандомное число от 0 до 120
    rand = random();
    // Добавляем его в дерево 
    insertion(rand);
  }
  cout <<"\nTree is:\n\n";
  // Вывод сформированного дерева
  print(root,0);
}


int main() {
    // Создание дерева из 14 эл-тов диапазоном значений от 0 до 120
    create_tree();
    //
    int value; 
    int vibor;
    while (true) {
        cout<<"\n1. Searching\n2. Output\n3. Exit\n";
        cin >> vibor;
        cout << endl;
        switch (vibor) {
        case 1:
            cout<<"Enter the element to search:";
            cin >> value;
            path = "";
            searching(value, &vibor, root);
            cout << "Path is: " << path;
            break;
        case 2:
            print(root,0);
            break;
        case 3:
            exit(0);
        }
        cout << endl;
    }
}



// Генератор псевдослучайных чисел
int random()
{
    int rnd =  rand() % (120 - 0 + 1);
    if (rnd < 0) {rnd *= -1;}
    return rnd;
}