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

class CircularList{
    Node* head;
    Node* tail;

public:
    CircularList(){
        head = tail = NULL;
    }

    void insertAtHead(int val){
        Node* newNode = new Node(val);

        if(head==NULL){         // if(tail==NULL)
            head = tail = newNode;
            tail->next = head;
        } else{
            newNode->next = head;       // newNode->next = tail->next
            head = newNode;             // tail->next = newNode
            tail->next = head;
        }
    }

     void printLL(){
        if(head==NULL){
            cout<<"CLL is empty"<<endl;
            return;
        }
        cout<<head->data<<" -> ";
        Node* temp = head->next;
        while(temp!=head){      // onno gulai if(temp!=NULL)
            cout<<temp->data<<" -> ";
            temp = temp->next;
        }
        cout<<temp->data<<endl;       //cout<<head->data<<" -> ";
    }

};

int main()
{
    CircularList cll;

    cll.insertAtHead(1);
    cll.insertAtHead(2);
    cll.insertAtHead(3);

    cll.printLL();

    return 0;
}