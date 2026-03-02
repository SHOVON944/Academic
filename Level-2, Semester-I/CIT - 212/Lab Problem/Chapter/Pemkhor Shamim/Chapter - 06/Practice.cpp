#include <iostream>
using namespace std;

int stn[100];
char stbeg[100], staux[100], stend[100];
int top = -1; 

void PUSH(int n, char beg, char aux, char end){
    top = top + 1;
    stn[top] = n;
    stbeg[top] = beg;
    staux[top] = aux;
    stend[top] = end;
}

void POP(int &n, char &beg, char &aux, char &end){
    n = stn[top];
    beg = stbeg[top];
    aux = staux[top];
    end = stend[top];
    top = top - 1;
}

void TOWER(int n, char beg, char aux, char end){
    PUSH(n, beg, aux, end);
    if(n==1){
        cout<<beg<<" -> "<<end<<endl;
        POP(n, beg, aux, end);
        return;
    }
    TOWER(n-1, beg, end, aux);
    cout<<beg<<" -> "<<end<<endl;
    TOWER(n-1, aux, beg, end);
    POP(n, beg, aux, end);
}

int main()
{
    int n;
    cin>>n;
    TOWER(n, 'a', 'b', 'c');

    return 0;
}
