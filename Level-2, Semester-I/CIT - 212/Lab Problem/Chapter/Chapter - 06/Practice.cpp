#include <iostream>
using namespace std;

void PUSH(int STACK[], int MAXSTK, int &TOP, int ITEM){
    if(TOP == MAXSTK-1){
        cout<<"OVERFLOW"<<endl;
        return;
    }
    TOP = TOP + 1;
    STACK[TOP] = ITEM;
    return;
}

void POP(int STACK[], int &TOP, int &ITEM){
    if(TOP == -1){
        cout<<"UNDERFLOW"<<endl;
        return;
    }

    ITEM = STACK[TOP];
    TOP = TOP - 1;
}

int EvalutePostfix(char p[]){
    int stack[40];
    int top = -1;
    int maxstk = 40;

    char symbol;
    int value, a, b;
    int num;
    int i = 0;

    while(p[i] != '\0'){
        i++;
    }
    p[i] = ')';
    p[i+1] = '\0';

    i = 0;
    while((symbol=p[i]) != ')'){
        if(symbol>='0'  && symbol<='9'){
            num = 0;
            while(p[i]>='0'  && p[i]<='9'){
                num = num*10 + (p[i] - '0');
                i++;
            }
            PUSH(stack, maxstk, top, num);
        } else if(p[i]==' '){
            i++;
        } else{
            POP(stack, top, a);
            POP(stack, top, b);

            if(p[i]=='+') value = b + a;
            if(p[i]=='-') value = b - a;
            if(p[i]=='*') value = b * a;
            if(p[i]=='/') value = b / a;

            PUSH(stack, maxstk, top, value);
            i++;
        }
    }

    POP(stack, top, value);
    return value;
}

int main()
{
    char P[100];
    cin.getline(P, 100);
    cout<<EvalutePostfix(P)<<endl;


    return 0;
}
