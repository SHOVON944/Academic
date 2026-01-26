#include <iostream>
using namespace std;

#define NULLPTR 0

void QINSERT(int QUEUE[], int N, int &FRONT, int &REAR, int ITEM){
    if((FRONT==1  &&  REAR==N)   ||   (FRONT==REAR+1)){
        cout<<"OVERFLOW"<<endl;
        return;
    }

    if(FRONT==NULLPTR){
        FRONT = 1;
        REAR  = 1;
    } else if(REAR==N){
        REAR = 1;
    } else{
        REAR = REAR + 1;
    }

    QUEUE[REAR] = ITEM;
}

void QDELETE(int QUEUE[], int N, int &FRONT, int &REAR, int &ITEM){
    if(FRONT==NULLPTR){
        cout<<"UNDERFLOW"<<endl;
        return;
    }

    ITEM = QUEUE[FRONT];

    if(FRONT==REAR){
        FRONT = NULLPTR;
        REAR  = NULLPTR;
    } else if (FRONT == N){
        FRONT = 1;
    } else{
        FRONT = FRONT + 1;
    }
}

void DISPLAY(int QUEUE[], int N, int FRONT, int REAR){
    if(FRONT==NULLPTR){
        cout<<"Queue is empty"<<endl;
        return;
    }

    cout<<"Queue elements: ";

    if(FRONT<=REAR){
        for(int i=FRONT; i<=REAR; i++)
            cout<<QUEUE[i]<<" ";
    } else{
        for(int i=FRONT; i<=N; i++)
            cout<<QUEUE[i]<<" ";
        for(int i=1; i<=REAR; i++)
            cout<<QUEUE[i]<<" ";
    }

    cout<<endl;
}

int main()
{
    int N = 5;
    int QUEUE[6]; // 1-based indexing
    int FRONT = NULLPTR;
    int REAR  = NULLPTR;
    int ITEM;

    QINSERT(QUEUE, N, FRONT, REAR, 10);
    QINSERT(QUEUE, N, FRONT, REAR, 20);
    QINSERT(QUEUE, N, FRONT, REAR, 30);
    DISPLAY(QUEUE, N, FRONT, REAR);

    QDELETE(QUEUE, N, FRONT, REAR, ITEM);
    cout<<"Deleted item: "<<ITEM<<endl;
    DISPLAY(QUEUE, N, FRONT, REAR);

    QINSERT(QUEUE, N, FRONT, REAR, 40);
    QINSERT(QUEUE, N, FRONT, REAR, 50);
    DISPLAY(QUEUE, N, FRONT, REAR);

    return 0;
}
