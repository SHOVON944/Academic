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

class List{
public:
    Node* head;
    Node* tail;

    List(){
        head = tail = NULL;
    }

    void push_front(int val){
        Node* newNode = new Node(val);

        if(head == NULL){
            head = tail = newNode;
        }
        else{
            newNode->next = head;
            head = newNode;
        }
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

    // Insert after nth node
    void insertAfter(int pos, int val){
        Node* temp = head;

        for(int i=1; i<pos && temp!=NULL; i++){
            temp = temp->next;
        }

        if(temp == NULL) return;

        Node* newNode = new Node(val);

        newNode->next = temp->next;
        temp->next = newNode;

        if(temp == tail)
            tail = newNode;
    }

    void pop_front(){
        if(head == NULL) return;

        Node* temp = head;
        head = head->next;

        delete temp;

        if(head == NULL)
            tail = NULL;
    }

    void pop_back(){
        if(head == NULL) return;

        if(head == tail){
            delete head;
            head = tail = NULL;
            return;
        }

        Node* temp = head;

        while(temp->next != tail){
            temp = temp->next;
        }

        delete tail;
        tail = temp;
        tail->next = NULL;
    }

    // Delete nth node
    void deletePosition(int pos){
        if(head == NULL) return;

        if(pos == 1){
            pop_front();
            return;
        }

        Node* temp = head;

        for(int i=1; i<pos-1 && temp!=NULL; i++){
            temp = temp->next;
        }

        if(temp == NULL || temp->next == NULL)
            return;

        Node* delNode = temp->next;

        temp->next = delNode->next;

        if(delNode == tail)
            tail = temp;

        delete delNode;
    }

    void printLL(){
        Node* temp = head;

        while(temp != NULL){
            cout << temp->data << " -> ";
            temp = temp->next;
        }

        cout << "NULL" << endl;
    }
};

int main(){

    cout << "1. Insert 10 at Start" << endl;
    List l1;
    l1.push_back(20);
    l1.push_back(30);
    l1.push_front(10);
    l1.printLL();

    cout << endl;
    cout << "2. Insert 50 at End" << endl;
    l1.push_back(50);
    l1.printLL();

    cout << endl;
    cout << "3. Insert 40 after 3rd node" << endl;
    l1.insertAfter(3,40);
    l1.printLL();

    cout << endl;
    cout << "4. Delete from Start" << endl;
    List l2;
    l2.push_back(10);
    l2.push_back(20);
    l2.push_back(30);
    l2.pop_front();
    l2.printLL();

    cout << endl;
    cout << "5. Delete from End" << endl;
    List l3;
    l3.push_back(20);
    l3.push_back(30);
    l3.push_back(40);
    l3.pop_back();
    l3.printLL();

    cout << endl;
    cout << "6. Delete 3rd Node" << endl;
    List l4;
    l4.push_back(20);
    l4.push_back(30);
    l4.push_back(50);
    l4.push_back(60);
    l4.deletePosition(3);
    l4.printLL();

    return 0;
}