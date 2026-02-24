#include<iostream>
using namespace std;

#define NULLPTR 0

void QINSERT(int QUEUE[], int N, int &FRONT, int &REAR, int ITEM){
    if((FRONT==1  &&  REAR==N)   ||   (FRONT==REAR+1)){
        cout<<"OVERFLOW"<<endl;
        return;
    }
    if(FRONT==NULLPTR){
        FRONT = 1;
        REAR = 1;
    } else if(REAR==N){
        REAR = 1;
    } else{
        REAR = REAR + 1;
    }

    QUEUE[REAR] = ITEM;

    return;
}

void DISPLAY(int QUEUE[], int N, int FRONT, int REAR){
    if (FRONT==0){
        cout<<"Queue is empty"<<endl;
        return;
    }

    cout<<"Queue elements: ";

    if(FRONT<=REAR){
        for(int i=FRONT; i<=REAR; i++)
            cout<<QUEUE[i]<<" ";
    } else{
        for(int i=FRONT; i<N; i++)
            cout<<QUEUE[i]<<" ";
        for(int i=0; i<=REAR; i++)
            cout<<QUEUE[i]<< " ";
    }

    cout<<endl;
}

int main()
{
    int N = 5;
    int QUEUE[6];   // 1 based index
    int FRONT = NULLPTR;
    int REAR = NULLPTR;
    int ITEM;

    ITEM = 20;
    QINSERT(QUEUE, N, FRONT, REAR, ITEM);
    ITEM = 30;
    QINSERT(QUEUE, N, FRONT, REAR, ITEM);
    ITEM = 40;
    QINSERT(QUEUE, N, FRONT, REAR, ITEM);
    DISPLAY(QUEUE, N, FRONT, REAR);

    return 0;
}