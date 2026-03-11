#include <iostream>
using namespace std;

// -------Node
class Node{
public:
    int data;
    Node* next;

    Node(int val = 0){
        data = val;
        next = nullptr;
    }
};

// ---------LL
class List{
public:
    Node* head;
    Node* avail;

    List(){
        head = nullptr;
        avail = nullptr;
    }

    void initFreeList(int size){
        for(int i=0; i<size; i++){
            Node* temp = new Node();
            temp->next = avail;
            avail = temp;
        }
    }

    int FIND(int ITEM){
        Node* PTR = head;
        int pos = 1;
        while(PTR != nullptr){
            if (PTR->data == ITEM) return pos;
            PTR = PTR->next;
            pos++;
        }
        return -1; // not found
    }

    bool DELETE(int DEST){
        bool FLAG = false;
        Node* PTR = head;
        Node* PREV = nullptr;

        while(PTR != nullptr){
            if(PTR->data == DEST){
                FLAG = true;
                if(PREV == nullptr){
                    head = PTR->next;
                } else{
                    PREV->next = PTR->next;
                }

                PTR->next = avail;
                avail = PTR;
                return FLAG;
            }
            PREV = PTR;
            PTR = PTR->next;
        }
        return FLAG;
    }

    bool INSEDGF(int START, int DEST){
        bool FLAG;

        int posStart = FIND(START); // no use
        int posDest = FIND(DEST);
        if(posDest != -1){
            return false; // already exists
        }
        if(avail == nullptr){
            return false;
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
        while(temp != nullptr){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

// ------ Graph
class Graph{
    int V;
    List* adj;

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];
        for(int i=0; i<V; i++){
            adj[i].initFreeList(10); // 10 free nodes per list
        }
    }

    void insertEdge(int A, int B){
        if(A<0 || A>=V || B<0 || B>=V){
            cout<<"Invalid vertices: "<<A<<" "<<B<<endl;
            return;
        }

        bool FLAG = adj[A].INSEDGF(A, B);
        if(!FLAG){
            cout<<"Cannot insert edge "<<A<<"->"<<B<<endl;
        }

        FLAG = adj[B].INSEDGF(B, A);
        if(!FLAG){
            cout<<"Cannot insert edge "<<B<<"->"<<A<<endl;
        }
    }

    void deleteEdge(int A, int B){
        if(A<0 || A>=V || B<0 || B>=V){
            cout<<"Invalid vertices: "<<A<<" "<<B<<endl;
            return;
        }

        bool FLAG = adj[A].DELETE(B);
        if(!FLAG){
            cout<<"Edge "<<A<<"->"<<B<<" not found";
        }

        FLAG = adj[B].DELETE(A);
        if(!FLAG){
            cout<<"Edge "<<B<<"->"<<A<<" not found";
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
    cout<<"Enter edges (A B):\n";
    for(int i=0; i<E; i++){
        int A, B;
        cin>>A>>B;
        g.insertEdge(A, B);
    }

    cout<<"Adjacency List after insertion:"<<endl;
    g.printAdjList();
    int delA, delB;
    cout<<"Enter edge to delete (A B): ";
    cin>>delA>>delB;
    g.deleteEdge(delA, delB);
    cout<<"Adjacency List after deletion:"<<endl;
    g.printAdjList();

    return 0;
}