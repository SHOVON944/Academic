#include<iostream>
#include<vector>
#include<list>
#include<queue>
using namespace std;


int primMST(int V, vector<vector<pair<int, int>>> &adj){
    vector<bool> inMST(V, false);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>pq;
    int mstCost = 0;
    pq.push({0, 0}); // {wt, vertex}
    while(pq.size()>0){
        auto p = pq.top();
        int wt = p.first;
        int u = p.second;
        pq.pop();

        if(!inMST[u]){
            inMST[u] = true;
            mstCost += wt;

            for(int i=0; i<adj[u].size(); i++){
                int v = adj[u][i].first;
                int w = adj[u][i].second;

                pq.push({w, v});
            }
        }
    }

    return mstCost;
}


int main()
{
    int V = 4;
    vector<vector<pair<int, int>>> adj(V);

    // Undirected weighted graph
    adj[0].push_back({1, 10}); //V, wt
    adj[1].push_back({0, 10}); //U, wt

    adj[0].push_back({3, 30});
    adj[3].push_back({0, 30});

    adj[0].push_back({2, 15});
    adj[2].push_back({0, 15});

    adj[1].push_back({3, 40});
    adj[3].push_back({1, 40});

    adj[2].push_back({3, 50});
    adj[3].push_back({2, 50});

    cout << "Minimum cost of MST = " << primMST(V, adj) << endl;

    return 0;
}