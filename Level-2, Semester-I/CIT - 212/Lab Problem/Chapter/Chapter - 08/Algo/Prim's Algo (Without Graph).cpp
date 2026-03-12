#include <iostream>
using namespace std;

// ----- Adjacency List Node
class Node{
public:
    int vertex;
    int weight;
    Node* next;

    Node(int v, int w){
        vertex = v;
        weight = w;
        next = NULL;
    }
};

// ------LL
class List{
    Node* head;

public:
    List(){
        head = NULL;
    }

    void push_back(int v, int w){
        Node* newNode = new Node(v,w);
        if(head == NULL){
            head = newNode;
            return;
        }

        Node* temp = head;
        while(temp->next != NULL){
            temp = temp->next;
        }
        temp->next = newNode;
    }

    Node* getHead(){
        return head;
    }

    void print(){
    Node* temp = head;
    while(temp != NULL){
        cout << "(" << temp->vertex << "," << temp->weight << ") -> ";
        temp = temp->next;
    }

    cout << "NULL";
}
};

// ------ Priority Queue Node
class PQNode{
public:
    int vertex;
    int weight;
    PQNode* link;

    PQNode(int v,int w){
        vertex = v;
        weight = w;
        link = NULL;
    }
};

// ----- Priority Queue
class PriorityQueue{
    PQNode* start;

public:
    PriorityQueue(){
        start = NULL;
    }

    void push(int v,int w){
        PQNode* newNode = new PQNode(v,w);
        if(start == NULL || w < start->weight){
            newNode->link = start;
            start = newNode;
            return;
        }

        PQNode* ptr = start;
        while(ptr->link != NULL && ptr->link->weight <= w){
            ptr = ptr->link;
        }
        newNode->link = ptr->link;
        ptr->link = newNode;
    }

    PQNode* pop(){
        if(start == NULL){
            return NULL;
        }
        PQNode* temp = start;
        start = start->link;

        return temp;
    }

    bool empty(){
        return start == NULL;
    }
};

// ------ Prim Algorithm
int primMST(int V, List adj[]){
    bool* inMST = new bool[V];
    for(int i=0; i<V; i++){
        inMST[i] = false;
    }

    PriorityQueue pq;
    pq.push(0,0);
    int cost = 0;
    while(!pq.empty()){
        PQNode* p = pq.pop();
        int u = p->vertex;
        int wt = p->weight;

        if(inMST[u]){
            continue;
        }

        inMST[u] = true;
        cost += wt;
        Node* temp = adj[u].getHead();
        while(temp != NULL){
            int v = temp->vertex;
            int w = temp->weight;
            if(!inMST[v]){
                pq.push(v,w);
            }

            temp = temp->next;
        }
    }

    return cost;
}

void printAdjList(int V, List adj[]){
    for(int i=0;i<V;i++){
        cout<<i<<" : ";
        adj[i].print();
        cout<<endl;
    }
}

// -------- Main --------
int main(){

    int V = 4;

    List adj[4];

    // Undirected Graph
    adj[0].push_back(1,10);
    adj[1].push_back(0,10);

    adj[0].push_back(3,30);
    adj[3].push_back(0,30);

    adj[0].push_back(2,15);
    adj[2].push_back(0,15);

    adj[1].push_back(3,40);
    adj[3].push_back(1,40);

    adj[2].push_back(3,50);
    adj[3].push_back(2,50);

    printAdjList(V, adj);
    cout<<"Minimum Cost of MST = "<<primMST(V,adj);

    return 0;
}

//* Example (user input)
/*
        Enter number of vertices: 4
        Enter number of edges: 5
        Enter edges (u v w):

        0 1 10
        0 3 30
        0 2 15
        1 3 40
        2 3 50
int main()
{
    int V,E;
    cout<<"Enter number of vertices: ";
    cin>>V;
    List* adj = new List[V];
    cout<<"Enter number of edges: ";
    cin>>E;
    cout<<"Enter edges (u v w): "<<endl;
    for(int i=0;i<E;i++){
        int u,v,w;
        cin>>u>>v>>w;
        adj[u].push_back(v,w);
        adj[v].push_back(u,w); // undirected graph
    }
    cout<<"Minimum Cost of MST = "<<primMST(V,adj);

    return 0;
}
*/