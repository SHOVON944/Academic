#include<bits/stdc++.h>
#include<stack>
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
    stack<char> STACK;
    string P;

    //  step 1: Push '(' onto STACK and add ')' to end of Q
    STACK.push('(');
    Q = Q + ')';

    //  step 2: scan Q [left -> right]
    for(int i=0; i<Q.length(); i++){
        char ch = Q[i];

        if(isALphabateDigit(ch)){    // Step 3: If operand -> add to P
            P = P + ch;
        } else if(ch=='('){     // Step 4: If left parenthesis, push into STACK
            STACK.push(ch);
        } else if(ch=='*'   ||   ch=='/'   ||   ch=='^'   ||   ch=='+'   ||   ch=='-'){ // Step 5: If operator encountered
            while(precedence(STACK.top()) > precedence(ch)   ||
                 (precedence(STACK.top()) == precedence(ch) && ch != '^')){   // (a) Pop operators of higher or equal precedence
                P = P + STACK.top();
                STACK.pop();
            }
            STACK.push(ch);    // (b) Push operator into STACK
        } else if(ch==')'){  // Step 6: If right parenthesis
            while(STACK.top()!='('){  // (a) Pop until left parenthesis
                P = P + STACK.top();
                STACK.pop();
            }
            STACK.pop();    // (b) Remove left parenthesis
        }
    }
    cout<<"Postfix Expression: "<<P<<endl;
}


int main()
{
    string Q;
    cout<<"Enter infix expression: ";
    cin>>Q;

    POLISH(Q);

    return 0;
}