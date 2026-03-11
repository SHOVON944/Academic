#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node* next;

    Node(int val) {
        data = val;
        next = NULL;
    }
};

class List {
    Node* head;
    Node* tail;

public:
    List() {
        head = tail = NULL;
    }

    void push_back(int val) {
        Node* newNode = new Node(val);
        if (head == NULL) {
            head = tail = newNode;
        } else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    void printLL() {
        Node* temp = head;
        while (temp != NULL) {
            cout << temp->data << " -> ";
            temp = temp->next;
        }
        cout << "NULL" << endl;
    }

    void DELETE(int item){
        if(head == NULL){
            cout<<"List is empty. Cannot delete.";
            return;
        }

        Node* PTR = head;
        while(PTR != NULL){
            if(PTR->data == item){
                head = head->next;
                delete PTR;
                cout << "Item deleted from the first node.\n";
                return;
            } else{
                PTR = PTR->next;
            }
        }
    }
};

int main()
{
    List ll;
    int n, val;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter values:" << endl;
    for (int i = 0; i < n; i++) {
        cin >> val;
        ll.push_back(val);
    }

    cout << "Linked List: ";
    ll.printLL();

    int item;
    cout << "Enter item to delete: ";
    cin >> item;

    ll.DELETE(item);

    cout << "Linked List after deletion: ";
    ll.printLL();

    return 0;
}