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

// ------- Linked List
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

        return -1; // not found
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

// -------- Graph
class Graph{
    int V;
    List* adj;

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];
    }

    void addEdge(int u,int v){
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void printAdjList(){
        for(int i=0;i<V;i++){
            cout<<i<<": ";
            adj[i].print();
            cout<<endl;
        }
    }

    void FINDEDGE(int A,int B){
        int LOC = adj[A].FIND(B);
        if(LOC == -1){
            cout<<"Edge ("<<A<<","<<B<<") not found.";
        } else{
            cout<<"Edge ("<<A<<","<<B<<") found at position "<<LOC;
        }
    }
};

int main()
{
    Graph g(5);

    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(2,4);

    cout<<"Adjacency List:"<<endl;
    g.printAdjList();

    int A,B;
    cout<<"\nEnter edge to find (A B): ";
    cin>>A>>B;
    g.FINDEDGE(A,B);

    return 0;
}