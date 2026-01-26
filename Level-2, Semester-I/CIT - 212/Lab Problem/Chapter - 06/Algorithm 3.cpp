#include<iostream>
using namespace std;

void PUSH(int STACK[], int &TOP, int MAXSTK, int ITEM){
    if(TOP == MAXSTK-1){
        cout<<"OVERFLOW"<<endl;
        return;
    }

    TOP = TOP + 1;
    STACK[TOP] = ITEM;
}

void POP(int STACK[], int &TOP, int &ITEM){
    if(TOP == -1){
        cout<<"UNDERFLOW"<<endl;
        return;
    }

    ITEM = STACK[TOP];
    TOP = TOP - 1;
}


int EvalutePostfix(char P[]){
    int STACK[50];
    int TOP = -1;
    int MAXSTK = 50;

    char symbol;
    int A, B, VALUE;
    int i = 0;
    int num;

    // step - 1 [add ')']
    while(P[i] != '\0'){
        i++;
    }
    P[i] = ')';
    P[i+1] = '\0';
    i = 0;

    // step - 2
    while((symbol=P[i]) != ')'){
        if(symbol>='0'  &&  symbol<='9'){   // step - 3
            num = 0;
            while(P[i]>='0'  &&  P[i]<='9'){
                num = num*10 + (P[i] - '0');
                i++;
            }
            PUSH(STACK, TOP, MAXSTK, num);
        } else if(P[i] == ' '){
            i++;
        } else{     // step - 4 [for operator operation]
            POP(STACK, TOP, A);
            POP(STACK, TOP, B);

            if(P[i] == '+') VALUE = B + A;
            else if(P[i] == '-') VALUE = B - A;
            else if(P[i] == '*') VALUE = B * A;
            else if(P[i] == '/') VALUE = B / A;

            PUSH(STACK, TOP, MAXSTK, VALUE);
            i++;
        }
    }

    // step - 5
    POP(STACK, TOP, VALUE);
    return VALUE;
}


int main()
{
    char P[100];
    cout<<"Enter postfix expression: ";
    cin.getline(P, 100);
    cout<<EvalutePostfix(P);

    return 0;
}