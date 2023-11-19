/*
https://uxmankabir.wordpress.com/2017/05/08/cpp-program-to-perform-insertion-deletion-and-print-in-b-tree/

2 Построить дерево, содержащее n = 18 узлов и имеющее степень m = 5 Значения ключей в узлах
задавать с помощью датчика случайных чисел с диапазоном D от 0 до 160
3 Обеспечить обход деревьев «сверху вниз».
4 Выполнить поиск значения ключа по совпадению.

*/

#include <iostream>
#include <string>
#include <string.h>
 
using namespace std;
 
const int MAX = 4;
const int MIN = 2;
string path = "";
bool is_found = false;
 
struct btreeNode {
    int value[MAX + 1];    // Массив знайений (5)
    int count;    // Счетчик ключей в узле
    btreeNode *link[MAX + 1];    // Массив ссылок на дочерние узлы
};
 
btreeNode *root;


/* creating new node */
btreeNode * createNode(int value, btreeNode *child) {
    // Указатель на новый узел
    btreeNode *newNode = new btreeNode;
    newNode->value[1] = value;    // Значения
    newNode->count = 1;    // В нем уже получается есть 1 значение
    newNode->link[0] = root;    // Связываем с текущим корневым узлом
    newNode->link[1] = child;    // Связывается с переданным дочерним узлом
    return newNode;
}
 

// Функция для вставки нового значения и связанного с ним дочернего узла внутрь узла
void addvalueToNode(int value, int pos, btreeNode *node, btreeNode *child) {
    // Количество эементов в узле
    int j = node->count;
    while (j > pos) {
        // Двигаемся влево, перемещая элементы вправо на 1 позицию, освобождая место
        node->value[j + 1] = node->value[j];
        node->link[j + 1] = node->link[j];
        j--;
    }
    // Устанавливаем на освободившееся место вставляем новое значение
    node->value[j + 1] = value;
    // Устанавливаем на освободившееся место вставляем ссылку на дочерний узел
    node->link[j + 1] = child;
    // Увеличиваем кол-во значений в узле
    node->count++;
}
 
// Функция выполняет разделение узла при вставке нового значения и 
// поддерживает корректное распределение значений и связей между узлами.
void splitNode(int value, int *pvalue, int pos, btreeNode *node,btreeNode *child, btreeNode **newNode) {
    // Середина, по которой будет разделение 
    int median;
    // Переменная-счетчик для копирования значений и связей в новый узел
    int j;
    
    // Если позиция вставки больше середины 
    if (pos > MIN)
        median = MIN + 1;
    else
        median = MIN;
 
    // Создаем новый узел
    *newNode = new btreeNode;
    // Начиная с позиции 
    j = median + 1;
    // и до конца копируем копируем значения и ссылки в новый узел
    while (j <= MAX) {
        (*newNode)->value[j - median] = node->value[j];
        (*newNode)->link[j - median] = node->link[j];
        j++;
    }
    // Устанавливаем значения хранящихся в узлах значений
    node->count = median;
    (*newNode)->count = MAX - median;

    // Выбираем куда вставлять значение
    if (pos <= MIN) {
        // Вызываем вставку значения в узел
        addvalueToNode(value, pos, node, child);
    }
    else {
        // Вставляем значение в новыйузел
        addvalueToNode(value, pos - median, *newNode, child);
    }
    // Значение, которое нужно поднять в родительский узел
    // Устанавливается в значение последнего элемента в узле 
    *pvalue = node->value[node->count];
    // Связь в новом узле с индексом 0 устанавливается в последнюю связь в узле
    (*newNode)->link[0] = node->link[node->count];
    // Счетчик кол-ва элементов в узле уменьшаем
    node->count--;
}


 
// Функция для вставки значения в узел дерева
int setvalueInNode(int value, int *pvalue,btreeNode *node, btreeNode **child) {
    // Позиция, на которую нужно вставить новое значение в узле
    int pos;
    // Достигли листового узла
    if (!node) {
        // Записываем значение по указателю родителя
        *pvalue = value;
        // Дочерних нет
        *child = NULL;
        return 1;
    }
    // Если вставляемое значение меньше всех эл-тов узла
    if (value < node->value[1]) {
        // То нужно вставить в начало
        pos = 0;
    }
    else {    // Проверка на дубликат
        for (pos = node->count; (value < node->value[pos] && pos > 1); pos--);
        if (value == node->value[pos]) {
            cout<<"Duplicate\n";
            return 0;
        }
    }
    // Если вставка произошла успешно
    if (setvalueInNode(value, pvalue, node->link[pos], child)) {
        // Если узел еще не переполнен
        if (node->count < MAX) {
            // Вставляем значение в текущий узел
            addvalueToNode(*pvalue, pos, node, *child);
        }
        else {
            // Иначе разделяем текущий узел
            splitNode(*pvalue, pvalue, pos, node, *child, child);
            return 1;
        }
    }
    return 0;
}
 
// Функция вставки нового значения
void insertion(int value) {
    int flag, valueue;
    // Указатель на дочерний узел, который будет связан с новым значением
    btreeNode *child;
    // Функция пытается вставить значение в узел и, при необходимости, разделяет его
    flag = setvalueInNode(value, &valueue, root, &child);
    if (flag)
        // Создаем новый корневой узел 
        // Помещается значение valueue и связывается с дочерним узлом child
        root = createNode(valueue, child);
}

/* search val in B-Tree */
void searching(int val, int *pos,btreeNode *myNode) {

    if (!myNode) {
        return;
    }
 
    if (val < myNode->value[1]) {
        *pos = 0;
    }
    else {
        for (*pos = myNode->count;
            (val < myNode->value[*pos] && *pos > 1); (*pos)--);
        if (val == myNode->value[*pos]) {
            cout << "Given data is Found\n";
            is_found = true;
            path += " Key " + to_string(*pos);
            return;
        }
    }
    path += "Leaf " + to_string(*pos) + " -> ";
    searching(val, pos, myNode->link[*pos]);
    return;
}
 
/* B-Tree print */
void print(btreeNode *myNode,int gen) {
    int i;
    if (myNode) {
        cout << "Gen " << gen << " | ";
        for(i = 0; i < myNode->count; i++){
            cout << myNode->value[i + 1]<<' '; 
        }
        cout << endl;
        for (i = 0; i < myNode->count; i++) {
            print(myNode->link[i],gen+1);
            // cout<< myNode->val[i + 1]<<' ';
        }
        print(myNode->link[i],gen+1);
    }
}


void outputArray(int array[], int n){
    for(int i = 0; i < n; i++){
        cout << array[i] << " ";
    }
    cout << endl;
}

// #if 0
void testcase(){
  int test1Arr[18] = {16, 146, 144, 23, 141, 159, 122, 8, 48, 112, 56, 88, 7, 98, 132, 75, 31, 116};
  cout << "\n--------------\nTestCase";
  cout << "\nTest Array: "; outputArray(test1Arr,18);

  for(int i = 0; i < 18; i++){
      cout << "Num " << test1Arr[i] << " added, "; 
      insertion(test1Arr[i]);
  }
  cout <<"\nTestTree is:\n";
  print(root,0);
  cout << "--------------\nTestCase\n";
}
// #endif

int main() {
    testcase();
    // /*
    int val, opt;
    while (true) {
        cout<<"1. Insertion\n2. Searching\n3. Output\n4. Exit\n";
        cin >> opt;
        cout << endl;
        switch (opt) {
        case 1:
            cout<<"Enter your input:";
            cin >> val;
            insertion(val);
            break;
        case 2:
            cout<<"Enter the element to search:";
            cin >> val;
            path = "";
            searching(val, &opt, root);
            if(is_found) { cout << "Path is: " << path; is_found = false; }
            else cout << "Path not found";
            break;
        case 3:
            print(root,0);
            break;
        case 4:
            exit(0);
        }
        cout << endl;
    }
    // */
}

/*



*/