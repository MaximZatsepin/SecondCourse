/*
Вариант 8
1 Построить двоичное дерево, содержащее n = 14 узлов. Значения ключей в узлах задавать с
помощью датчика случайных чисел с диапазоном D от 0 до 120
*/

#include <iostream>
using namespace std;

int random();


// Структура узла, у него есть левое и правое поддеревья, а так же я запоминаю его уровень 
struct TreeNode {
    // узел
    int key;
    // Левое поддерево
    TreeNode* left;
    // Правое поддерево
    TreeNode* right;
    // Номер узла
    int level;
    // Конструктор структуры, указываем стартовые значения 
    TreeNode(int val, int lvl) : key(val), left(nullptr), right(nullptr), level(lvl) {}
};

// Рекурсивная вставка уздла в дереве
TreeNode* insert(TreeNode* root, int key, int level) {
    // Достигли листового узла
    if (root == nullptr) {
        return new TreeNode(key, level);
    }
    // Значение key меньше узла
    if (key < root->key) {
        // Переходимв левую ветку этого узла и вызываем эту же функцию для него
        root->left = insert(root->left, key, level + 1);
    // Значение key больше узла
    } else if (key > root->key) {
        // Переводим в правую ветку 
        root->right = insert(root->right, key, level + 1);
    }
}


// Объод дерева "снизу - вверх"
void bottomUpTraversal(TreeNode* node) {
    // Если узел пуст
    if (node == nullptr) {
        return;
    }
    bottomUpTraversal(node->left);
    bottomUpTraversal(node->right);
    cout << "Level " << node->level << ": " << node->key << endl;
}

int main() {
    int n = 14;    // Кол-во узлов

    TreeNode* root = nullptr;

    cout << "\nNumbers: " << endl;
    for (int i = 0; i < n; ++i) {
        int key = random();
        cout << key << " ";
        root = insert(root, key, 1); // Начинаем с уровня 1
    }

    cout << "\n\nResult: " << endl;
    bottomUpTraversal(root);
}


// Генератор псевдослучайных чисел
int random()
{
    int rnd =  rand() % (120 - 0 + 1);
    if (rnd < 0) {rnd *= -1;}
    return rnd;
}