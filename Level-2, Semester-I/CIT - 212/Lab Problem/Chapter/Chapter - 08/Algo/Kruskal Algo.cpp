#include <iostream>
using namespace std;

// -------- Edge Node for Priority Queue --------
class EdgeNode{
public:
    int u;
    int v;
    int weight;
    EdgeNode* link;

    EdgeNode(int uu, int vv, int w){
        u = uu;
        v = vv;
        weight = w;
        link = NULL;
    }
};

// Priority Queue for edges
class EdgePQ{
    EdgeNode* start;

public:
    EdgePQ(){
        start = NULL;
    }

    void push(int u,int v,int w){
        EdgeNode* newNode = new EdgeNode(u,v,w);
        if(start == NULL || w < start->weight){
            newNode->link = start;
            start = newNode;
            return;
        }

        EdgeNode* ptr = start;
        while(ptr->link != NULL && ptr->link->weight <= w){
            ptr = ptr->link;
        }
        newNode->link = ptr->link;
        ptr->link = newNode;
    }

    EdgeNode* pop(){
        if(start == NULL) return NULL;
        EdgeNode* temp = start;
        start = start->link;
        return temp;
    }

    bool empty(){
        return start == NULL;
    }
};

// -------- Disjoint Set (Union-Find) --------
class DisjointSet{
public:
    int parent[100];   // assume max 100 vertices
};

int find(DisjointSet* ds, int i){
    while(ds->parent[i] != i){
        i = ds->parent[i];
    }
    return i;
}

void Union(DisjointSet* ds, int a, int b){
    int rootA = find(ds,a);
    int rootB = find(ds,b);
    ds->parent[rootB] = rootA;
}

// -------- Graph (Adjacency list optional for reference) --------
class Graph{
    int V;

public:
    Graph(int v){ V = v; }

    int kruskalMST(EdgePQ &pq){
        DisjointSet ds;
        for(int i=0;i<V;i++) ds.parent[i] = i;

        int mstCost = 0;
        int edgeCount = 0;

        cout << "Edges in MST:\n";

        while(!pq.empty() && edgeCount < V-1){
            EdgeNode* e = pq.pop();
            int u = e->u;
            int v = e->v;
            int w = e->weight;

            int setU = find(&ds,u);
            int setV = find(&ds,v);

            if(setU != setV){
                cout << u << " - " << v << " : " << w << endl;
                mstCost += w;
                Union(&ds,setU,setV);
                edgeCount++;
            }
        }

        return mstCost;
    }
};

// -------- Main --------
int main(){

    int V = 4;

    EdgePQ pq;

    // Insert edges
    pq.push(0,1,10);
    pq.push(0,3,30);
    pq.push(0,2,15);
    pq.push(1,3,40);
    pq.push(2,3,50);

    Graph g(V);

    int cost = g.kruskalMST(pq);

    cout << "Minimum Cost = " << cost << endl;

    return 0;
}