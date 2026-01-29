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

    void printAdjList(){
        for(int i=0; i<V; i++){
            cout<<i<<": ";
            for(int neigh : l[i]){
                cout<<neigh<<" ";
            }
            cout<<endl;
        }
    }

    // BFS traversal
    //! TC: O(V+E)      [V-> vertex, E-> Edge]
    void BFS(){
        queue<int> Q;
        vector<bool> visit(V, false);

        Q.push(0);
        visit[0] = true;

        while(Q.size()>0){
            int u = Q.front();  // u or src (source)
            Q.pop();

            cout<<u<<" ";

            for(int v : l[u]){
                if(!visit[v]){
                    visit[v] = true;
                    Q.push(v);
                }
            }
        }
    }

};

//TODO://     BFS visits all neighboring nodes first than moving to the next level.
int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    g.addEdge(2, 4);


    g.BFS();        //! TC: O(V+E)      [V-> vertex, E-> Edge]

    return 0;
}