#include <iostream>
using namespace std;

class Node {
  int *keys;
  int t;
  Node **C;
  int n;
  bool leaf;

   public:
  Node(int t1, bool leaf1);

  void insertNonFull(int k);
  void splitChild(int i, Node *y);
  void display(int gen);

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
      root->display(0);
  }

  void insert(int k);
};

Node::Node(int t1, bool leaf1) {
  t = t1;
  leaf = leaf1;

  keys = new int[4];//2 * t - 1];
  C = new Node *[5];//2 * t];

  n = 0;
}

void Node::display(int gen) {
  cout << "gen " <<gen<< ", Node keys: ";
  for(int i = 0; i < 4; i++){
      cout << keys[i] << " ";
  }
  cout << endl; 
  if(leaf == false){
    for(int i = 0 ; i < 5; i++){
      if(C[i] != NULL)C[i]->display(gen + 1);
      else cout << "NULL" << endl;
    } 
  }
  // for (i = 0; i < n; i++) {
  //   if (leaf == false)
  //     C[i]->display();
  //   cout << " " << keys[i];
  // }

  // if (leaf == false)
  //   C[i]->display();
}

void BTree::insert(int k) {
  if (root == NULL) {
    root = new Node(t, true);
    root->keys[0] = k;
    root->n = 1;
  } else {
    if (root->n == 4){//2 * t - 1) {
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

void Node::insertNonFull(int k) {
  int i = n - 1;

  if (leaf == true) {
    while (i >= 0 && keys[i] > k) {
      keys[i + 1] = keys[i];
      i--;
    }

    keys[i + 1] = k;
    n = n + 1;
  } else {
    while (i >= 0 && keys[i] > k)
      i--;

    if (C[i + 1]->n == 2 * t - 1) {
      splitChild(i + 1, C[i + 1]);

      if (keys[i + 1] < k)
        i++;
    }
    C[i + 1]->insertNonFull(k);
  }
}


void Node::splitChild(int i, Node *y) {
  Node *z = new Node(y->t, y->leaf);
  z->n = t - 1;

  for (int j = 0; j < t - 1; j++)
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


void outputArray(int arr[],int n){
  for(int i = 0; i < n; i++){
    cout << arr[i] << " ";
  }
  cout << endl;
}

int main() {
  // BTree t(1);
  // t.insert(8);
  // t.insert(9);
  // t.insert(10);
  // t.insert(11);
  // t.insert(15);
  // t.insert(16);
  // t.insert(17);
  // t.insert(18);
  // t.insert(20);
  // t.insert(23);
  int test1Arr[18] = {16, 146, 144, 23, 141, 159, 122, 8, 48, 112, 56, 88, 32, 98, 132, 75, 31, 116};
  cout << "\nTest Array: "; outputArray(test1Arr,18);

  BTree testTree(4);
  for(int i = 0; i < 18; i++){
      cout << "Num " << test1Arr[i] << " added, " << endl; 
      testTree.insert(test1Arr[i]);
      cout <<"\n---------\n";
      testTree.display();
      cout <<"\n---------\n";
  }

  cout << "\n\n\nThe B-tree is: \n";
  testTree.display();
}