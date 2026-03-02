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

void POP(int STACK[], int &TOP, int &ITEM){
    if (TOP == -1){
        cout << "UNDERFLOW" << endl;
        return;
    }

    ITEM = STACK[TOP];
    TOP = TOP - 1;
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
    POP(STACK, TOP, ITEM);

    cout<<"Pop Item is: "<<ITEM;

    return 0;
}