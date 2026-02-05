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


    // DFS traversal
    //! TC: O(V+E)      [V-> vertex, E-> Edge]
    void DFS_Helper(int u, vector<bool> &visit){
        cout<<u<<" ";
        visit[u] = true;

        for(int v : l[u]){
            if(!visit[v]){
                DFS_Helper(v, visit);
            }
        }
    }

    void DFS(){
        int src = 0;
        vector<bool> visit(V, false);
        DFS_Helper(src, visit);
        cout<<endl;
/*
! if any one or more edge are not connected than we should call multiple source in diff diff components in BFS/ DFS thle uporer DF_Helper(src, visit); and cout<<endl; bad dia nicer ei code ta likhbo
*   for(int i=0; i<V; i++){
*       if(!visit[i]){
*         DFS_Helper(i, visit);
*        }
    }
*/
    }

};

//TODO://     DFS goes deep into a neighbour first, then backtracks to explore other neighbour.
int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);

    cout<<"BFS: ";
    g.BFS();
    cout<<endl<<"DFS: ";
    g.DFS();        //! TC: O(V+E)      [V-> vertex, E-> Edge]
    //user input er somoi head value pass hobe eikhan theke
    return 0;
}