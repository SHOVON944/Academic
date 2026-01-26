#include<bits/stdc++.h>
using namespace std;

class Node{
public:
    int data;
    Node* next;

    Node(int val){
        data = val;
        next = NULL;
    }
};

class Stack{
    Node* topNode;   // stack er top

public:
    Stack(){
        topNode = NULL;
    }

    // PUSH operation
    void push(int val){
        Node* newNode = new Node(val);

        newNode->next = topNode;
        topNode = newNode;
    }

    // POP operation
    void pop(){
        if(empty()){
            cout << "Stack is empty" << endl;
            return;
        }

        Node* temp = topNode;
        topNode = topNode->next;
        delete temp;
    }

    // TOP element
    int top(){
        if(empty()){
            cout << "Stack is empty" << endl;
            return -1;
        }
        return topNode->data;
    }

    // EMPTY check
    bool empty(){
        return topNode == NULL;
    }
};

int main()
{
    Stack st;

    st.push(1);
    st.push(2);
    st.push(3);

    while(!st.empty()){
        cout << st.top() << " ";
        st.pop();
    }
    cout << endl;

    return 0;
}
