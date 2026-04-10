#include<iostream>
using namespace std;

class Graph{
    int V;
    int** dist;

public:
    Graph(int v){
        V = v;

        dist = new int*[V];
        for(int i=0;i<V;i++){
            dist[i] = new int[V];
        }
    }

    void inputMatrix(){
        cout<<"Enter Adjacency Matrix (-1 for no edge):"<<endl;
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                cin>>dist[i][j];
            }
        }

        // Convert -1 to INF and set diagonal 0
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(dist[i][j] == -1){
                    dist[i][j] = 999999;
                }
                if(i == j){
                    dist[i][j] = 0;
                }
            }
        }
    }

    //* -------- Floyd Warshall Algorithm
    void floydWarshall(){
        for(int k=0;k<V;k++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    void printResult(){
        cout<<"\nShortest Distance Matrix:\n";
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(dist[i][j] == 999999){
                    cout<<"-1 ";
                } else{
                    cout<<dist[i][j]<<" ";
                }
            }
            cout<<endl;
        }
    }
};

int main()
{
    int V;
    cout<<"Enter number of vertices: ";
    cin>>V;

    Graph g(V);

    g.inputMatrix();

    g.floydWarshall();

    g.printResult();

    return 0;
}

/*
*Input:
    Enter number of vertices: 3

    Enter Adjacency Matrix (-1 for no edge):
     0  1  4
    -1  0  2
    -1 -1  0

*Output:
    Shortest Distance Matrix:
     0  1  3
    -1  0  2
    -1 -1  0
*/