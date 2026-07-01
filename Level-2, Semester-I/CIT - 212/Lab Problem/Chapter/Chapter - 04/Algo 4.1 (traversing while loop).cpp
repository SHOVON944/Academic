/*
Algorithm 4.1: Traversing a Linear Array) Here LA is a linear array with lower bound LB and upper bound UB. This algorithm traverses LA applying an operation PROCESS to each element of LA.
1. [Initialize counter.] Set K: = LB.
2. Repeat Steps 3 and 4 while K := B.
3.	 [Visit element.] Apply PROCESS to LA[K].
4. 	 [Increase counter.] Set K := K + 1.
    [End of Step 2 loop.]
5. Exit.

*/
#include <iostream>
using namespace std;

int main()
{
    int LA[100];
    int LB, UB;

    cout << "Enter the lower bound (LB): ";
    cin >> LB;
    cout << "Enter the upper bound (UB): ";
    cin >> UB;

    cout << "Enter " << (UB - LB + 1) << " elements of the array: ";
    for(int i=LB; i<=UB; i++){
        cin>>LA[i];
    }

    int K = LB;
    cout << "\nThe array elements are: ";
    while(K<=UB){
        cout<<LA[K]<<" ";
        K = K + 1;
    }
    cout << endl;

    return 0;
}