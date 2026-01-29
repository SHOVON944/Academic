#include<iostream>
#include<vector>
#include<list>
#include<queue>
using namespace std;

class Graph{
    int V;
    list<int> *l;    //* int *arr;

public:
    Graph(int V){
        this->V = V;
        l = new list<int> [V];  //* arr = new int[V]
    }

    void addEdge(int u, int v){
        l[u].push_back(v);
        l[v].push_back(u);
    }

    bool isCycleUndirDFS(int src, int par, vector<bool> &visit){ //! TC: O(V+E)
        visit[src] = true;
        list<int> neighbours = l[src];

        for(int v : neighbours){
            if(!visit[v]){
                if(isCycleUndirDFS(v, src, visit)){
                    return true;
                }
            } else if(v != par){
                return true;
            }
        }

        return false;
    }

    bool isCycle(){
        vector<bool> visit(V, false);
        for(int i=0; i<V; i++){
            if(!visit[i]){
                if(isCycleUndirDFS(i, -1, visit)){
                    return true;
                }
            }
        }
        return false;
    }
};


int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    // g.addEdge(0, 2);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(3, 4);

    cout<<g.isCycle()<<endl;

    return 0;
}