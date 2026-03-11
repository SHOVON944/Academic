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

/* ---------- Linked List ---------- */
class List{
    Node* head;
    Node* tail;
    Node* avail;   // free list (AVAIL)

public:
    List(){
        head = tail = NULL;
        avail = NULL;
    }

    // insert at end
    void push_back(int val){
        Node* newNode = new Node(val);

        if(head == NULL){
            head = tail = newNode;
            return;
        }

        tail->next = newNode;
        tail = newNode;
    }

    // DELETE first node containing ITEM
    bool DELETE(int ITEM){

        // Step 1: list empty?
        if(head == NULL){
            return false;
        }

        // Step 2: ITEM in first node?
        if(head->data == ITEM){
            Node* PTR = head;
            head = head->next;

            PTR->next = avail;
            avail = PTR;

            return true;
        }

        // Step 3
        Node* SAVE = head;
        Node* PTR = head->next;

        // Step 4 loop
        while(PTR != NULL){

            // Step 5
            if(PTR->data == ITEM){
                SAVE->next = PTR->next;

                PTR->next = avail;
                avail = PTR;

                return true;
            }

            // Step 6 update pointers
            SAVE = PTR;
            PTR = PTR->next;
        }

        // Step 7
        return false;
    }

    // print list
    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout << temp->data << " ";
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

    void addEdge(int u, int v){
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void printAdjList(){
        for(int i=0;i<V;i++){
            cout << i << ": ";
            adj[i].print();
            cout << endl;
        }
    }

    void deleteEdge(int u, int v){
        if(adj[u].DELETE(v)){
            cout << "Deleted edge " << u << "->" << v << endl;
        } else {
            cout << "Edge " << u << "->" << v << " not found" << endl;
        }

        if(adj[v].DELETE(u)){
            cout << "Deleted edge " << v << "->" << u << endl;
        } else {
            cout << "Edge " << v << "->" << u << " not found" << endl;
        }
    }
};

int main()
{
    int V, E;
    cout << "Enter number of vertices: ";
    cin >> V;
    Graph g(V);

    cout << "Enter number of edges: ";
    cin >> E;

    cout << "Enter edges (u v):\n";
    for(int i=0;i<E;i++){
        int u,v;
        cin >> u >> v;
        g.addEdge(u,v);
    }

    cout << "\nAdjacency List:\n";
    g.printAdjList();

    int u,v;
    cout << "Enter edge to delete (u v): ";
    cin >> u >> v;
    g.deleteEdge(u,v);
    cout << "\nUpdated Adjacency List:\n";
    g.printAdjList();

    return 0;
}