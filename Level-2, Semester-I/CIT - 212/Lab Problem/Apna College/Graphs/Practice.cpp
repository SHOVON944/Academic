/*
███████╗██╗  ██╗ ██████╗ ██╗   ██╗ ██████╗ ███╗   ██╗
██╔════╝██║  ██║██╔═══██╗██║   ██║██╔═══██╗████╗  ██║
███████╗███████║██║   ██║██║   ██║██║   ██║██╔██╗ ██║
╚════██║██╔══██║██║   ██║╚██╗ ██╔╝██║   ██║██║╚██╗██║
███████║██║  ██║╚██████╔╝ ╚████╔╝ ╚██████╔╝██║ ╚████║
╚══════╝╚═╝  ╚═╝ ╚═════╝   ╚═══╝   ╚═════╝ ╚═╝  ╚═══╝
*/

#include <iostream>
#include <vector>
#include <list>
#include <queue>
#include <climits>
using namespace std;

class Edge {
public:
    int v;  // destination vertex
    int wt; // weight of the edge
    
    // Constructor
    Edge(int v, int wt) {
        this->v = v;
        this->wt = wt;
    }
};

void dijkstra(int src, vector<vector<Edge>>g, int V){
    vector<int> dist(V, INT_MAX);
    dist[src] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>> >pq;
    // <dist[u], v>

    pq.push({0, src});
    while(pq.size()>0){
        int u = pq.top().second;
        pq.pop();

        for(Edge e : g[u]){     // edge relaxiton
            if(dist[e.v] >  dist[u] + e.wt){
                dist[e.v] =  dist[u] + e.wt;
                pq.push({dist[e.v], e.v});
            }
        }
    }

    for(int i=0; i<V; i++){
        cout<<dist[i]<<" ";
    }
    cout<<endl;
}



int main() {
    int V = 6; // Number of vertices
    vector<vector<Edge>> g(V);
    
    // Building the graph (undirected weighted graph)
    // Format: g[u].push_back(Edge(v, weight));
    
    g[0].push_back(Edge(1, 2));
    g[0].push_back(Edge(2, 4));
    
    g[1].push_back(Edge(0, 2)); // Add reverse edge for undirected graph
    g[1].push_back(Edge(2, 1));
    g[1].push_back(Edge(3, 7));
    
    g[2].push_back(Edge(0, 4)); // Add reverse edge
    g[2].push_back(Edge(1, 1));
    g[2].push_back(Edge(4, 3));
    
    g[3].push_back(Edge(1, 7));
    g[3].push_back(Edge(5, 1));
    
    g[4].push_back(Edge(2, 3));
    g[4].push_back(Edge(3, 2));
    g[4].push_back(Edge(5, 5));
    
    g[5].push_back(Edge(3, 1)); // Add reverse edge
    g[5].push_back(Edge(4, 5));
    
    // Run Dijkstra's algorithm from source vertex 0
    cout << "Running Dijkstra's Algorithm from source vertex 0:" << endl;
    dijkstra(0, g, V);
    
    return 0;
}