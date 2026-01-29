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

class List{     // not access modifier declire. Means, it is private class
    Node* head;
    Node* tail;

public:
    List(){
        head = tail = NULL;
    }

    void push_front(int val){
        Node* newNode = new Node(val);      // dynamic
        if(head == NULL){
            head = tail = newNode;
        } else{
            newNode->next = head;
            head = newNode;
        }
    }

    void push_back(int val){
        Node* newNode = new Node(val);
        if(head == NULL){
            head = tail = newNode;
        } else{
            tail->next = newNode;
            tail = newNode;
        }
    }

    void pop_front(){
        if(head==NULL){
            cout<<"LL is empty"<<endl;
            return;
        }
        Node* temp = head;
        head = head->next;
        temp->next = NULL;

        delete temp;
    }

    void pop_back(){    // while(temp->next->next!=NULL), eita empty(), 1 value er jnno work korbe na...2/2+ value er jnno work korbe
        if(head==NULL){
            cout<<"LL is empty"<<endl;
            return;
        }
        Node* temp = head;
        while(temp->next!=tail){
            temp = temp->next;
        }
        temp->next = NULL;
        delete tail;
        tail = temp;
    }

    void printLL(){
        Node* temp = head;
        while(temp!=NULL){
            cout<<temp->data<<" -> ";
            temp = temp->next;
        }
        cout<<" NULL"<<endl;
    }

};


int main()
{
    List ll;

    ll.push_front(1);
    ll.push_front(2);
    ll.push_front(3);

    ll.push_back(4);

    ll.pop_front();

    ll.pop_back();

    ll.printLL();

    return 0;
}