#include <iostream>
using namespace std;

class Node{
public:
    int data;
    Node* next;

    Node(int val = 0){
        data = val;
        next = nullptr;
    }
};

// --------LL
class List{
public:
    Node* head;
    Node* avail;

    List(){
        head = nullptr;
        avail = nullptr;
    }

    // Initialize free list with size nodes
    void initFreeList(int size) {
        for (int i = 0; i < size; i++) {
            Node* temp = new Node();
            temp->next = avail;
            avail = temp;
        }
    }

    int FIND(int ITEM){
        Node* PTR = head;
        int pos = 1;
        while(PTR != nullptr){
            if(PTR->data == ITEM){
                return pos;
            }

            PTR = PTR->next;
            pos++;
        }
        return -1; // not found
    }

    bool INSEDGF(int START, int DEST){
        bool FLAG;
        int posStart = FIND(START); // no use
        int posDest = FIND(DEST);
        if(posDest != -1){
            FLAG = false;
            return FLAG;
        }

        if(avail == NULL){
            FLAG = false;
            return FLAG;
        }

        Node* NEW = avail;
        avail = avail->next;
        NEW->data = DEST;
        NEW->next = head;
        head = NEW;

        FLAG = true;
        return FLAG;
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

// ---------- Graph
class Graph{
    int V;
    List* adj;

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];

        for(int i = 0; i < V; i++){
            adj[i].initFreeList(5);
        }
    }

    void insertEdge(int A, int B){
        if (A < 0 || A >= V || B < 0 || B >= V){
            cout<<"Invalid vertices: "<<A<<" "<<B<<"\n";
            return;
        }
        bool FLAG;
        // Insert B into adjacency list of A
        FLAG = adj[A].INSEDGF(A, B);  // START = A, DEST = B
        if(!FLAG){
            cout << "Cannot insert edge " << A << "->" << B << " (Overflow or duplicate)\n";
        }

        // Insert A into adjacency list of B (undirected graph)
        FLAG = adj[B].INSEDGF(B, A);  // START = B, DEST = A
        if(!FLAG){
            cout << "Cannot insert edge " << B << "->" << A << " (Overflow or duplicate)\n";
        }
    }

    void printAdjList(){
        for(int i=0; i<V; i++){
            cout<<i<<": ";
            adj[i].print();
            cout<<endl;
        }
    }
};

int main()
{
    int V, E;
    cout<<"Enter number of vertices: ";
    cin>>V;
    Graph g(V);

    cout<<"Enter number of edges: ";
    cin>>E;
    cout<<"Enter edges (A B):"<<endl;
    for(int i=0; i<E; i++){
        int A, B;
        cin>>A>>B;
        g.insertEdge(A, B);
    }

    cout << "Adjacency List after INSEDGF insertions:"<<endl;
    g.printAdjList();

    return 0;
}

// Input
/*
Enter number of vertices: 4
Enter number of edges: 4
0 1
0 2
1 2
2 3
*/