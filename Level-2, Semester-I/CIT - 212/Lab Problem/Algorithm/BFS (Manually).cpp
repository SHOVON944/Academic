#include <iostream>
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

// -------- LL
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
        } else{
            tail->next = newNode;
            tail = newNode;
        }
    }

    // void push_front(int val){
    //     Node* newNode = new Node(val);      // dynamic
    //     if(head == NULL){
    //         head = tail = newNode;
    //     } else{
    //         newNode->next = head;
    //         head = newNode;
    //     }
    // }


    // void pop_front(){
    //     if(head==NULL){
    //         cout<<"LL is empty"<<endl;
    //         return;
    //     }
    //     Node* temp = head;
    //     head = head->next;
    //     temp->next = NULL;

    //     delete temp;
    // }

    // void pop_back(){    // while(temp->next->next!=NULL), eita empty(), 1 value er jnno work korbe na...2/2+ value er jnno work korbe
    //     if(head==NULL){
    //         cout<<"LL is empty"<<endl;
    //         return;
    //     }
    //     Node* temp = head;
    //     while(temp->next!=tail){
    //         temp = temp->next;
    //     }
    //     temp->next = NULL;
    //     delete tail;
    //     tail = temp;
    // }

    Node* getHead(){
        return head;
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

//---- Queue
class Queue{
    Node* head;
    Node* tail;
    int count;

public:
    Queue(){
        head = tail = NULL;
        count = 0;
    }

    void push(int val){
        Node* newNode = new Node(val);

        if(empty()){
            head = tail = newNode;
        } else{
            tail->next = newNode;
            tail = newNode;
        }
        count++;
    }

    void pop(){
        if(empty()){
            return;
        } else{
            Node* temp = head;
            head = head->next;
            delete temp;

            if(head == NULL){
                tail = NULL;
            }
        }
        count--;
    }

    int front(){
        if(empty()){
            return -1;
        }

        return head->data;
    }

    int size(){
        return count;
    }

    bool empty(){
        return head==NULL;
    }
};

// ----- Graph
class Graph{
    int V;
    List* adj;        // adjacency list
    bool* visited;   // visited array

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];      // creating empty list for each vertex
        visited = new bool[V];  // creating visited array

        for(int i = 0; i < V; i++){
            visited[i] = false; // In first, all vertex unvisited
        }
    }

    // undirected graph
    void addEdge(int u, int v){
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void printAdjList(){
        for (int i = 0; i < V; i++) {
            cout << i << ": ";
            adj[i].print();
            cout << endl;
        }
    }

    // BFS Traversal
    void BFS(int src){
        for(int i =0; i<V; i++){
            visited[i] = false;
        }
        Queue q;
        q.push(src);
        visited[src] = true;

        while(!q.empty()){
            int u = q.front();
            q.pop();

            cout<<u<<" ";

            Node* temp = adj[u].getHead();
            while(temp != NULL){
                int v = temp->data;
                if(!visited[v]){
                    visited[v] = true;
                    q.push(v);
                }
                temp = temp->next;
            }
        }
    }
};

int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);

    cout<<"Adjacency List: "<<endl;
    g.printAdjList();

    cout<<endl<<"BFS Traversal: ";
    g.BFS(0);   // user input er somoi head value pass hobe eikhan theke

    return 0;
}

//* Example
/*
                    ----4
0-------    -------2
        ---1     -----3
*/


/*
int main()
{
    int V,E;
    cout<<"Enter number of vertices: ";
    cin>>V;
    Graph g(V);
    cout<<"Enter number of edges: ";
    cin>>E;
    cout<<"Enter edges (u v):"<<endl;
    for(int i=0; i<E; i++){
        int u,v;
        cin>>u>>v;
        g.addEdge(u,v);
    }
    g.printAdjList();
    int src;
    cout<<"Enter BFS starting vertex: ";
    cin>>src;
    cout<<endl<<"BFS Traversal: ";
    g.BFS(src);

    return 0;
}
*/