#include<iostream>
using namespace std;

#define NULLPTR 0

void QINSERT(int QUEUE[], int N, int &FRONT, int &REAR, int ITEM){
    if((FRONT==1  &&  REAR==N)   ||   (FRONT==REAR+1)){
        cout << "OVERFLOW" << endl;
        return;
    }
    if(FRONT==NULLPTR){ FRONT = 1; REAR = 1; }
    else if(REAR==N){ REAR = 1; }
    else{ REAR = REAR + 1; }

    QUEUE[REAR] = ITEM;
    return;
}

void DISPLAY(int QUEUE[], int N, int FRONT, int REAR){
    if (FRONT==0){ cout << "The Queue is empty." << endl; return; }

    cout << "The Queue elements are: ";
    if(FRONT<=REAR){
        for(int i=FRONT; i<=REAR; i++) cout << QUEUE[i] << " ";
    } else{
        for(int i=FRONT; i<=N; i++) cout << QUEUE[i] << " ";
        for(int i=1; i<=REAR; i++) cout << QUEUE[i] << " ";
    }
    cout << endl;
}

int main()
{
    int N;
    cout << "Enter the size of the queue: ";
    cin >> N;

    int QUEUE[N+1];
    int FRONT = NULLPTR;
    int REAR = NULLPTR;
    int ITEM, k;

    cout << "Enter the number of items to insert: ";
    cin >> k;

    for(int i = 1; i <= k; i++){
        cout << "Enter item " << i << ": ";
        cin >> ITEM;
        QINSERT(QUEUE, N, FRONT, REAR, ITEM);
    }

    DISPLAY(QUEUE, N, FRONT, REAR);

    return 0;
}