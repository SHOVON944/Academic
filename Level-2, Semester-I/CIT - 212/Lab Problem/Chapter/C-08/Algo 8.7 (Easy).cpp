#include<iostream>
using namespace std;

class Node{
public:
    int data;
    Node* next;

    Node(int val=0){
        data = val;
        next = NULL;
    }
};

// ---------- Linked List for Graph ----------
class List{
    Node* head;    // START
    Node* tail;
    Node* avail;   // AVAILN

public:
    List(){
        head = tail = NULL;
        avail = NULL;
    }

    // Initial Free Node(Avail)
    void initFreeList(int size){
        for(int i=0;i<size;i++){
            Node* temp = new Node();
            temp->next = avail;
            avail = temp;
        }
    }

    bool INSNODE(int N){
        if(avail == NULL){
            return false;
        }
        // Remove node from AVAIL
        Node* NEW = avail;
        avail = avail->next;
        NEW->next = head;
        head = NEW;
        NEW->data = N;

        return true; // success
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
    List* adj;

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];

        // Initialize AVAIL lists for each adjacency list
        for(int i=0;i<V;i++){
            adj[i].initFreeList(5); // 5 free nodes per list
        }
    }

    void addEdgeINSNODE(int u,int v){
        bool FLAG = adj[u].INSNODE(v); // insert in adjacency list of u
        if(!FLAG){
            cout<<"Overflow! Cannot insert edge "<<u<<"->"<<v<<"\n";
        }

        // For undirected graph, also insert reverse
        FLAG = adj[v].INSNODE(u);
        if(!FLAG){
            cout<<"Overflow! Cannot insert edge "<<v<<"->"<<u<<"\n";
        }
    }

    void printAdjList(){
        for(int i=0;i<V;i++){
            cout<<i<<": ";
            adj[i].print();
            cout<<endl;
        }
    }
};

int main()
{
    int V,E;       // nice creat input ase
    cout<<"Enter number of vertices: ";
    cin>>V;
    Graph g(V);
    cout<<"Enter number of edges: ";
    cin>>E;
    cout<<"Enter NO:"<<endl;
    for(int i=0; i<E; i++){
        int u,v;
        cin>>u>>v;
        g.addEdgeINSNODE(u,v);
    }

    cout<<"Adjacency List after INSNODE insertions:"<<endl;
    g.printAdjList();

    return 0;
}



/*
    Graph g(5);

    g.addEdgeINSNODE(0, 1);
    g.addEdgeINSNODE(1, 2);
    g.addEdgeINSNODE(1, 3);
    g.addEdgeINSNODE(2, 3);

    g.printAdjList();
    g.addEdgeINSNODE(2, 2);
    g.addEdgeINSNODE(4, 1);
    g.addEdgeINSNODE(1, 4);
    g.addEdgeINSNODE(1, 4);
*/