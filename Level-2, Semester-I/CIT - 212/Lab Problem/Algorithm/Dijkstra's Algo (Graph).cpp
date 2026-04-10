#include <iostream>
using namespace std;

// ------ Adjacency List Node
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

// ---- LL
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

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<"("<<temp->vertex<<","<<temp->weight<<") -> ";
            temp = temp->next;
        }

        cout<<"NULL";
    }
};

// ----- Priority Queue Node
class PQNode{
public:
    int vertex;
    int dist;
    PQNode* link;

    PQNode(int v,int d){
        vertex = v;
        dist = d;
        link = NULL;
    }
};

// ------ Priority Queue
class PriorityQueue{
    PQNode* start;

public:
    PriorityQueue(){
        start = NULL;
    }

    void push(int v,int d){
        PQNode* newNode = new PQNode(v,d);
        if(start == NULL || d < start->dist){
            newNode->link = start;
            start = newNode;
            return;
        }

        PQNode* temp = start;
        while(temp->link != NULL && temp->link->dist <= d){
            temp = temp->link;
        }
        newNode->link = temp->link;
        temp->link = newNode;
    }

    PQNode* pop(){

        if(start == NULL){
            return NULL;
        }
        PQNode* temp = start;
        start = start->link;

        return temp;
    }

    bool empty(){
        return start == NULL;
    }
};

// ---- Graph Class
class Graph{
    int V;
    List* adj;

public:
    Graph(int v){
        V = v;
        adj = new List[V];
    }

    void addEdge(int u,int v,int w){
        adj[u].push_back(v,w);
        adj[v].push_back(u,w);
    }

    void printAdjList(){
        for(int i=0;i<V;i++){
            cout<<i<<" : ";
            adj[i].print();
            cout<<endl;
        }
    }

    //* -------- Dijkstra Algorithm 
    void dijkstra(int src){
        int* dist = new int[V];
        bool* visited = new bool[V];
        for(int i=0;i<V;i++){
            dist[i] = 999999;
            visited[i] = false;
        }

        PriorityQueue pq;
        dist[src] = 0;
        pq.push(src,0);
        while(!pq.empty()){
            PQNode* p = pq.pop();
            int u = p->vertex;
            if(visited[u]){
                continue;
            }
            visited[u] = true;
            Node* temp = adj[u].getHead();
            while(temp != NULL){
                int v = temp->vertex;
                int w = temp->weight;
                if(dist[v] > dist[u] + w){
                    dist[v] = dist[u] + w;
                    pq.push(v,dist[v]);
                }
                temp = temp->next;
            }
        }

        cout<<"Shortest distance from source "<<src<<":"<<endl;
        for(int i=0;i<V;i++){
            cout<<"Vertex "<<i<<" = "<<dist[i]<<endl;
        }
    }
};

int main()
{
    Graph g(6);

    g.addEdge(0,1,2);
    g.addEdge(0,2,4);

    g.addEdge(1,2,1);
    g.addEdge(1,3,7);

    g.addEdge(2,4,3);

    g.addEdge(3,5,1);

    g.addEdge(4,3,2);
    g.addEdge(4,5,5);

    cout<<"Adjacency List:"<<endl;
    g.printAdjList();

    cout<<endl;

    g.dijkstra(0);

    return 0;
}

//* Example
/*
        Enter number of vertices: 6
        Enter number of edges: 8
        Enter edges (u v w):
        0 1 2
        0 2 4
        1 2 1
        1 3 7
        2 4 3
        3 5 1
        4 3 2
        4 5 5
        Enter source vertex: 0
*/
/*
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
    cout<<"Adjacency List:"<<endl;

    g.printAdjList();
    g.dijkstra(src);

    return 0;
}
*/