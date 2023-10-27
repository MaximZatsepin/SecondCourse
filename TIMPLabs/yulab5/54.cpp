/*
Вариант 8
4 Выполнить поиск значения ключа по близости снизу.

*/

#include <iostream>
#include <random>

// Структура для представления узла В+ дерева
struct BPlusTreeNode {
    int keys[4];
    BPlusTreeNode* children[5];
    int numKeys;
    bool isLeaf;

    BPlusTreeNode() {
        numKeys = 0;
        isLeaf = true;
        for (int i = 0; i < 5; ++i) {
            children[i] = nullptr;
        }
    }
};

// Вставка ключа в В+ дерево
BPlusTreeNode* insert(BPlusTreeNode* root, int key) {
    if (!root) {
        BPlusTreeNode* newNode = new BPlusTreeNode();
        newNode->keys[0] = key;
        newNode->numKeys = 1;
        return newNode;
    }

    if (root->isLeaf) {
        int i = 0;
        while (i < root->numKeys && key > root->keys[i]) {
            i++;
        }

        for (int j = root->numKeys; j > i; j--) {
            root->keys[j] = root->keys[j - 1];
        }

        root->keys[i] = key;
        root->numKeys++;

        if (root->numKeys > 4) {
            // Разделение листового узла
            BPlusTreeNode* newLeaf = new BPlusTreeNode();
            int splitIndex = root->numKeys / 2;

            for (int j = splitIndex; j < root->numKeys; j++) {
                newLeaf->keys[j - splitIndex] = root->keys[j];
                newLeaf->numKeys++;
            }

            root->numKeys = splitIndex;

            newLeaf->children[4] = root->children[4];
            root->children[4] = newLeaf;
            return newLeaf;
        }

        return root;
    } else {
        int i = 0;
        while (i < root->numKeys && key > root->keys[i]) {
            i++;
        }

        BPlusTreeNode* newChild = insert(root->children[i], key);

        if (newChild != root->children[i]) {
            // Произошло разделение дочернего узла
            for (int j = root->numKeys; j > i; j--) {
                root->keys[j] = root->keys[j - 1];
                root->children[j + 1] = root->children[j];
            }

            root->keys[i] = newChild->keys[0];
            root->children[i + 1] = newChild;
            root->numKeys++;

            if (root->numKeys > 4) {
                // Разделение внутреннего узла
                BPlusTreeNode* newInner = new BPlusTreeNode();
                int splitIndex = root->numKeys / 2;

                for (int j = splitIndex + 1; j < root->numKeys; j++) {
                    newInner->keys[j - splitIndex - 1] = root->keys[j];
                    newInner->children[j - splitIndex] = root->children[j];
                    newInner->numKeys++;
                }

                root->numKeys = splitIndex;

                for (int j = 0; j < newInner->numKeys; j++) {
                    newInner->children[j]->isLeaf = false;
                }

                newInner->children[4] = root->children[4];
                root->children[4] = newInner;
                return newInner;
            }
        }

        return root;
    }
}

// Вывод В+ дерева
void printBPlusTree(BPlusTreeNode* root, int level) {
    if (root) {
        if (root->isLeaf) {
            std::cout << "Leaf Level " << level << ": ";
        } else {
            std::cout << "Level " << level << ": ";
        }

        for (int i = 0; i < root->numKeys; i++) {
            std::cout << root->keys[i] << " ";
        }

        std::cout << std::endl;

        if (!root->isLeaf) {
            for (int i = 0; i <= root->numKeys; i++) {
                printBPlusTree(root->children[i], level + 1);
            }
        }
    }
}

int main() {
    int n = 14;
    BPlusTreeNode* root = nullptr;

    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<int> dist(0, 120);

    for (int i = 0; i < n; i++) {
        int key = dist(gen);
        root = insert(root, key);
    }

    std::cout << "B+ Tree:" << std::endl;
    printBPlusTree(root, 0);

    return 0;
}
