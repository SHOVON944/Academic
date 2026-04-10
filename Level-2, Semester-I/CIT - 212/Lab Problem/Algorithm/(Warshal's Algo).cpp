#include<iostream>
using namespace std;

int main()
{
    int M;
    cout<<"Enter number of vertices: ";
    cin>>M;
    int A[M][M];
    int P[M][M];

    cout<<"Enter Adjacency Matrix: "<<endl;
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            cin>>A[i][j];
        }
    }

    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            if(A[i][j]==0){
                P[i][j] = 0;
            } else{
                P[i][j] = 1;
            }
        }
    }

    for(int k=0; k<M; k++){
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                P[i][j] = P[i][j] ||  (P[i][k] && P[k][j]);
            }
        }
    }

    cout<<endl;
    cout<<"Path Matrix (transitive Closure): "<<endl;
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            cout<<P[i][j]<<" ";
        }
        cout<<endl;
    }

    return 0;
}