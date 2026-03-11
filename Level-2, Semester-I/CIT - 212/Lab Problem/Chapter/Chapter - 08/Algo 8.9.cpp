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

class List{
public:
    Node* head;
    Node* tail;
    Node* avail;

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

    Node* FIND(int ITEM){
        Node* PTR = head;
        while(PTR != NULL){
            if(PTR->data == ITEM){
                return PTR;
            }
            PTR = PTR->next;
        }

        return NULL;
    }

    bool DELETE_NODE(int ITEM){
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
        Node* SAVE = head;
        Node* PTR = head->next;

        while(PTR != NULL){
            if(PTR->data == ITEM){
                SAVE->next = PTR->next;
                PTR->next = avail;
                avail = PTR;

                return true;
            }
            SAVE = PTR;
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
        for(int i = 0; i < V; i++){
            if(adj[i].head != NULL){      // skip deleted nodes
                cout << i << ": ";
                adj[i].print();
                cout << endl;
            }
        }
    }

    void DELNODE(int N, bool &FLAG){
        if(N < 0 || N >= V){
            FLAG = false;
            return;
        }
        for(int i=0;i<V;i++){
            if(i != N){ // skip the node itself
                adj[i].DELETE_NODE(N);
            }
        }

        //! caile ei nicer code na dileu hobe...but eita memory reuse(efficient)
        // if(adj[N].head != NULL){
        //     Node* BEG = adj[N].head;
        //     Node* END = adj[N].head;
        //     while(END->next != NULL) END = END->next;

        //     END->next = adj[N].avail;
        //     adj[N].avail = BEG;
        // }

        adj[N].head = NULL;
        adj[N].tail = NULL;

        FLAG = true;
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
    int N;
    bool FLAG;
    cout<<"Enter node to delete: ";
    cin>>N;
    g.DELNODE(N,FLAG);
    if(FLAG == false){
        cout<<"Node not found"<<endl;
    } else{
        cout<<"Node deleted successfully"<<endl;
    }

    cout<<"Updated Graph:"<<endl;
    g.printAdjList();

    return 0;
}