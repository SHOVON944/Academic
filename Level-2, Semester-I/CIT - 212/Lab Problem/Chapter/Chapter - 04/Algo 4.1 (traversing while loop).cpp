/*
Algorithm 4.1: (Traversing a Linear Array) This algorithm traverses a linear array LA with lower bound LB and upper bound UB.
1.	Repeat for K = LB to UB: Apply PROCESS to LA[K]. [End of loop.]
2.	Exit.

*/

#include <iostream>
using namespace std;

int main()
{
    int LA[100];
    int LB, UB;
    cin>>LB;
    cin>>UB;
    for(int i=LB; i<=UB; i++){
        cin>>LA[i];
    }

    int K = LB;
    while(K<=UB){
        cout<<LA[K]<<" ";
        K = K + 1;
    }

    return 0;
}
