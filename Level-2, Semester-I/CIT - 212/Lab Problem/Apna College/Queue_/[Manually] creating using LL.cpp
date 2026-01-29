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

class QueUe{
    Node* head;
    Node* tail;
    int count;

public:
    QueUe(){
        head = tail = NULL;
        count = 0;
    }

    void push(int val){
        Node* newNode = new Node(val);

        if(empty()){
            head = tail = newNode;
        } else{
            tail->next = newNode;
            tail = newNode;
        }
        count++;
    }

    void pop(){
        if(empty()){
            return;
        } else{
            Node* temp = head;
            head = head->next;
            delete temp;
        }
        count--;
    }

    int front(){
        if(empty()){
            return -1;
        }

        return head->data;
    }

    int size(){
        return count;
    }

    bool empty(){
        return head==NULL;
    }
};


int main()
{
    QueUe q;
    q.push(1);
    q.push(2);
    q.push(3);

    while(q.empty()){
        cout<<q.front()<<" ";
    }
    cout<<endl;

    return 0;
}