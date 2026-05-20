#include <iostream>
using namespace std;

#define NULLPTR 0

void TRAVERSE(int INFO[], int LINK[], int START){
    int PTR;
    PTR = START;
    while(PTR != NULLPTR){
        cout << INFO[PTR] << " ";
        PTR = LINK[PTR];
    }
}

int main()
{
    int n, START;
    cout << "Enter number of nodes: ";
    cin >> n;
    int INFO[n+1];
    int LINK[n+1];

    cout << "Enter INFO values:\n";
    for(int i=1;i<=n;i++) cin >> INFO[i];

    cout << "Enter LINK values:\n";
    for(int i=1;i<=n;i++) cin >> LINK[i];

    cout << "Enter START position: ";
    cin >> START;

    cout << "Linked List Traversal: ";
    TRAVERSE(INFO, LINK, START);

    return 0;
}


/*
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

    // Algorithm 5.1 (Traverse)
    void traverse(){
        Node* ptr = head;

        while(ptr != NULL){
            cout << ptr->data << " -> ";
            ptr = ptr->next;
        }

        cout << "NULL" << endl;
    }
};

int main()
{
    List ll;
    int n,val;

    cout<<"Enter number of nodes: ";
    cin>>n;

    cout<<"Enter values:"<<endl;
    for(int i=0;i<n;i++){
        cin>>val;
        ll.push_back(val);
    }

    cout<<"Linked List: ";
    ll.traverse();

    return 0;
}

*/