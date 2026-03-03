#include <bits/stdc++.h>
using namespace std;

void PUSH(int STACK[], int &TOP, int MAXSTK, int ITEM){
    if (TOP == MAXSTK - 1){
        cout << "OVERFLOW" << endl;
        return;
    }
    STACK[++TOP] = ITEM;
}

void POP(int STACK[], int &TOP, int &ITEM){
    if (TOP == -1){
        cout << "UNDERFLOW" << endl;
        return;
    }
    ITEM = STACK[TOP--];
}

int fibonacci(int n, int STACK[], int &TOP){
    if(n == 0) return 0;
    if(n == 1) return 1;

    PUSH(STACK, TOP, 100, n);
    int result = fibonacci(n-1, STACK, TOP) + fibonacci(n-2, STACK, TOP);

    int ITEM;
    POP(STACK, TOP, ITEM);

    return result;
}

int main(){
    int n;
    cin>>n;

    int STACK[100];
    int TOP = -1;

    for(int i=0; i<n; i++){
        cout<<fibonacci(i, STACK, TOP)<< " ";
    }
    cout << endl;

    return 0;
}
