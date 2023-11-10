#include <iostream>
#include <cstring>
using namespace std;

int main() {

    int num_edges = 0;  // Количество рёбер - 14
    // memset(selected, false, sizeof(selected));
    int graph[8][8] = {{0, 12, 20, 0,  0,  0,  2,  0 },
                       {12, 0,  0, 0,  0,  0,  0, 12 },
                       {20, 0,  0, 17, 1,  0,  0,  3 },
                       {0,  0, 17, 0,  5,  6, 11,  0 },
                       {0,  0,  1, 5,  0, 13, 16,  0 },
                       {0,  0,  0, 6, 13,  0,  4, 17 },
                       {2,  0,  0, 11,16,  4,  0,  0 },
                       {0, 12,  3, 0,  0, 17,  0,  0 }};

    bool selected[8] = {0,0,0,0,0,0,0,0};
    selected[0] = true;

    int from, to;  // Номера вершин
    cout << endl;
    
    int totalweight = 0;
    while (num_edges < 14) {

        int min_weight = 1000000;
        from = 0;
        to = 0;

        for (int i = 0; i < 8; i++) {
            if (selected[i]) {
                for (int j = 0; j < 8; j++) {
                    if (!selected[j] && graph[i][j]) {  // Не в выбранных и есть ребро
                        if (min_weight > graph[i][j]) {
                            min_weight = graph[i][j];
                            from = i;
                            to = j;
                        }
                    }
                }
            }
        }
        
        cout << from+1 << " - " << to+1 << " | " << graph[from][to];
        totalweight += graph[from][to];
        cout << endl;
        selected[to] = true;
        num_edges++;
    }
    cout << "\nTotal weight is " << totalweight << endl;

    return 0;
}
