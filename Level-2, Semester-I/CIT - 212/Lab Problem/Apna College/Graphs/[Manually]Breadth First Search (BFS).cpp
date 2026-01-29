#include <iostream>
using namespace std;

/* ---------- Node ---------- */
class Node {
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

    void push_back(int val) {
        Node* newNode = new Node(val);

        if (head == NULL) {
            head = tail = newNode;
        } else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    Node* getHead() {
        return head;
    }

    void print() {
        Node* temp = head;
        while (temp != NULL) {
            cout << temp->data << " ";
            temp = temp->next;
        }
    }
};

/* ---------- Queue (using Linked List) ---------- */
class Queue {
    Node* frontNode;
    Node* rearNode;

public:
    Queue() {
        frontNode = rearNode = NULL;
    }

    bool empty() {
        return frontNode == NULL;
    }

    void push(int val) {
        Node* newNode = new Node(val);

        if (empty()) {
            frontNode = rearNode = newNode;
        } else {
            rearNode->next = newNode;
            rearNode = newNode;
        }
    }

    void pop() {
        if (empty()) return;

        Node* temp = frontNode;
        frontNode = frontNode->next;
        delete temp;

        if (frontNode == NULL)
            rearNode = NULL;
    }

    int front() {
        if (empty()) return -1;
        return frontNode->data;
    }
};

/* ---------- Graph ---------- */
class Graph {
    int V;
    List* adj;        // adjacency list
    bool* visited;   // visited array

public:
    Graph(int V) {
        this->V = V;
        adj = new List[V];
        visited = new bool[V];

        for (int i = 0; i < V; i++)
            visited[i] = false;
    }

    // undirected graph
    void addEdge(int u, int v) {
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void printAdjList() {
        for (int i = 0; i < V; i++) {
            cout << i << ": ";
            adj[i].print();
            cout << endl;
        }
    }

    // BFS Traversal
    void BFS(int src = 0) {
        Queue q;

        q.push(src);
        visited[src] = true;

        while (!q.empty()) {
            int u = q.front();
            q.pop();

            cout << u << " ";

            Node* temp = adj[u].getHead();
            while (temp != NULL) {
                int v = temp->data;
                if (!visited[v]) {
                    visited[v] = true;
                    q.push(v);
                }
                temp = temp->next;
            }
        }
    }
};

/* ---------- Main ---------- */
int main() {
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    g.addEdge(2, 4);

    cout << "Adjacency List:\n";
    g.printAdjList();

    cout << "\nBFS Traversal:\n";
    g.BFS(0);

    return 0;
}
