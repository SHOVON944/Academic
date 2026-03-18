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

// -------- Linked List
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

//  FIND function
    Node* find(int ITEM){
        Node* LOC = head;
        while(LOC != NULL){
            if(LOC->data == ITEM){
                return LOC;
            }
            LOC = LOC->next;
        }

        return NULL;
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

// ------- Graph
class Graph{
    int V;
    List* adj;   // array of linked lists

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];
    }

    // undirected graph
    void addEdge(int u, int v){
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void findNode(int u, int ITEM){
        Node* LOC = adj[u].find(ITEM);
        if(LOC != NULL){
            cout<<"Found "<<ITEM<<" in list of vertex "<<u<<" at address: "<<LOC<<endl;
        } else{
            cout<<ITEM<<" not found in list of vertex "<<u<<endl;
        }
    }

    void printAdjList(){
        for(int i=0; i<V; i++){
            cout<<i<<": ";
            adj[i].print();
            cout << endl;
        }
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

    cout<<"Adjacency List:"<<endl;
    g.printAdjList();

    cout<<endl;

    // Find location of ITEM
    g.findNode(1,3);   // search 3 in list of vertex 1
    g.findNode(2,5);   // search 5 in list of vertex 2

    return 0;
}

/*
        Enter number of vertices: 5
        Enter number of edges: 5
        Enter edges (u v):
        0 1
        1 2
        1 3
        2 3
        2 4
        Search in which vertex list: 1
        Enter ITEM to find: 3

        Adjacency List:
        0: 1
        1: 0 2 3
        2: 1 3 4
        3: 1 2
        4: 2

        Found 3 in list of vertex 1
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
    cout<<"Enter edges (u v):"<<endl;
    for(int i=0;i<E;i++){
        int u,v;
        cin>>u>>v;
        g.addEdge(u,v);
    }

    cout<<"\nAdjacency List:"<<endl;
    g.printAdjList();

    int vertex,item;
    cout<<"Search in which vertex list: ";
    cin>>vertex;
    cout<<"Enter ITEM to find: ";
    cin>>item;
    g.findNode(vertex,item);

    return 0;
}
*/