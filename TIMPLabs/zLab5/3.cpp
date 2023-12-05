/*

1. Создать пустое множество tree для хранения рёбер остовного дерева.
2. Создать структуру данных для хранения множеств вершин, сначала каждая вершина находится в своём множестве.
3. Создать список всех рёбер графа и отсортировать его по весам в порядке возрастания.
4. Для каждого ребра (u, v) из отсортированного списка:
  4.1 Если вершины u и v принадлежат разным множествам:
    4.1.1 Добавить ребро (u, v) в tree.
    4.1.2. Объединить множества, в которых находятся u и v в одно множество.
5. Вернуть множество tree, которое является остовным деревом.
6. Конец программы

*/


#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

//Алгоритм Крускала
struct Edge {
    int src, dest, weight;
};

// Сравнение рёбер по весу для сортировки
bool compareEdges(Edge e1, Edge e2) {
    return e1.weight < e2.weight;
}

// Найти множество вершины
int findSet(int v, vector<int>& parent) {
    if (v == parent[v])
        return v;
    return parent[v] = findSet(parent[v], parent);
}

// Объединить два множества
void unionSets(int a, int b, vector<int>& parent, vector<int>& rank) {
    a = findSet(a, parent);
    b = findSet(b, parent);
    if (a != b) {
        if (rank[a] < rank[b])
            swap(a, b);
        parent[b] = a;
        if (rank[a] == rank[b])
            rank[a]++;
    }
}

// Алгоритм Крускала для поиска MST
void kruskal(int V, vector<Edge>& edges) {
    int minSum = 0;
    sort(edges.begin(), edges.end(), compareEdges);
    vector<int> parent(V);
    vector<int> rank(V, 0);
    for (int i = 0; i < V; i++)
        parent[i] = i;
    vector<Edge> result;
    for (Edge e : edges) {
        if (findSet(e.src, parent) != findSet(e.dest, parent)) {
            result.push_back(e);
            unionSets(e.src, e.dest, parent, rank);
        }
    }
    // Вывод результата
    for (Edge e : result) {
        cout << "Node " << e.src << " linked with node " << e.dest << " by edge " << e.weight << endl;
        minSum += e.weight;
    }
    cout << "Minimum sum of edges: " << minSum;

}

int main() {
    int V = 14;
    setlocale(0, "");
    vector<Edge> edges = {
        {1, 2, 12}, {1, 3, 20}, {1, 7, 2},  {2, 8, 12}, 
        {3, 8, 3},  {3, 5, 1},  {3, 4, 17}, {4, 5, 5}, 
        {4, 7, 11}, {4, 6, 6},  {5, 7, 17},
        {5,6,13},   {6,7,4},    {6,8,17}
    };
    kruskal(V, edges);
    return 0;
}