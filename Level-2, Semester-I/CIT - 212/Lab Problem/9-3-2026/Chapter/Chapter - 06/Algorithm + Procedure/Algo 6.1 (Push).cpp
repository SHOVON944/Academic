#include <bits/stdc++.h>
using namespace std;


void PUSH(int STACK[], int &TOP, int MAXSTK, int ITEM){
    if (TOP == MAXSTK - 1){
        cout << "OVERFLOW" << endl;
        return;
    }

    TOP = TOP + 1;
    STACK[TOP] = ITEM;
    return;
}

int main()
{
    int STACK[8];
    int TOP = -1;
    int ITEM;
    cout<<"Enter item to push: ";
    cin>>ITEM;
    PUSH(STACK, TOP, 8, ITEM);

    cout<<"Top Item is: "<<STACK[TOP];

    return 0;
}
