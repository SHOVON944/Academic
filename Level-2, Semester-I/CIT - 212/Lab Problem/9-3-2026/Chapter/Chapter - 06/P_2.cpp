#include <iostream>
#include <ctime>
using namespace std;

// Linear Search
int LINEAR(int DATA[], int N, int ITEM){
    DATA[N+1] = ITEM;
    int LOC = 1;

    while(DATA[LOC] != ITEM){
        LOC++;
    }

    if(LOC == N+1)
        return -1;
    else
        return LOC;
}

// Binary Search
int BINARY(int DATA[], int LB, int UB, int ITEM){
    int BEG = LB, END = UB, MID;

    while(BEG <= END){
        MID = (BEG + END) / 2;

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

    // Linear Time
    clock_t start1 = clock();
    LINEAR(DATA, N, ITEM);
    clock_t end1 = clock();

    double timeLinear = double(end1 - start1) / CLOCKS_PER_SEC;

    // Binary Time
    clock_t start2 = clock();
    BINARY(DATA, 1, N, ITEM);
    clock_t end2 = clock();

    double timeBinary = double(end2 - start2) / CLOCKS_PER_SEC;

    cout << "Linear Time: " << timeLinear << " seconds\n";
    cout << "Binary Time: " << timeBinary << " seconds\n";

    return 0;
}