#include <iostream>
using namespace std;


class Node{
public:
    int vertex;
    int weight;
    Node* next;

    Node(int v,int w){
        vertex = v;
        weight = w;
        next = NULL;
    }
};


class List{
    Node* head;

public:
    List(){
        head = NULL;
    }

    void push_back(int v,int w){
        Node* newNode = new Node(v,w);

        if(head == NULL){
            head = newNode;
            return;
        }

        Node* temp = head;
        while(temp->next != NULL){
            temp = temp->next;
        }

        temp->next = newNode;
    }

    Node* getHead(){
        return head;
    }
};

// -------- Graph
class Graph{
    int V;
    List* adj;

public:
    Graph(int v){
        V = v;
        adj = new List[V];
    }

    void addEdge(int u,int v,int w){
        adj[u].push_back(v,w);   // directed graph
    }

    // -------- Bellman-Ford
    void BellManFord(int src){
        int* dist = new int[V];

        for(int i=0;i<V;i++){
            dist[i] = 999999;
        }

        dist[src] = 0;

        // V-1 times relax
        for(int i=0;i<V-1;i++){
            for(int u=0;u<V;u++){
                Node* temp = adj[u].getHead();

                while(temp != NULL){
                    int v = temp->vertex;
                    int w = temp->weight;

                    if(dist[u] != 999999 && dist[v] > dist[u] + w){
                        dist[v] = dist[u] + w;
                    }

                    temp = temp->next;
                }
            }
        }

        // ---- Print result
        cout<<"Shortest distances from source "<<src<<":"<<endl;
        for(int i=0;i<V;i++){
            cout<<"Vertex "<<i<<" = "<<dist[i]<<endl;
        }
    }
};

//! TC -> O(V.E)
int main()
{
    Graph g(5);

    g.addEdge(0,1,2);
    g.addEdge(0,2,4);

    g.addEdge(1,4,-1);
    g.addEdge(1,2,-4);

    g.addEdge(2,3,2);

    g.addEdge(3,4,4);

    g.BellManFord(0);

    return 0;
}




/*
*Input:
    Enter number of vertices: 5
    Enter number of edges: 6
    Enter edges (u v w):
    0 1 2
    0 2 4
    1 4 -1
    1 2 -4
    2 3 2
    3 4 4
    Enter source vertex: 0

*Output:
    0 2 -2 0 1



int main()
{
    int V,E;
    cout<<"Enter number of vertices: ";
    cin>>V;

    Graph g(V);

    cout<<"Enter number of edges: ";
    cin>>E;

    cout<<"Enter edges (u v w):"<<endl;
    for(int i=0;i<E;i++){
        int u,v,w;
        cin>>u>>v>>w;
        g.addEdge(u,v,w);
    }

    int src;
    cout<<"Enter source vertex: ";
    cin>>src;

    cout<<"\nAdjacency List:"<<endl;
    g.printAdjList();

    g.bellmanFord(src);

    return 0;
}

*/