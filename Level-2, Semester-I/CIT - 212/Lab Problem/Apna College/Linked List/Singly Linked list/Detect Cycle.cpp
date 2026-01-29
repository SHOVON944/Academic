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

    void pop_back(){
        if(head==NULL){
            cout<<"LL is empty"<<endl;
            return;
        }
        Node* temp = head;
        while(temp->next!=tail){    // while(temp->next->next!=NULL), eita empty(), 1 value er jnno work korbe na...2/2+ value er jnno work korbe
            temp = temp->next;
        }
        temp->next = NULL;
        delete tail;
        tail = temp;
    }

    void insert(int val, int pos){
        if(pos<0){
            cout<<"Invalid position"<<endl;
            return;
        }
        if(pos==0){
            push_front(val);
        }
        Node* temp = head;
        for(int i=0; i<pos-1; i++){
            if(temp==NULL){
                cout<<"Invalid position"<<endl;
                return;
            }
            temp = temp->next;
        }

        Node* newNode = new Node(val);
        newNode->next = temp->next;
        temp->next = newNode;
    }

    void printLL(){
        Node* temp = head;
        while(temp!=NULL){
            cout<<temp->data<<" -> ";
            temp = temp->next;
        }
        cout<<" NULL"<<endl;
    }

    int search(int key){
        Node* temp = head;
        int idx = 0;
        while(temp!=NULL){
            if(temp->data==key){
                return idx;
            }
            temp = temp->next;
            idx++;
        }
        return -1;
    }

    void reverseList(){
        Node* prev = NULL;
        Node* curr = head;
        Node* next = NULL;

        while(curr!=NULL){
            next = curr->next;
            curr->next = prev;

            prev = curr;
            curr = next;
        }
        head = prev;
    }

    int middleNodeIndex(){
        Node* slow = head;
        Node* fast = head;
        int idx = 0;

        while(fast!=NULL   &&   fast->next!=NULL){
            slow = slow->next;
            fast = fast->next->next;
            idx++;
        }
        return idx;
    }

    bool detectCycle(){
        Node* slow = head;
        Node* fast = head;

        while(fast!=NULL   &&   fast->next!=NULL){
            slow = slow->next;
            fast = fast->next->next;
            if(slow==fast){
                cout<<"Yes, Loop exist."<<endl;
                return true;
            }
        }
        cout<<"NO loop exist."<<endl;
        return false;
    }
};

// slow fast pointer approch
int main()
{
    List ll;

    ll.push_front(3);
    ll.push_front(2);
    ll.push_front(1);

    ll.detectCycle();

    ll.printLL();

    return 0;
}