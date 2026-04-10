#include<iostream>
using namespace std;

int main()
{
    int M;
    cout<<"Enter number of vertices: ";
    cin>>M;

    int A[M][M];

    cout<<"Enter Adjacency Matrix (-1 for no edge): "<<endl;
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            cin>>A[i][j];
        }
    }

    // Step 1: Replace -1 with INF and set diagonal = 0
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            if(A[i][j] == -1){
                A[i][j] = 1e9; // INF
            }
            if(i == j){
                A[i][j] = 0;
            }
        }
    }

    // Step 2: Floyd-Warshall Algorithm
    for(int k=0; k<M; k++){
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                if((A[i][k] + A[k][j])   <   A[i][j]){  // min
                    A[i][j] = A[i][k] + A[k][j];
                }
            }
        }
    }

    // Step 3: Replace INF back to -1
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            if(A[i][j] == 1e9){
                A[i][j] = -1;
            }
        }
    }

    cout<<"\nShortest Distance Matrix:\n";
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            cout<<A[i][j]<<" ";
        }
        cout<<endl;
    }

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