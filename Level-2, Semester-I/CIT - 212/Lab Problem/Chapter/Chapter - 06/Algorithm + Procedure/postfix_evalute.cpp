#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;

void PUSH(double STACK[], int &TOP, int MAXSTK, double ITEM){
    if(TOP == MAXSTK-1){
        cout << "OVERFLOW" << endl;
        return;
    }
    TOP = TOP + 1;
    STACK[TOP] = ITEM;
}

void POP(double STACK[], int &TOP, double &ITEM){
    if(TOP == -1){
        cout << "UNDERFLOW" << endl;
        return;
    }
    ITEM = STACK[TOP];
    TOP = TOP - 1;
}

int precedence(char ch){
    if(ch=='+' || ch=='-') return 1;
    if(ch=='*' || ch=='/') return 2;
    if(ch=='^') return 3;
    return 0;
}

bool isAlphaNumeric(char ch){
    if(ch>='0' && ch<='9') return true;
    if(ch>='A' && ch<='Z') return true;
    if(ch>='a' && ch<='z') return true;
    return false;
}

void InfixToPostfix(char infix[], char postfix[]){
    char STACK[100];
    int TOP = -1;
    int MAX = 100;

    int i=0, j=0;
    char ch;
    char item;

    STACK[++TOP] = '(';
    strcat(infix, ")");

    while((ch = infix[i]) != '\0'){
        if(ch>='0' && ch<='9'){
            while(infix[i]>='0' && infix[i]<='9'){
                postfix[j++] = infix[i];
                i++;
            }
            postfix[j++] = ' ';
            continue;
        }
        else if(isAlphaNumeric(ch)){
            postfix[j++] = ch;
            postfix[j++] = ' ';
        }
        else if(ch == '('){
            STACK[++TOP] = ch;
        }
        else if(ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='^'){
            while(TOP != -1 && STACK[TOP] != '(' &&
                  (precedence(STACK[TOP]) > precedence(ch) ||
                  (precedence(STACK[TOP]) == precedence(ch) && ch != '^'))){
                postfix[j++] = STACK[TOP--];
                postfix[j++] = ' ';
            }
            STACK[++TOP] = ch;
        }
        else if(ch == ')'){
            while(TOP != -1 && STACK[TOP] != '('){
                postfix[j++] = STACK[TOP--];
                postfix[j++] = ' ';
            }
            TOP--; // remove '('
        }
        i++;
    }
    postfix[j] = '\0';
}

/* ----- Evaluate Postfix (floating point result) ----- */
double EvaluatePostfix(char postfix[]){
    double STACK[100];
    int TOP = -1;
    int MAX = 100;

    int i=0;
    char ch;
    double A, B, VALUE;

    while((ch = postfix[i]) != '\0'){
        if(ch==' '){
            i++;
        }
        else if(ch>='0' && ch<='9'){
            double num = 0;
            while(postfix[i]>='0' && postfix[i]<='9'){
                num = num*10 + (postfix[i]-'0');
                i++;
            }
            PUSH(STACK, TOP, MAX, num);
        }
        else if(isAlphaNumeric(ch)){
            i++;
        }
        else{
            POP(STACK, TOP, A);
            POP(STACK, TOP, B);

            if(ch=='+') VALUE = B + A;
            else if(ch=='-') VALUE = B - A;
            else if(ch=='*') VALUE = B * A;
            else if(ch=='/') VALUE = B / A;          // double / double = double
            else if(ch=='^') VALUE = pow(B, A);

            PUSH(STACK, TOP, MAX, VALUE);
            i++;
        }
    }

    POP(STACK, TOP, VALUE);
    return VALUE;
}

/* ----- MAIN ----- */
int main(){
    char infix[100], postfix[100];

    cout << "Enter the infix expression: ";
    cin >> infix;

    InfixToPostfix(infix, postfix);

    cout << "The postfix expression is: " << postfix << endl;

    double result = EvaluatePostfix(postfix);

    cout << "The evaluated result (numeric only) is: " << result << endl;

    return 0;
}