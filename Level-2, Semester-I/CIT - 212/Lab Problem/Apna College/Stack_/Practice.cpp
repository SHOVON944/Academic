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
    Node* topNode;       // main stack
    Node* minTopNode;    // min stack

public:
    Stack(){
        topNode = NULL;
        minTopNode = NULL;
    }

    bool empty(){
        return topNode == NULL;
    }

    // PUSH operation
    void push(int val){
        // Push to main stack
        Node* newNode = new Node(val);
        newNode->next = topNode;
        topNode = newNode;

        // Push to min stack if needed
        if(minTopNode == NULL || val <= minTopNode->data){
            Node* minNode = new Node(val);
            minNode->next = minTopNode;
            minTopNode = minNode;
        }
    }

    // POP operation
    void pop(){
        if(empty()){
            cout << "Stack is empty" << endl;
            return;
        }

        int removed = topNode->data;

        Node* temp = topNode;
        topNode = topNode->next;
        delete temp;

        // If removed element was minimum, pop from min stack too
        if(minTopNode != NULL && removed == minTopNode->data){
            Node* minTemp = minTopNode;
            minTopNode = minTopNode->next;
            delete minTemp;
        }
    }

    // TOP element
    int top(){
        if(empty()){
            cout << "Stack is empty" << endl;
            return -1;
        }
        return topNode->data;
    }

    // GET MINIMUM
    int getMin(){
        if(minTopNode == NULL){
            cout << "Stack is empty" << endl;
            return -1;
        }
        return minTopNode->data;
    }
};

int main()
{
    Stack st;

    st.push(5);
    st.push(2);
    st.push(10);
    st.push(1);

    cout << "Current Min: " << st.getMin() << endl; // 1

    st.pop();
    cout << "Current Min after pop: " << st.getMin() << endl; // 2

    cout << "Stack elements: ";
    while(!st.empty()){
        cout << st.top() << " ";
        st.pop();
    }
    cout << endl;

    return 0;
}
