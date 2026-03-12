#include<iostream>
#include<algorithm>
using namespace std;

# define INFINITE 1000000000

int main()
{
    int M;
    cout<<"Enter number of vertices: ";
    cin>>M;
    int W[M][M];
    int Q[M][M];

    cout<<"Enter Adjacency Matrix: "<<endl;
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            cin>>W[i][j];
        }
    }

    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            // if(i==j){    //! ei if ta dile tkn diagonal distance always 0 dia inialize hoccilo..loop er nice r jassilo na...
            //     Q[i][j] = 0;
            // } else
            if(W[i][j]==0){
                Q[i][j] = INFINITE;
            } else{
                Q[i][j] = W[i][j];
            }
        }
    }

    for(int k=0; k<M; k++){
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                Q[i][j] = min(Q[i][j], Q[i][k] + Q[k][j]);  //TODO  or, manually korbo
            }
        }
    }

    cout<<endl;
    cout<<"Shortest Path Matrix: "<<endl;
    for(int i=0; i<M; i++){
        for(int j=0; j<M; j++){
            if(Q[i][j] == INFINITE){
                cout<<"∞ ";     //!   ∞-> eita ms word theke anbo
            } else{
                cout<<Q[i][j]<<" ";
            }
        }
        cout<<endl;
    }

    return 0;
}