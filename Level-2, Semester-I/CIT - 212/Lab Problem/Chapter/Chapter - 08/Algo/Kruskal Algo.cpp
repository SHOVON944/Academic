#include <iostream>
using namespace std;

// -------- Edge Node --------
class Edge{
public:
    int u,v,w;
    Edge* next;

    Edge(int uu,int vv,int ww){
        u = uu;
        v = vv;
        w = ww;
        next = NULL;
    }
};

// -------- Edge Priority Queue (Sorted Linked List) --------
class EdgePQ{
    Edge* start;

public:
    EdgePQ(){
        start = NULL;
    }

    void push(int u,int v,int w){
        Edge* newNode = new Edge(u,v,w);

        if(start == NULL || w < start->w){
            newNode->next = start;
            start = newNode;
            return;
        }

        Edge* temp = start;
        while(temp->next != NULL && temp->next->w <= w){
            temp = temp->next;
        }

        newNode->next = temp->next;
        temp->next = newNode;
    }

    Edge* pop(){
        if(start == NULL) return NULL;

        Edge* temp = start;
        start = start->next;
        return temp;
    }

    bool empty(){
        return start == NULL;
    }
};

// -------- Disjoint Set --------
class DisjointSet{
public:
    int parent[100];
    int rank[100];
};

// find function
int findParent(DisjointSet &ds,int node){

    if(ds.parent[node] == node){
        return node;
    }

    return ds.parent[node] = findParent(ds, ds.parent[node]);
}

// union function
void unionSet(DisjointSet &ds,int u,int v){

    int rootU = findParent(ds,u);
    int rootV = findParent(ds,v);

    if(ds.rank[rootU] < ds.rank[rootV]){
        ds.parent[rootU] = rootV;
    }

    else if(ds.rank[rootV] < ds.rank[rootU]){
        ds.parent[rootV] = rootU;
    }

    else{
        ds.parent[rootV] = rootU;
        ds.rank[rootU]++;
    }
}

// -------- Graph Class --------
class Graph{

    int V;
    EdgePQ pq;

public:

    Graph(int v){
        V = v;
    }

    void addEdge(int u,int v,int w){
        pq.push(u,v,w);
    }

    int kruskalMST(){

        DisjointSet ds;

        for(int i=0;i<V;i++){
            ds.parent[i] = i;
            ds.rank[i] = 0;
        }

        int cost = 0;
        int edgeCount = 0;

        cout<<"Edges in MST:"<<endl;

        while(!pq.empty() && edgeCount < V-1){

            Edge* e = pq.pop();

            int u = e->u;
            int v = e->v;
            int w = e->w;

            int setU = findParent(ds,u);
            int setV = findParent(ds,v);

            if(setU != setV){

                cout<<u<<" - "<<v<<" : "<<w<<endl;

                cost += w;

                unionSet(ds,setU,setV);

                edgeCount++;
            }
        }

        return cost;
    }
};

// -------- Main --------
int main(){

    Graph g(4);

    g.addEdge(0,1,10);
    g.addEdge(0,3,30);
    g.addEdge(0,2,15);
    g.addEdge(1,3,40);
    g.addEdge(2,3,50);

    cout<<"Minimum Cost = "<<g.kruskalMST();

    return 0;
}