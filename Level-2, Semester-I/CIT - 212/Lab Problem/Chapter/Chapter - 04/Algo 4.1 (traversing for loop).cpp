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

    cout << "Enter the lower bound (LB): ";
    cin >> LB;
    cout << "Enter the upper bound (UB): ";
    cin >> UB;

    cout << "Enter " << (UB - LB + 1) << " elements of the array: ";
    for(int i=LB; i<=UB; i++){
        cin>>LA[i];
    }

    cout << "\nThe array elements are:\n";
    for(int L=LB; L<=UB; L++){
        cout << "LA[" << L << "] = " << LA[L] << endl;
    }

    return 0;
}