#include <iostream>
using namespace std;

struct Node{
    int INFO;      // data
    int PRN;       // priority number (lower value = higher priority)
    Node *LINK;
};

Node *START = NULL;   // priority queue empty


void INSERT(int ITEM, int PRIORITY){
    Node *NEW = new Node;
    NEW->INFO = ITEM;
    NEW->PRN  = PRIORITY;
    NEW->LINK = NULL;

    if(START==NULL   ||   PRIORITY<START->PRN){
        NEW->LINK = START;
        START = NEW;
        return;
    }

    Node *PTR = START;
    while(PTR->LINK!=NULL   &&   PTR->LINK->PRN<=PRIORITY){
        PTR = PTR->LINK;
    }

    NEW->LINK = PTR->LINK;
    PTR->LINK = NEW;
}


void DELETE(){
    if(START==NULL){
        cout<<"UNDERFLOW\n";
        return;
    }

    Node *TEMP = START;
    cout<<"Deleted ITEM = "<<TEMP->INFO<<" Priority = "<<TEMP->PRN<<endl;

    START = START->LINK;
    delete TEMP;
}


void DISPLAY(){
    if(START==NULL){
        cout<<"Priority Queue is empty\n";
        return;
    }

    Node *PTR = START;
    cout<<"\nITEM   PRIORITY\n";

    while(PTR!=NULL){
        cout<<PTR->INFO<<"       "<<PTR->PRN<<endl;
        PTR = PTR->LINK;
    }
}


int main()
{
    INSERT(10, 3);

    INSERT(30, 2);
    INSERT(40, 1);
    INSERT(20, 1);
    INSERT(50, 4);

    DISPLAY();

    DELETE();
    DELETE();

    DISPLAY();

    return 0;
}
