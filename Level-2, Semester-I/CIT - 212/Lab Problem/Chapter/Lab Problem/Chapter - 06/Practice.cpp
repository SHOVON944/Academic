#include <iostream>
using namespace std;

#define NULLPTR -1

void Qinsert(int queue[],int n,int &front, int  &rear,int item){
    if((front==1   &&   rear==n)  ||  (front==rear+1)){
        return;
    }

    if(front==NULLPTR){
        front = 0;
        rear = 0;
    } else if(rear==n-1){
        rear=0;
    } else{
        rear = rear + 1;
    }

    queue[rear] = item;
}

void display(int queue[], int n,int front, int  rear){
    if(front==-1){
        return;
    }
    if(front<=rear){
        for(int i=front; i<=rear; i++){
            cout<<queue[i]<<" ";
        }
    } else{
        for(int i=front; i<n; i++){
            cout<<queue[i]<<" ";
        }
        for(int i=0; i<rear; i++){
            cout<<queue[i]<<" ";
        }
    }
    cout<<endl;
}

void QDELETE(int queue[], int n, int &front, int &rear, int &item){
    if(front==NULLPTR) return;

    item = queue[front];
    if(front == rear){
        front = NULLPTR;
        rear = NULLPTR;
    } else if(front==n-1){
        front = 0;
    } else{
        front = front + 1;
    }
}

int main()
{
    int n = 5;
    int queue[5];
    int front = NULLPTR;
    int rear = NULLPTR;
    int item = 20;
    Qinsert(queue, n, front, rear, item);
    Qinsert(queue, n, front, rear, 30);
    Qinsert(queue, n, front, rear, 40);
    int ITEM;

    display(queue, n, front, rear);
    QDELETE(queue, n, front, rear, ITEM);
    cout<<ITEM<<endl;

    return 0;
}
