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

// -------- Linked List --------
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

    Node* getHead(){
        return head;
    }

    void setHead(Node* h){
        head = h;
    }

    void print(){
        Node* temp = head;

        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

// -------- Graph --------
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

    // Procedure 8.9 DELNODE
    void DELETE_NODE(int N){

        if(N < 0 || N >= V){
            cout<<"Node not found\n";
            return;
        }

        // Step 3: delete edges ending at N
        for(int i=0;i<V;i++){
            adj[i].DELETE_ITEM(N);
        }

        // Step 4: clear adjacency list of N
        adj[N].setHead(NULL);

        cout<<"Node "<<N<<" deleted from graph\n";
    }

};


// -------- Main --------
int main(){

    Graph g(5);

    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(2,4);

    cout<<"Adjacency List:\n";
    g.printAdjList();

    int N;

    cout<<"\nEnter node to delete: ";
    cin>>N;

    g.DELETE_NODE(N);

    cout<<"\nUpdated Graph:\n";
    g.printAdjList();

    return 0;
}