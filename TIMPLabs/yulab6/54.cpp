#include <iostream>
using namespace std;


struct Edge {
    int destination;
    int weight;
    
    Edge() : destination(0), weight(0) {}  // Конструктор по умолчанию
    Edge(int dest, int w) : destination(dest), weight(w) {}
};

const int INF = 1e9 + 7;
const int MAX_N = 100;

Edge graph[MAX_N][MAX_N];
bool used[MAX_N];

int main() {
    int n = 8;
    int mst_weight = 0;

    // Инициализация графа (пример)
    graph[1][0] = Edge(2, 1);
    graph[0][0] = Edge(1, 2);
    graph[0][1] = Edge(4, 3);
    graph[0][2] = Edge(5, 10);
    graph[7][0] = Edge(6, 10);
    graph[4][0] = Edge(5, 11);
    graph[7][1] = Edge(5, 12);
    graph[6][0] = Edge(3, 13);
    graph[5][0] = Edge(6, 15);
    graph[2][0] = Edge(3, 17);
    graph[4][1] = Edge(7, 20);
    graph[1][1] = Edge(6, 35);

    for (int i = 0; i < MAX_N; ++i) {
        used[i] = false;
    }

    for (int start_vertex = 0; start_vertex < n; ++start_vertex) {
        if (!used[start_vertex]) {
            int closest_vertex = -1;
            int min_edge_weight = INF;

            for (int i = 0; i < n; ++i) {
                if (graph[start_vertex][i].weight > 0 && !used[i] && graph[start_vertex][i].weight < min_edge_weight) {
                    closest_vertex = i;
                    min_edge_weight = graph[start_vertex][i].weight;
                }
            }

            if (closest_vertex != -1) {
                used[start_vertex] = used[closest_vertex] = true;
                mst_weight += min_edge_weight;

                cout << "Added edge: " << start_vertex << " -- " << closest_vertex << " with weight " << min_edge_weight << endl;
            }
        }
    }

    cout << "Minimum spanning tree weight: " << mst_weight << endl;

    return 0;
}
