#include <bits/stdc++.h>

using namespace std;

int p[100000];
int rk[100000];

//Тип для представления рёбер.
struct edge {
    int a, b, len;

    bool operator<(const edge& other) {
        return len < other.len;
    }
};

void init_dsu() {
    for (int i = 0; i < 100000; i++) {
        p[i] = i;
        rk[i] = 1;
    }
}

int get_root(int v) {
    if (p[v] == v) {
        return v;
    } else {
        return p[v] = get_root(p[v]);   //На выходе из рекурсии переподвешиваем v
    }
}

bool merge(int a, int b) {
    int ra = get_root(a), rb = get_root(b);

    if (ra == rb) {
        return false;
    } else {
        if (rk[ra] < rk[rb]) {
            p[ra] = rb;
        } else if (rk[rb] < rk[ra]) {
            p[rb] = ra;
        } else {
            p[ra] = rb;
            rk[rb]++;
        }

        return true;
    }
}



int main() {
    vector<edge> edges;
    //Ввод edges...

    sort(edges.begin(), edges.end());

    int mst_weight = 0;

    init_dsu();

    for (edge e: edges) {
        if (merge(e.a, e.b)) {      //Если a и b находятся в разных компонентах,
            mst_weight += e.len;    //Добавить ребро в минимальный остов.
        }
    }

    cout << "Minimum spanning tree weight: " << mst_weight << endl;
}