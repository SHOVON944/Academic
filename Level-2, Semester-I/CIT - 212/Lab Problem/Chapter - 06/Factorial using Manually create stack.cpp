#include <bits/stdc++.h>
using namespace std;

void PUSH(int STACK[], int &TOP, int MAXSTK, int ITEM){
    if (TOP == MAXSTK - 1){
        cout << "OVERFLOW" << endl;
        return;
    }

    TOP = TOP + 1;
    STACK[TOP] = ITEM;
}

void POP(int STACK[], int &TOP, int &ITEM){
    if (TOP == -1){
        cout << "UNDERFLOW" << endl;
        return;
    }
    ITEM = STACK[TOP];
    TOP = TOP - 1;
}

int factorial(int n, int STACK[], int &TOP){
    if(n == 0)return 1;

    PUSH(STACK, TOP, 100, n);
    int result = factorial(n - 1, STACK, TOP);

    int ITEM;
    POP(STACK, TOP, ITEM);
    return ITEM * result;
}

int main(){
    int n;
    cin>>n;

    int STACK[100];
    int TOP = -1;

    cout<<factorial(n, STACK, TOP);

    return 0;
}
