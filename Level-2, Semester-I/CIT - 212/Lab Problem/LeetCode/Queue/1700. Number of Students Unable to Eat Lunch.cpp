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

class Queue{     // not access modifier declire. Means, it is private class
    Node* head;
    Node* tail;
    int q_size;

public:
    Queue(){
        head = tail = NULL;
        q_size = 0;
    }

    void push(int val){
        Node* newNode = new Node(val);      // dynamic

        if(empty()){
            head = tail = newNode;
        } else{
            tail->next = newNode;
            tail = newNode;
        }
        q_size++;
    }

    void pop(){
        if(empty()){
            cout<<"LL is empty"<<endl;
            return;
        } else{
            Node* temp = head;
            head = head->next;
            delete temp;
        }
        q_size--;
    }

    int front(){
        if(empty()){
            cout<<"LL is empty"<<endl;
            return -1;
        }
        return head->data;
    }

    bool empty(){
        return head==NULL;
    }

    int size(){
        return q_size;
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

class Solution {
public:
    int countStudents(vector<int>& students, vector<int>& sandwiches) {
        Queue q;
        Stack st;
        for(int i=students.size()-1; i>=0; i--) q.push(students[i]);
        for(int i=sandwiches.size()-1; i>=0; i--) st.push(sandwiches[i]);
        int checker_cycle = 0;
        while(1){
            if(q.front() == st.top()){
                q.pop();
                st.pop();
                checker_cycle = 0;
            } else{
                int res = q.front();
                q.pop();
                q.push(res);
                checker_cycle++;
            }
            if(checker_cycle == q.size()){
                return q.size();
            }
        }
    }
};


int main()
{


    return 0;
}