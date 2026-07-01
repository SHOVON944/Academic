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

    cout << "Enter the number of elements (N): ";
    cin>>N;

    cout << "Enter " << N << " elements of the array: ";
    for(int i=1; i<=N; i++) cin >> LA[i];

    cout << "Enter the position (K) of the element you want to delete: ";
    cin >> K;

    DELETE(LA, N, K, ITEM);

    cout << "\nThe deleted value is: " << ITEM << endl;
    cout << "After deletion, the array is:\n";
    for(int i=1; i<=N;i++){
        cout<<LA[i]<<" ";
    }
    cout << endl;

    return 0;
}