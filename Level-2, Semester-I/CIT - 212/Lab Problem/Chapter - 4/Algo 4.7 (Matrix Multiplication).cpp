/*
Algorithm 4.7: (Matrix Multiplication) MATMUL(A, B, C, M, P, N)
Let A be an M×PM×P matrix array, and let B be a P×NP×N matrix array. This algorithm stores the product of A and B in an M×NM×N matrix array C.
1.	Repeat Steps 2 to 4 for I=1I=1 to MM:
2.	     Repeat Steps 3 and 4 for J=1J=1 to NN:
3.	          Set C[I,J]:=0C[I,J]:=0. [Initializes C[I, J].]
4.	          Repeat for K=1K=1 to PP:
              C[I,J]:=C[I,J]+A[I,K]∗B[K,J]C[I,J]:=C[I,J]+A[I,K]∗B[K,J]
        [End of inner loop.]
  [End of Step 2 middle loop.]
[End of Step 1 outer loop.]
5.	Exit.

*/

#include <iostream>
using namespace std;

void MATMUL(int A[100][100], int B[100][100], int C[100][100], int M, int P, int N){
    for(int I=1; I<=M; I++){
        for(int J=1; J<=N; J++){
            C[I][J] = 0;
            for(int K=1; K<=P; K++){
                C[I][J] = C[I][J] + A[I][K] * B[K][J];
            }
        }
    }
}

int main()
{
    int A[100][100], B[100][100], C[100][100];
    int M, P, N;

    cin>>M>>P;
    cin>>N;

    for(int I=1; I<=M; I++){
        for(int K=1; K<=P; K++){
            cin>>A[I][K];
        }
    }

    for(int K=1; K<=P; K++){
        for(int J=1; J<=N; J++){
            cin>>B[K][J];
        }
    }

    MATMUL(A, B, C, M, P, N);

    for(int I=1; I<=M; I++){
        for(int J=1; J<=N; J++){
            cout<<C[I][J]<<" ";
        }
        cout<<endl;
    }

    return 0;
}