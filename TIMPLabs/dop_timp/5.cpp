#include <iostream>
using namespace std;

class Node {
  int *keys; // keys
  int t; // хуй знает че это, prob. m
  Node **C; // children
  int n; // key_num
  bool leaf; // is_leaf

    public:
  Node(int t1, bool leaf1);

  void insertNonFull(int k);
  void splitChild(int i, Node *y);
  void display();

  friend class BTree;
};

class BTree {
  Node *root;
  int t;

   public:
  BTree(int _t) {
    root = NULL;
    t = _t;
  }

  void display() {
    if (root != NULL)
      root->display();
  }

  void insert(int k);
};

Node::Node(int t1, bool leaf1) {
  t = t1;
  leaf = leaf1;

  keys = new int[2 * t - 1];
  C = new Node *[2 * t];

  n = 0;
}

void Node::display() {
//   int i;
  for (int i = 0; i < n; i++) { // n = 5
    if (leaf == false) // i = 0,1,2,3,4
      C[i]->display();
    cout << " " << keys[i];
  }
  if (leaf == false) C[n]->display();

//   if (leaf == false)
    // C[i]->display();

}

void BTree::insert(int k) { // k = data
  if (root == NULL) { // da
    root = new Node(t, true);
    root->keys[0] = k;
    root->n = 1; // n = countsOfData
  } else {
    if (root->n == 2 * t - 1) {
      Node *s = new Node(t, false);

      s->C[0] = root;

      s->splitChild(0, root);

      int i = 0;
      if (s->keys[0] < k)
        i++;
      s->C[i]->insertNonFull(k);

      root = s;
    } else
      root->insertNonFull(k);
  }
}

void Node::insertNonFull(int k) { // k = data
  int i = n - 1;  // index = keysCount - 1

  if (leaf == true) {
    while (i >= 0 && keys[i] > k) { // Двигаем всё в конец
      keys[i + 1] = keys[i]; // "всё" - то, что больше data
      i--;
    }

    keys[i + 1] = k; // вставляем дату
    n = n + 1; // keysCount += 1
  } else {                          // Если не лист
    while (i >= 0 && keys[i] > k) // двигаем индекс в начало
      i--;

    if (C[i + 1]->n == 2 * t - 1) { // если 
      splitChild(i + 1, C[i + 1]); // поделить дебилов
            // Передается индекс (0 и children[0] указатель)
      if (keys[i + 1] < k) 
        i++;
    }
    C[i + 1]->insertNonFull(k);
  }
}


void Node::splitChild(int i, Node *y) {
  Node *z = new Node(y->t, y->leaf);
  z->n = t - 1;

  for (int j = 0; j < t - 1; j++) // ------------
    z->keys[j] = y->keys[j + t];

  if (y->leaf == false) {
    for (int j = 0; j < t; j++)
      z->C[j] = y->C[j + t];
  }

  y->n = t - 1;
  for (int j = n; j >= i + 1; j--)
    C[j + 1] = C[j];

  C[i + 1] = z;

  for (int j = n - 1; j >= i; j--)
    keys[j + 1] = keys[j];

  keys[i] = y->keys[t - 1];
  n = n + 1;
}

int main() {
  BTree t(1); // t = 1, root = NULL
  t.insert(8);
  t.insert(9);
  t.insert(10);
  t.insert(11);
  t.insert(15);
  t.insert(16);
  t.insert(17);
  t.insert(18);
  t.insert(20);
  t.insert(23);

  cout << "The B-tree is: \n";
  t.display();
}