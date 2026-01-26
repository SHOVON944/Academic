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

public:
    Queue(){
        head = tail = NULL;
    }

    void push(int val){
        Node* newNode = new Node(val);      // dynamic

        if(empty()){
            head = tail = newNode;
        } else{
            tail->next = newNode;
            tail = newNode;
        }
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

};


int main()
{
    Queue q;
    q.push(1);
    q.push(2);
    q.push(3);

    while(q.empty()){
        cout<<q.front()<<" ";
    }
    cout<<endl;

    return 0;
}