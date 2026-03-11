#include<iostream>
using namespace std;

class Node{
public:
    int data;
    Node* next;

    Node(int val){
        data = val;
        next = NULL;
    }
};

//      LL
class List{
    Node* head;
    Node* tail;

public:
    List(){
        head = tail = NULL;
    }

    void push_back(int val){
        Node* newNode = new Node(val);

        if(head == NULL){
            head = tail = newNode;
            return;
        }

        tail->next = newNode;
        tail = newNode;
    }

    int FIND(int ITEM){
        Node* PTR = head;
        int pos = 1;

        while(PTR != NULL){
            if(PTR->data == ITEM){
                return pos;
            }
            PTR = PTR->next;
            pos++;
        }

        return -1;
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<< " ";
            temp = temp->next;
        }
    }

    void SEARCH_ITEM(int item){
        int LOC = FIND(item);

        if(LOC == -1){
            cout<<"Item not found.";
        } else{
            cout<<"Item found at position: "<<LOC;
        }
    }
};

class Graph{
    int V;
    List* adj;

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];
    }

    void addEdge(int u, int v){
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void printAdjList(){
        for(int i=0; i<V; i++){
            cout<<i<<": ";
            adj[i].print();
            cout << endl;
        }
    }

    void SEARCH_IN_VERTEX(int v, int item){
        adj[v].SEARCH_ITEM(item);
    }
};

int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    g.addEdge(2, 4);

    g.printAdjList();

    int vertex, item;
    cout<<"Search in adjacency list of vertex: ";
    cin>>vertex;
    cout<<"Enter item to search: ";
    cin>>item;

    g.SEARCH_IN_VERTEX(vertex, item);

    return 0;
}

/*
    int V;
    cout<<"Enter number of vertices: ";
    cin>>V;
    Graph g(V);
    int E;
    cout<<"Enter number of edges: ";
    cin>>E;
    cout<<"Enter edges (u v):"<<endl;
    for(int i=0; i<E; i++){
        int u, v;
        cin>>u>>v;
        g.addEdge(u, v);
    }
*/