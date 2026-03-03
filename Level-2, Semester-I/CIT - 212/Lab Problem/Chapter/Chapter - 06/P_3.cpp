#include <iostream>
using namespace std;

// Linear Search with iteration count
int LINEAR(int DATA[], int N, int ITEM, int &count){
    DATA[N+1] = ITEM;
    int LOC = 1;
    count = 0;

    while(DATA[LOC] != ITEM){
        LOC++;
        count++;
    }

    if(LOC == N+1)
        return -1;
    else
        return LOC;
}

// Binary Search with iteration count
int BINARY(int DATA[], int LB, int UB, int ITEM, int &count){
    int BEG = LB, END = UB, MID;
    count = 0;

    while(BEG <= END){
        MID = (BEG + END) / 2;
        count++;

        if(DATA[MID] == ITEM)
            return MID;
        else if(ITEM < DATA[MID])
            END = MID - 1;
        else
            BEG = MID + 1;
    }
    return -1;
}

int main(){

    int N, ITEM;
    cin >> N;

    int DATA[100000];

    for(int i=1; i<=N; i++)
        cin >> DATA[i];

    cin >> ITEM;

    int linearCount, binaryCount;

    LINEAR(DATA, N, ITEM, linearCount);
    BINARY(DATA, 1, N, ITEM, binaryCount);

    cout << "Linear Iterations: " << linearCount << endl;
    cout << "Binary Iterations: " << binaryCount << endl;

    return 0;
}