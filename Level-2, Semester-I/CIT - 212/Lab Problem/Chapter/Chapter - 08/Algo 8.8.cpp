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

// ------LL
class List{
    Node* head;
    Node* tail;
    Node* avail;

public:
    List(){
        head = tail = NULL;
        avail = NULL;
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

    bool DELETE_ITEM(int ITEM){

        if(head == NULL){
            return false;
        }
        if(head->data == ITEM){
            Node* PTR = head;
            head = head->next;
            PTR->next = avail;
            avail = PTR;

            return true;
        }

        Node* PREV = head;
        Node* PTR = head->next;
        while(PTR != NULL){
            if(PTR->data == ITEM){
                PREV->next = PTR->next;
                PTR->next = avail;
                avail = PTR;

                return true;
            }
            PREV = PTR;
            PTR = PTR->next;
        }
        return false;
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};


// ---- Graph
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

    void DELETE_EDGE(int A,int B){
        bool FLAG = adj[A].DELETE_ITEM(B);
        if(FLAG == false){
            cout<<"Edge not found"<<endl;
        } else{
            cout<<"Edge deleted successfully"<<endl;
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
    cout<<"Enter edge to delete (A B): ";
    cin>>A>>B;

    g.DELETE_EDGE(A,B);
    cout<<"Updated Graph:"<<endl;
    g.printAdjList();

    return 0;
}