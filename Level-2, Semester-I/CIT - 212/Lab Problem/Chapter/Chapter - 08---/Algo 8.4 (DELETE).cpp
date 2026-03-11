/*
Like as book
void DELETE(int item){
    if(head == NULL){
        cout<<"List is empty. Cannot delete.\n";
        return;
    }

    // First node check
    if(head->data == item){
        Node* PTR = head;
        head = head->next;
        if(PTR == tail) tail = NULL; // single node case
        delete PTR;
        cout << "Item deleted from the first node.\n";
        return;
    }

    // Middle / Last node
    Node* prev = head;
    Node* curr = head->next;

    while(curr != NULL){
        if(curr->data == item){
            prev->next = curr->next;
            if(curr == tail) tail = prev; // last node delete
            delete curr;
            cout << "Item deleted from the list.\n";
            return;
        }
        prev = curr;
        curr = curr->next;
    }

    cout << "Item not found. Cannot delete.\n";
}
*/



#include <bits/stdc++.h>
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

class List{
    Node* head;
    Node* tail;

public:
    List(){
        head = tail = NULL;
    }

    void push_back(int val){
        Node* newNode = new Node(val);

        if(head == NULL){
            head = tail = newNode;
        }
        else{
            tail->next = newNode;
            tail = newNode;
        }
    }

    void printLL(){
        Node* temp = head;
        while(temp != NULL){
            cout<<temp->data << " -> ";
            temp = temp->next;
        }
        cout<<"NULL"<<endl;
    }

    void DELETE(int item){
        if(head == NULL){
            cout<<"List is empty. Cannot delete.";
            return;
        }

        Node* PTR = head;
        Node* PREV = NULL;  // previous node, initially NULL

        while(PTR != NULL){
            if(PTR->data == item){
                if(PTR == head){                    // first node
                    head = PTR->next;
                    if(PTR == tail) tail = NULL;    // single node case
                } else{                            // middle or last node
                    PREV->next = PTR->next;
                    if(PTR == tail) tail = PREV;    // last node update
                }

                delete PTR;
                cout << "Item deleted successfully."<<endl;
                return;
            }
            PREV = PTR;
            PTR = PTR->next;
        }
        cout << "Item not found. Cannot delete.";
    }
};

int main()
{
    List ll;
    int n, val;
    cout<<"Enter number of nodes: ";
    cin>>n;
    cout<<"Enter values: ";
    for(int i=0; i<n; i++){
        cin>>val;
        ll.push_back(val);
    }
    cout<<"Linked List: ";
    ll.printLL();

    int item;
    cout<<"Enter item to delete: ";
    cin>>item;
    ll.DELETE(item);
    cout<<"Linked List after deletion: ";
    ll.printLL();

    return 0;
}
