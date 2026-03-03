#include <iostream>
#include <ctime>   // for clock()
using namespace std;

// Linear Search with Sentinel
int LINEAR(int DATA[], int N, int ITEM, int &iteration){
    int LOC;
    DATA[N+1] = ITEM;   // sentinel
    LOC = 1;
    iteration = 0;

    while(DATA[LOC] != ITEM){
        LOC = LOC + 1;
        iteration++;
    }

    if(LOC == N+1)
        return -1;
    else
        return LOC;
}

// Binary Search
int BINARY(int DATA[], int LB, int UB, int ITEM, int &iteration){
    int BEG = LB;
    int END = UB;
    int MID;
    iteration = 0;

    while(BEG <= END){
        MID = (BEG + END) / 2;
        iteration++;

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
    cout<<"Enter number of elements: ";
    cin>>N;

    int DATA[100000];

    cout<<"Enter sorted elements:\n";
    for(int i=1; i<=N; i++)
        cin>>DATA[i];

    cout<<"Enter item to search: ";
    cin>>ITEM;

    int iterLinear, iterBinary;
    int loc1, loc2;

    // -------- Linear Search Time --------
    clock_t start1 = clock();     // start
    loc1 = LINEAR(DATA, N, ITEM, iterLinear);
    clock_t end1 = clock();       // end

    double timeLinear = double(end1 - start1) / CLOCKS_PER_SEC;

    // -------- Binary Search Time --------
    clock_t start2 = clock();     // start
    loc2 = BINARY(DATA, 1, N, ITEM, iterBinary);
    clock_t end2 = clock();       // end

    double timeBinary = double(end2 - start2) / CLOCKS_PER_SEC;

    // -------- Output --------
    cout<<"\n----- Result -----\n";

    cout<<"Linear Search:\n";
    cout<<"Location = "<<loc1<<endl;
    cout<<"Iterations = "<<iterLinear<<endl;
    cout<<"Time = "<<timeLinear<<" seconds\n\n";

    cout<<"Binary Search:\n";
    cout<<"Location = "<<loc2<<endl;
    cout<<"Iterations = "<<iterBinary<<endl;
    cout<<"Time = "<<timeBinary<<" seconds\n";

    return 0;
}