/*
*Linear array LA with N elements, position K, element ITEM.
*Inserts: ITEM into Kth position.
*[Initialize counter.] Set J := N.
*Repeat Steps 3 and 4 while J ≥ K.
*[Move Jth element downward.] Set LA[J + 1] := LA[J].
*[Decrease counter.] Set J := J - 1.
*[End of Step 2 loop.]
*[Insert element.] Set LA[K] := ITEM.
*[Reset N.] Set N := N + 1.
*Exit.
*/

#include <iostream>
using namespace std;

int main()
{
    int LA[100];
    int N, K, ITEM;
    int J;
    cin>>N;
    for(int i = 1; i <= N; i++) cin>>LA[i];

    cin>>K;
    cin>>ITEM;
    J = N;
    while(J>=K){
        LA[J + 1] = LA[J];
        J = J - 1;
    }

    LA[K] = ITEM;
    N = N + 1;

    for(int i = 1; i <= N; i++){
        cout<<LA[i]<<" ";
    }

    return 0;
}
