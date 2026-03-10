#include <iostream>
using namespace std;

#define NULLPTR 0

void SEARCH(int INFO[], int LINK[], int START, int ITEM, int &LOC)
{
    int PTR;

    PTR = START;

    while(PTR != NULLPTR)
    {
        if(INFO[PTR] == ITEM)
        {
            LOC = PTR;
            return;
        }
        else
        {
            PTR = LINK[PTR];
        }
    }

    LOC = NULLPTR;
}

int main()
{
    int n, START, ITEM, LOC;

    cout << "Enter number of nodes: ";
    cin >> n;

    int INFO[n+1], LINK[n+1];

    cout << "Enter INFO values:\n";
    for(int i=1;i<=n;i++)
        cin >> INFO[i];

    cout << "Enter LINK values:\n";
    for(int i=1;i<=n;i++)
        cin >> LINK[i];

    cout << "Enter START position: ";
    cin >> START;

    cout << "Enter ITEM to search: ";
    cin >> ITEM;

    SEARCH(INFO, LINK, START, ITEM, LOC);

    if(LOC != 0)
        cout << "Item found at location: " << LOC;
    else
        cout << "Item not found";

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

    // Algorithm 5.2 (Search)
    void search(int item){
        Node* ptr = head;
        int pos = 1;

        while(ptr != NULL){
            if(ptr->data == item){
                cout<<"Item found at position: "<<pos<<endl;
                return;
            }
            ptr = ptr->next;
            pos++;
        }

        cout<<"Item not found"<<endl;
    }

    void printLL(){
        Node* temp = head;

        while(temp!=NULL){
            cout<<temp->data<<" -> ";
            temp = temp->next;
        }
        cout<<"NULL"<<endl;
    }
};

int main()
{
    List ll;
    int n,val,item;

    cout<<"Enter number of nodes: ";
    cin>>n;

    cout<<"Enter values:"<<endl;
    for(int i=0;i<n;i++){
        cin>>val;
        ll.push_back(val);
    }

    cout<<"Linked List: ";
    ll.printLL();

    cout<<"Enter item to search: ";
    cin>>item;

    ll.search(item);

    return 0;
}

*/