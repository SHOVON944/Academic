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