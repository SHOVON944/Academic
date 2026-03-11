#include <iostream>
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

//*     Linked List
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

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout << temp->data << " ";
            temp = temp->next;
        }
    }

    int FIND(int item){
        Node* PTR = head;
        int LOC = 1;
        while(PTR != NULL){
            if(item == PTR->data){
                return LOC;
            }
            PTR = PTR->next;
            LOC++;
        }
        return 0; // not found
    }
};

//*------ Graph using adjacency list
class Graph{
    int V;       // number of vertices
    List* adj;   // array of linked lists

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
        for(int i = 0; i < V; i++){
            cout << i << ": ";
            adj[i].print();
            cout << endl;
        }
    }

    void findInAdj(int u, int item){        //! eita kono ekta vertex er value er jnno find..r all vertex er jnno value find nice...
        int loc = adj[u].FIND(item);
        if(loc){
            cout << "Node " << item << " found at position " << loc << " in adjacency list of vertex " << u << endl;
        } else{
            cout << "Node " << item << " not found in adjacency list of vertex " << u << endl;
        }
    }
};

int main()
{
    int V, E;
    cout<<"Enter number of vertices: ";
    cin>>V;
    cout<<"Enter number of edges: ";
    cin>>E;

    Graph g(V);

    cout<<"Enter edges (u v) format (0-based index):" << endl;
    for(int i = 0; i < E; i++){
        int u, v;
        cin>>u>>v;
        g.addEdge(u, v);
    }
    cout<<"\nGraph adjacency list:\n";
    g.printAdjList();
    int u, item;
    cout << "Enter vertex u: ";
    cin >> u;
    cout << "Enter node to find: ";
    cin >> item;
    g.findInAdj(u, item);

    return 0;
}






/*
    void findIndAdj(int item){
        bool found = false;

        for(int i=0;i<V;i++){
            int loc = adj[i].FIND(item);

            if(loc != 0){
                cout<<"Node "<<item<<" found in adjacency list of vertex "<<i<<" at position "<<loc<<endl;
                found = true;
            }
        }

        if(!found){
            cout<<"Node "<<item<<" not found in graph"<<endl;
        }
    }
*/