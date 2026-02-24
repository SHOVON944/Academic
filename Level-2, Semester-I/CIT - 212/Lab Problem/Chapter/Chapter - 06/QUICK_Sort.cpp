#include <iostream>
using namespace std;

#define MAX 100

void QUICK(int A[], int N, int BEG, int END, int &LOC){
    int LEFT = BEG;
    int RIGHT = END;
    LOC = BEG;

    while(true){
        while(A[LOC]<=A[RIGHT]   &&   LOC != RIGHT){
            RIGHT--;
        }
        if(LOC==RIGHT) return;
        if(A[LOC] > A[RIGHT]){
            int temp = A[LOC];
            A[LOC] = A[RIGHT];
            A[RIGHT] = temp;
            LOC = RIGHT;
        }

        while(A[LEFT]<=A[LOC]   &&   LEFT != LOC){
            LEFT++;
        }

        if(LOC==LEFT) return;
        if(A[LEFT] > A[LOC]){
            int temp = A[LOC];
            A[LOC] = A[LEFT];
            A[LEFT] = temp;
            LOC = LEFT;
        }
    }
}

void QUICKSORT(int A[], int N){
    int LOWER[MAX], UPPER[MAX];
    int TOP = 0;   // NULL = 0 
    int BEG, END, LOC;

    if(N>1){
        TOP = TOP + 1;
        LOWER[TOP] = 0;
        UPPER[TOP] = N - 1;
    }

    while(TOP!=0){
        BEG = LOWER[TOP];
        END = UPPER[TOP];
        TOP = TOP - 1;

        QUICK(A, N, BEG, END, LOC);

        if(BEG < LOC-1){
            TOP = TOP + 1;
            LOWER[TOP] = BEG;
            UPPER[TOP] = LOC - 1;
        }

        if(LOC+1 < END){
            TOP = TOP + 1;
            LOWER[TOP] = LOC + 1;
            UPPER[TOP] = END;
        }
    }
}

int main(){
    int A[] = {77, 44, 33, 11, 55, 0, 90, 40, 60, 99, 22};
    int N = sizeof(A)/ sizeof(A[0]);

    cout<<"Original Array:\n";
    for(int i=0; i<N; i++){
        cout<<A[i]<<" ";
    }

    QUICKSORT(A, N);

    cout<<"\n\nSorted Array:\n";
    for(int i=0; i<N; i++){
        cout<<A[i]<<" ";
    }

    return 0;
}
