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

    void insertAtTail(int val){
        Node* newNode = new Node(val);

        if(head==NULL){         // if(tail==NULL)
            head = tail = newNode;
            tail->next = head;
        } else{
            newNode->next = head;
            tail->next = newNode;
            tail = newNode;
        }
    }

    void deleteAtHead(){
        if(head==NULL){
            cout<<"LL is empty"<<endl;
            return;
        } else if(head==tail){      // single node
            delete head;
            head = tail = NULL;
        } else{
            Node* temp = head;
            head = head->next;
            tail->next = head;
            temp->next = NULL;

            delete temp;
        }
    }

    void deleteAtTail(){
        if(head==NULL){
            cout<<"LL is empty"<<endl;
            return;
        } else if(head==tail){      // single node
            delete head;
            head = tail = NULL;
        } else{
            Node* temp = tail;
            Node* prev = head;
            while(prev->next!=tail){
                prev = prev->next;
            }
            tail = prev;
            tail->next = head;
            temp->next = NULL;

            delete temp;
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

    cll.insertAtTail(4);

    cll.deleteAtHead();

    cll.deleteAtTail();

    cll.printLL();

    return 0;
}