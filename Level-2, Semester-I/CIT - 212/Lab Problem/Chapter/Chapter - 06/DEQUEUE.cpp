#include <iostream>
using namespace std;

#define NULLPTR 0

void INSERT_RIGHT(int DEQUE[], int N, int &LEFT, int &RIGHT, int ITEM){
    if((LEFT==1  &&  RIGHT==N)   ||   (LEFT==RIGHT+1)){
        cout<<"OVERFLOW\n";
        return;
    }

    if(LEFT==NULLPTR){
        LEFT = 1;
        RIGHT = 1;
    } else if(RIGHT==N){
        RIGHT = 1;
    } else{
        RIGHT = RIGHT + 1;
    }

    DEQUE[RIGHT] = ITEM;
}

void INSERT_LEFT(int DEQUE[], int N, int &LEFT, int &RIGHT, int ITEM){
    if((LEFT==1  &&  RIGHT==N)   ||   (LEFT==RIGHT+1)){
        cout<<"OVERFLOW\n";
        return;
    }

    if(LEFT==NULLPTR){
        LEFT = 1;
        RIGHT = 1;
    } else if(LEFT==1){
        LEFT = N;
    } else{
        LEFT = LEFT - 1;
    }

    DEQUE[LEFT] = ITEM;
}

void DELETE_LEFT(int DEQUE[], int N, int &LEFT, int &RIGHT, int &ITEM){
    if(LEFT==NULLPTR){
        cout<<"UNDERFLOW\n";
        return;
    }

    ITEM = DEQUE[LEFT];

    if(LEFT==RIGHT){
        LEFT = NULLPTR;
        RIGHT = NULLPTR;
    } else if(LEFT==N){
        LEFT = 1;
    } else{
        LEFT = LEFT + 1;
    }
}

void DELETE_RIGHT(int DEQUE[], int N, int &LEFT, int &RIGHT, int &ITEM){
    if(LEFT==NULLPTR){
        cout<<"UNDERFLOW\n";
        return;
    }

    ITEM = DEQUE[RIGHT];

    if(LEFT==RIGHT){
        LEFT = NULLPTR;
        RIGHT = NULLPTR;
    } else if(RIGHT==1){
        RIGHT = N;
    } else{
        RIGHT = RIGHT - 1;
    }
}

void DISPLAY(int DEQUE[], int N, int LEFT, int RIGHT){
    if(LEFT==NULLPTR){
        cout<<"Deque is empty\n";
        return;
    }

    cout<<"Deque elements: ";

    if(LEFT<=RIGHT){
        for(int i=LEFT; i<=RIGHT; i++)
            cout<<DEQUE[i]<<" ";
    } else{
        for(int i=LEFT; i<=N; i++)
            cout<<DEQUE[i]<<" ";
        for(int i=1; i<=RIGHT; i++)
            cout<<DEQUE[i]<<" ";
    }
    cout<<endl;
}

int main()
{
    int N = 5;
    int DEQUE[6];   // 1-based indexing
    int LEFT = NULLPTR;
    int RIGHT = NULLPTR;
    int ITEM;

    INSERT_RIGHT(DEQUE, N, LEFT, RIGHT, 10);
    INSERT_RIGHT(DEQUE, N, LEFT, RIGHT, 20);
    INSERT_LEFT(DEQUE, N, LEFT, RIGHT, 5);
    DISPLAY(DEQUE, N, LEFT, RIGHT);

    DELETE_LEFT(DEQUE, N, LEFT, RIGHT, ITEM);
    cout << "Deleted from LEFT: " << ITEM << endl;
    DISPLAY(DEQUE, N, LEFT, RIGHT);

    DELETE_RIGHT(DEQUE, N, LEFT, RIGHT, ITEM);
    cout << "Deleted from RIGHT: " << ITEM << endl;
    DISPLAY(DEQUE, N, LEFT, RIGHT);

    return 0;
}
