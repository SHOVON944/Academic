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

//      LL
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

    // DELETE first node containing ITEM
    bool deleteItem(int ITEM){
        Node* temp = head;
        Node* prev = NULL;
        while(temp != NULL){
            if(temp->data == ITEM){
                if(prev == NULL){           // first node
                    head = temp->next;
                } else{    // any node except first, last node
                    prev->next = temp->next;
                }
                // last node check alada hobe, nahole if(temp == tail) kkno execute hobe na. so tail o update hobe na
                if(temp == tail){    // if last node
                    tail = prev;
                }

                delete temp;
                return true;   // FLAG = TRUE
            }

            prev = temp;
            temp = temp->next;
        }

        return false;  // FLAG = FALSE
    }

    void print(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data<<" ";
            temp = temp->next;
        }
    }
};

// -------Graph
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

    void deleteNode(int u,int ITEM){
        bool FLAG = adj[u].deleteItem(ITEM);

        if(FLAG)
            cout<<"Deleted "<<ITEM<<" from list of vertex "<<u<<endl;
        else
            cout<<"ITEM not found (FLAG = FALSE)"<<endl;
    }

    void printAdjList(){
        for(int i=0;i<V;i++){
            cout<<i<<": ";
            adj[i].print();
            cout<<endl;
        }
    }
};

int main()
{
    int V,E;
    cout<<"Enter number of vertices: ";
    cin>>V;
    Graph g(V);
    cout<<"Enter number of edges: ";
    cin>>E;
    cout<<"Enter edges (u v):"<<endl;
    for(int i=0;i<E;i++){
        int u,v;
        cin>>u>>v;
        g.addEdge(u,v);
    }

    cout<<endl<<"Adjacency List:"<<endl;
    g.printAdjList();

    int vertex,item;
    cout<<endl<<"Enter vertex list to delete from: ";
    cin>>vertex;
    cout<<"Enter ITEM to delete: ";
    cin>>item;

    g.deleteNode(vertex,item);
    cout<<endl<<"Updated List:"<<endl;
    g.printAdjList();

    return 0;
}

/*
    * Input:
        Enter number of vertices: 5
        Enter number of edges: 5
        0 1
        1 2
        1 3
        2 3
        2 4
        Enter vertex list to delete from: 1
        Enter ITEM to delete: 3

    * Output:
        Adjacency List:
        0: 1
        1: 0 2 3
        2: 1 3 4
        3: 1 2
        4: 2

        Deleted 3 from list of vertex 1

        Updated List:
        0: 1
        1: 0 2
        2: 1 3 4
        3: 1 2
        4: 2
*/

/*
int main()
{
    Graph g(5)
    / fixed edges
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(2,4);
    cout<<"Adjacency List:"<<endl;
    g.printAdjList();

    cout<<endl;

    / delete অপারেশন
    g.deleteNode(1,3);   // vertex 1 এর list থেকে 3 delete

    cout<<"\nAfter Deletion:"<<endl;
    g.printAdjList();

    return 0;
}
*/