/*
Вариант 8
2 Построить В+ дерево, содержащее n = 14 узлов и имеющее степень m = 5 Значения ключей в узлах
задавать с помощью датчика случайных чисел с диапазоном D от 0 до 120

*/


#include <iostream>
#include <random>

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

void bottomUpTraversal(BPlusTreeNode* root, int level) {
    if (!root) {
        return;
    }

    for (int i = 0; i < root->numKeys; i++) {
        std::cout << "Уровень " << level << ": " << root->keys[i] << std::endl;
    }

    if (!root->isLeaf) {
        for (int i = 0; i <= root->numKeys; i++) {
            bottomUpTraversal(root->children[i], level + 1);
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
    bottomUpTraversal(root, 0);

    return 0;
}
