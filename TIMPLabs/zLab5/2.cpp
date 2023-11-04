/*

2 Построить В+ дерево, содержащее n = 18 узлов и имеющее степень m = 5 Значения ключей в узлах
задавать с помощью датчика случайных чисел с диапазоном D от 0 до 160
3 Обеспечить обход деревьев «сверху вниз».
4 Выполнить поиск значения ключа по совпадению.


1 Каждый узел содержит строго меньше 5 (порядок дерева) потомков.
2 Каждый узел содержит не менее 5/2 = 3 потомков.
3 Корень может содержать меньше 5/2 = 3 потомков.
4 У корневого узла есть хотя бы 2 потомка, если он не является листом.
5 Все листья находятся на одном уровне и содержат только данные (ключи).

maxChildren = 5
minChildren = 5/2 = 3
maxKeys = 4
minKeys = 5/2 - 1 = 2
*/

#include <iostream>
#include <time.h>

using namespace std;

struct Node {
    public:
    int *key; // key
    int t; // хуй знает че это, prob. m
    Node **child; // children
    int key_num; // key_num
    bool is_leaf; // is_leaf

    Node(int t1, bool leaf1){
        t = t1;
        is_leaf = leaf1;

        key = new int[2 * t - 2];
        child = new Node *[2 * t - 1];

        key_num = 0;
    }

    void insertNonFull(int data){ // k = data
        int i = key_num - 1;  // index = keyCount - 1

        if (is_leaf == true) {
          while (i >= 0 && key[i] > data) { // Двигаем всё в конец
            key[i + 1] = key[i]; // "всё" - то, что больше data
            i--;
          }

          key[i + 1] = data; // вставляем дату
          key_num = key_num + 1; // keyCount += 1
        } 
        else {                          // Если не лист
          while (i >= 0 && key[i] > data) // двигаем индекс в начало
            i--;

          if (child[i + 1]->key_num == 2 * t - 1) { // если 
            splitChild(i + 1, child[i + 1]); // поделить дебилов
                  // Передается индекс (0 и children[0] указатель)
            if (key[i + 1] < data) 
              i++;
          }

          child[i + 1]->insertNonFull(data);
        }

    }

    void splitChild(int i, Node *y){
        Node *z = new Node(y->t, y->is_leaf);
        z->key_num = t - 1; // 2
        for (int j = 0; j < t - 1; j++) z->key[j] = y->key[j + t];

        if (y->is_leaf == false) {
          for (int j = 0; j < t; j++)
            z->child[j] = y->child[j + t];
        }

        y->key_num = t - 1;
        for (int j = key_num; j >= i + 1; j--) child[j + 1] = child[j];
        child[i + 1] = z;
        for (int j = key_num - 1; j >= i; j--) key[j + 1] = key[j];
        key[i] = y->key[t - 1];
        key_num = key_num + 1;
    }
    
    void display(int gen){
        cout << "gen" << gen << " |   ";
        for(int i = 0; i < key_num; i++) cout << " " << key[i];
        //   int i;
        cout << endl;
        for (int i = 0; i <= key_num; i++) { // n = 5
          if (is_leaf == false) child[i]->display(gen+1);
        // if (is_leaf == false) child[key_num]->display(gen+1);

        //   if (is_leaf == false)
        // C[i]->display();
        }
    }

};

struct BTree {
    Node *root;
    int t;

    BTree(int tt) {
        root = NULL;
        t = tt;
    }

    void display() {
      cout << "\n###########\n";
        if (root != NULL)
          root->display(0);
      cout << "\n###########\n";
    }

    void insert(int data){ // k = data
        if (root == NULL) { // da
          root = new Node(t, true);
          root->key[0] = data;
          root->key_num = 1; // n = countsOfData
        } else {
          if (root->key_num == 2 * t - 2) {
            Node *s = new Node(t, false);

            s->child[0] = root;

            s->splitChild(0, root);

            int i = 0;
            if (s->key[0] < data)
              i++;
            s->child[i]->insertNonFull(data);

            root = s;
          } else
            root->insertNonFull(data);
        }
        cout << "\nTree now: "; display();
    }
};

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

void testcase(){
  cout << "\n--------------\nTestCase";
  int test1Arr[18] = {16, 146, 144, 23, 141, 159, 122, 8, 48, 112, 56, 88, 31, 98, 132, 75, 31, 116};
  cout << "\nTest Array: "; outputArray(test1Arr,18);

  BTree testTree(3);
  for(int i = 0; i < 18; i++){
      cout << "Num " << test1Arr[i] << " added, "; 
      testTree.insert(test1Arr[i]);
  }
  cout <<"\nTestBPlusTree is:\n";
  testTree.display();
  cout << "--------------\nTestCase";
}

int main() {
  srand(time(nullptr));
  testcase();
  // int countOfNums = 18;
  // int arr[18];
  // fillArray(arr,countOfNums);
  // cout << "\nArray of nums is: "; outputArray(arr,countOfNums);

  // BTree tree(2); 
  // for(int i = 0; i < countOfNums; i++){
  //     cout << "Num " << i+1 << " added" << endl; 
  //     tree.insert(arr[i]);
  // }

  // cout << "BPlusTree: \n";
  // tree.display();
}

// В ноде выстреливает количество ключей больше чем 4
// Переписать с нуля всё к у
