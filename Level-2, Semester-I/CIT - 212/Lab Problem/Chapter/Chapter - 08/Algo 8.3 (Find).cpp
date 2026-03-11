#include <bits/stdc++.h>
using namespace std;

/* ---------- Node for Linked List ---------- */
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

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout << temp->data << " ";
            temp = temp->next;
        }
    }

    // Optional: FIND function in adjacency list
    int FIND(int item){
        Node* PTR = head;
        int LOC = 1;
        while(PTR != NULL){
            if(item == PTR->data){
                return LOC;
            }
            PTR = PTR->next;
            LOC++;
        }
        return 0; // not found
    }
};

/* ---------- Graph using adjacency list ---------- */
class Graph{
    int V;       // number of vertices
    List* adj;   // array of linked lists

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];
    }

    void addEdge(int u, int v){
        adj[u].push_back(v);
        adj[v].push_back(u); // undirected graph
    }

    void printAdjList(){
        for(int i = 0; i < V; i++){
            cout << i << ": ";
            adj[i].print();
            cout << endl;
        }
    }

    void findInAdj(int u, int item){
        int loc = adj[u].FIND(item);
        if(loc) cout << "Node " << item << " found at position " << loc << " in adjacency list of vertex " << u << endl;
        else cout << "Node " << item << " not found in adjacency list of vertex " << u << endl;
    }
};

/* ---------- Main ---------- */
int main(){
    int V, E;
    cout << "Enter number of vertices: ";
    cin >> V;
    cout << "Enter number of edges: ";
    cin >> E;

    Graph g(V);

    cout << "Enter edges (u v) format (0-based index):" << endl;
    for(int i = 0; i < E; i++){
        int u, v;
        cin >> u >> v;
        g.addEdge(u, v);
    }

    cout << "\nGraph adjacency list:\n";
    g.printAdjList();

    // Optional: FIND node in adjacency list
    char choice;
    cout << "\nDo you want to find a node in adjacency list? (y/n): ";
    cin >> choice;
    if(choice == 'y' || choice == 'Y'){
        int u, item;
        cout << "Enter vertex u: ";
        cin >> u;
        cout << "Enter node to find: ";
        cin >> item;
        g.findInAdj(u, item);
    }

    return 0;
}