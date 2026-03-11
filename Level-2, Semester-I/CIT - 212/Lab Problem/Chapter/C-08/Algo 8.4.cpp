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

// ------ LL
class List{
    Node* head;
    Node* tail;
    Node* avail;   // free list

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

    bool DELETE_ITEM(int ITEM){
        if(head == NULL){
            return false;
        }

        // Check ITEM in first node
        if(head->data == ITEM){
            Node* PTR = head;
            head = head->next;
            PTR->next = avail;
            avail = PTR;

            return true;
        }

        // check Item except first Node
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

    void DELETE_NODE(int item){
        bool FLAG = DELETE_ITEM(item);

        if(FLAG == false){
            cout<<"Item not found.";
        }
        else{
            cout<<"Item deleted successfully.";
        }
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

    void DELETE_IN_VERTEX(int v,int item){
        adj[v].DELETE_NODE(item);
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

    int vertex,item;
    cout<<"Delete from adjacency list of vertex: ";
    cin>>vertex;
    cout<<"Enter item to delete: ";
    cin>>item;
    g.DELETE_IN_VERTEX(vertex,item);

    cout<<"Updated Graph:"<<endl;
    g.printAdjList();

    return 0;
}