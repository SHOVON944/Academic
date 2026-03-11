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

    bool INSNODE(){
        if(avail == NULL){
            return false;
        }
        // Remove node from AVAIL
        Node* NEW = avail;
        avail = avail->next;
        NEW->next = head;
        head = NEW;

        return true;
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

    void insertNode(int u){
        bool FLAG = adj[u].INSNODE();
        if(!FLAG){
            cout<<"Overflow! Cannot insert node in list "<<u<<endl;
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
    int V;
    cout<<"Enter number of lists(vertices): ";
    cin>>V;

    Graph g(V);

    int n;
    cout<<"How many nodes to insert in each list: ";
    cin>>n;

    for(int i=0;i<V;i++){
        for(int j=0;j<n;j++){
            g.insertNode(i);
        }
    }

    cout<<endl<<"After Node Insertions:"<<endl;
    g.printAdjList();

    return 0;
}
