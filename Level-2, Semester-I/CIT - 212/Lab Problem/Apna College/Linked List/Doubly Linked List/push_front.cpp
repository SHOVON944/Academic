#include<bits/stdc++.h>
using namespace std;

class Node{
public:
    int data;
    Node* next;
    Node* prev;

    Node(int val){
        data = val;
        next = NULL;
        prev = NULL;
    }
};

class DoublyList{
    Node* head;
    Node* tail;

public:
    DoublyList(){
        head = tail = NULL;
    }

    void push_front(int val){
        Node* newNode = new Node(val);

        if(head==NULL){
            head = tail = newNode;
        } else{
            newNode->next = head;
            head->prev = newNode;
            head = newNode;
        }
    }

    void push_back(int val){
        Node* newNode = new Node(val);

        if(head==NULL){
            head = tail = newNode;
        } else{
            newNode->prev = tail;
            tail->next = newNode;
            tail = newNode;
        }
    }

    void print(){
        Node* temp = head;
        while(temp!=NULL){
            cout<<temp->data<<" <=> ";
            temp = temp->next;
        }
        cout<<"NULL"<<endl;
    }

};

int main()
{
    DoublyList dll;
    dll.push_front(1);
    dll.push_front(2);
    dll.push_front(3);

    dll.push_back(5);

    dll.print();

    return 0;
}