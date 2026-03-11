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

    void FIND(int item){
        Node* PTR = head;
        int LOC = 1;
        while(PTR != NULL){
            if(item == PTR->data){
                cout<<"Find. Location is: "<<LOC;
                return;
            } else{
                PTR = PTR->next;
                LOC++;
            }
        }
        LOC = 0;
        cout<<"Cannot Find. Location is: "<<LOC;
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
    cout<<"Finding item value: ";
    int item;
    cin>>item;
    ll.FIND(item);

    return 0;
}
