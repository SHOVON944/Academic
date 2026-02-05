#include <iostream>
using namespace std;

//---- Node
class Node{
public:
    int data;
    Node* next;

    Node(int val){
        data = val;
        next = NULL;
    }
};

//---- Linked List
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

//---Graph
class Graph{
    int V;
    List* adj;        // adjacency list

public:
    Graph(int V){
        this->V = V;
        adj = new List[V];
    }

    void addEdge(int u, int v){
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    //----DFS Helper
    void DFS_Helper(int u, bool* visit){
        cout<<u<<" ";
        visit[u] = true;

        Node* temp = adj[u].getHead();
        while(temp != NULL){
            int v = temp->data;
            if(!visit[v]){
                DFS_Helper(v, visit);
            }
            temp = temp->next;
        }
    }

//    DFS
    //! TC: O(V + E)
    void DFS(int src){
        bool* visit = new bool[V];

        for(int i=0; i<V; i++)
            visit[i] = false;

        DFS_Helper(src, visit);
        cout<<endl;
/*
! if any one or more edge are not connected than we should call multiple source in diff diff components in BFS/ DFS thle uporer DF_Helper(src, visit); and cout<<endl; bad dia nicer ei code ta likhbo
*   for(int i=0; i<V; i++){
*       if(!visit[i]){
*         DFS_Helper(i, visit);
*        }
    }
*/
        // delete[] visit;
    }
};

int main()
{
    Graph g(5);

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);

    cout<<"DFS: ";
    g.DFS(0);

    return 0;
}
