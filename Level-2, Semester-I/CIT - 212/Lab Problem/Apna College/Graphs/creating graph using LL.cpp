#include<iostream>
using namespace std;

class Node{
public:
    int data;
    Node* next;

    Node(int val) {
        data = val;
        next = NULL;
    }
};

/* ---------- Linked List ---------- */
class List {
    Node* head;
    Node* tail;

public:
    List() {
        head = tail = NULL;
    }

    // insert at end
    void push_back(int val) {
        Node* newNode = new Node(val);

        if (head == NULL) {
            head = tail = newNode;
            return;
        }

        tail->next = newNode;
        tail = newNode;
    }

    // print list
    void print() {
        Node* temp = head;
        while (temp != NULL) {
            cout << temp->data << " ";
            temp = temp->next;
        }
    }
};

class Graph {
    int V;
    List* adj;   // array of linked lists

public:
    Graph(int V) {
        this->V = V;
        adj = new List[V];
    }

    // undirected graph
    void addEdge(int u, int v) {
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void printAdjList() {
        for (int i = 0; i < V; i++) {
            cout << i << ": ";
            adj[i].print();     //* adj[i].print() == print(&adj[i]), compliner er vitore emonvabe run hoi. print() function ta adj[i] obj er jnno colbe
            cout << endl;
        }
    }
};

/* ---------- Main ---------- */
int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    g.addEdge(2, 4);

    g.printAdjList();

    return 0;
}
