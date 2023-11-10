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
 
struct btreeNode {
    int val[MAX + 1], count;
    btreeNode *link[MAX + 1];
};
 
btreeNode *root;
string path;
 
/* creating new node */
btreeNode * createNode(int val, btreeNode *child) {
    btreeNode *newNode = new btreeNode;
    newNode->val[1] = val;
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



/* Places the value in appropriate position */
void addValToNode(int val, int pos, btreeNode *node, btreeNode *child) {
    int j = node->count;
    while (j > pos) {
        node->val[j + 1] = node->val[j];
        node->link[j + 1] = node->link[j];
        j--;
    }
    node->val[j + 1] = val;
    node->link[j + 1] = child;
    node->count++;
}
 
/* split the node */
void splitNode(int val, int *pval, int pos, btreeNode *node,btreeNode *child, btreeNode **newNode) {
    int median, j;
 
    if (pos > MIN)
        median = MIN + 1;
    else
        median = MIN;
 
    *newNode = new btreeNode;
    j = median + 1;
    while (j <= MAX) {
        (*newNode)->val[j - median] = node->val[j];
        (*newNode)->link[j - median] = node->link[j];
        j++;
    }
    node->count = median;
    (*newNode)->count = MAX - median;
 
    if (pos <= MIN) {
        addValToNode(val, pos, node, child);
    }
    else {
        addValToNode(val, pos - median, *newNode, child);
    }
    *pval = node->val[node->count];
    (*newNode)->link[0] = node->link[node->count];
    node->count--;
}
 
/* sets the value val in the node */
int setValueInNode(int val, int *pval,btreeNode *node, btreeNode **child) {
 
    int pos;
    if (!node) {
        *pval = val;
        *child = NULL;
        return 1;
    }
 
    if (val < node->val[1]) {
        pos = 0;
    }
    else {    // Проверка на дубликат
        for (pos = node->count; (val < node->val[pos] && pos > 1); pos--);
        // if (val == node->val[pos]) {
            // cout<<"Duplicate\n";
            // return 0;
        // }
    }
    if (setValueInNode(val, pval, node->link[pos], child)) {
        if (node->count < MAX) {
            addValToNode(*pval, pos, node, *child);
        }
        else {
            splitNode(*pval, pval, pos, node, *child, child);
            return 1;
        }
    }
    return 0;
}
 
/* insert val in B-Tree */
void insertion(int val) {
    int flag, value;
    btreeNode *child;
 
    flag = setValueInNode(val, &value, root, &child);
    if (flag)
        root = createNode(value, child);
}

/* search val in B-Tree */
void searching(int val, int *pos,btreeNode *myNode) {

    if (!myNode) {
        return;
    }
 
    if (val < myNode->val[1]) {
        *pos = 0;
    }
    else {
        for (*pos = myNode->count;
            (val < myNode->val[*pos] && *pos > 1); (*pos)--);
        if (val == myNode->val[*pos]) {
            cout << "Given data is Found\n";
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
            cout << myNode->val[i + 1]<<' '; 
        }
        cout << endl;
        for (i = 0; i < myNode->count; i++) {
            print(myNode->link[i],gen+1);
            // cout<< myNode->val[i + 1]<<' ';
        }
        print(myNode->link[i],gen+1);
    }
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
  cout << "--------------\nTestCase";
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
            cout << "Path is: " << path;
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