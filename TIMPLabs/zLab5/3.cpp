/*
9 14
1 2 12
1 3 20
1 7 2
2 8 12
3 8 3
3 5 12
3 4 17
4 5 5
4 7 11
4 6 6
5 7 16
5 6 13
6 7 4
6 8 17

numberOfEdges = 14
*/


#include <iostream>
#include <vector>



using namespace std;
const int MAX = 10000000;
int root[MAX];
const int nodes = 8, edges = 14;
pair <int, pair<int, int> > arr[MAX];

int parent(int a)                                                  //find the parent of the given node
{ 
    while(root[a] != a)
    {
        root[a] = root[root[a]];
        a = root[a];
    }
    // Возвращает корень множества, к которому принадлежит а
    return a;
}

int kruskal()
{
    int a, b;
    int cost, minCost = 0;
    for(int i = 0 ; i < edges ; ++i)
    {
        a = arr[i].second.first;
        b = arr[i].second.second;
        cost = arr[i].first;
        if(parent(a) != parent(b))      //only select edge if it does not create a cycle (ie the two nodes forming it have different root nodes)
        {
            minCost += cost;
            cout << "("<<parent(a) << " - "<<parent(b) << " | "<<cost << ") ";
            // union_find(a, b);
            root[parent(a)] = root[parent(b)];
            //check if the given two vertices are in the same “union” or not
            for(int j = 0; j < nodes; j++){
                cout << root[j] << " ";
            }
            cout << endl;
        }
    }
    return minCost;
}

int main()
{
    // int x, y;
    // int weight, cost; 
    int minCost;
    for(int i = 0;i < MAX;++i)                                       //initialize the array groups
    {
        root[i] = i;
    }
    
    arr[0] = make_pair(12, make_pair(1, 2)); 
    arr[1] = make_pair(20, make_pair(1, 3)); 
    arr[2] = make_pair(2, make_pair(1, 7)); 
    arr[3] = make_pair(12, make_pair(2, 8)); 
    arr[4] = make_pair(3, make_pair(3, 8)); 
    arr[5] = make_pair(1, make_pair(3, 5)); 
    arr[6] = make_pair(17, make_pair(3, 4)); 
    arr[7] = make_pair(5, make_pair(4, 5));
    arr[8] = make_pair(11, make_pair(4, 7));
    arr[9] = make_pair(6, make_pair(4, 6)); 
    arr[10] = make_pair(16, make_pair(5, 7));
    arr[11] = make_pair(13, make_pair(5, 6));
    arr[12] = make_pair(4, make_pair(6, 7));
    arr[13] = make_pair(17, make_pair(6, 8));
    // 2 + 4 + 3 + 5 + 6 + 1 + 12 = 33

    sort(arr, arr + edges);                                             //sort the array of edges
    minCost = kruskal();
    cout << "Minimum cost is: "<< minCost << endl;
    return 0;
}