/*
*Algorithm 4.3 — Deleting from a Linear Array
*Given: Linear array LA with N elements, position K.
*Deletes: Kth element, assigns to ITEM.
* 1. Set ITEM := LA[K].
* 2. Repeat for J = K to N - 1:
*       [Move J + 1st element upward.] Set LA[J] := LA[J + 1].
*    [End of loop.]
* 3. [Reset N.] Set N := N - 1.
* 4. Exit.
*/

#include <iostream>
using namespace std;

void DELETE(int LA[], int &N, int K, int &ITEM){
    ITEM = LA[K];
    for (int J=K; J<=N-1;J++){
        LA[J] = LA[J + 1];
    }
    N = N - 1;
}

int main()
{
    int LA[100];
    int N, K, ITEM;
    cin>>N;
    for(int i=1; i<=N; i++) cin >> LA[i];

    cin >> K;
    DELETE(LA, N, K, ITEM);
    cout<<ITEM<<endl;
    for(int i=1; i<=N;i++){
        cout<<LA[i]<<" ";
    }

    return 0;
}
