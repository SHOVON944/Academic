#include<iostream>
#include<string>
using namespace std;

bool isALphabateDigit(char ch){
    if(ch>='A'  &&  'Z'>=ch) return true;
    if(ch>='a'  &&  'z'>=ch) return true;
    if(ch>='0'  &&  '9'>=ch) return true;
    return false;
}

int precedence(char x){
    if(x=='+' || x=='-') return 1;
    if(x=='*' || x=='/') return 2;
    if(x=='^') return 3;
    return 0;
}

void POLISH(string Q){
    char st[100];
    int top = -1;
    string P = "";

    st[++top] = '(';
    Q = Q + ')';

    for(int i=0; i<Q.length(); i++){
        char ch = Q[i];

        if(isALphabateDigit(ch)){
            P = P + ch;
        } else if(ch=='('){
            st[++top] = ch;
        } else if(ch=='*'   ||   ch=='/'   ||   ch=='^'   ||   ch=='+'   ||   ch=='-'){
            while(precedence(st[top]) > precedence(ch)   ||
                (precedence(st[top]) == precedence(ch) && ch != '^')){
                P = P + st[top--];
            }
            st[++top] = ch;
        } else if(ch==')'){
            while(st[top] != '('){
                P = P + st[top--];
            }
            top--;
        }
    }
    cout<<"Postfix Expression: "<<P<<endl;
}


int main()
{
    string Q;
    cout<<"Enter infix expression: ";
    getline(cin, Q);

    POLISH(Q);

    return 0;
}