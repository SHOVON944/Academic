/*
* Algorithm 4.5 — Linear Search (with Sentinel)
*(Linear Search) LINEAR(DATA, N, ITEM, LOC)
*Here DATA is a linear array with N elements, and ITEM is a given item of
*information. This algorithm finds the location I OC of ITEM in DATA, or Sets
*LOC =0 if the search is unsuccessful.
* 1. [insert ITEM at the end of DATA.] Set DATA[N + I]: = ITEM.
* 2. [Initialize counter] Set LOC:= 1.
* 3. [Search for ITEM.]
*    Repeat while DATA[LOCJ s ITEM:
*       Set LOC:=LOC+1.
*       (End of loop.]
* 4. (Successful?] If LOC = N + 1, then: Set LOC : = 0.
* 5.  Exit.
*/

//      <-----------STart CoDe---------------->
#include <iostream>
using namespace std;

void LINEAR(int DATA[], int N, int ITEM){
    int LOC;
    DATA[N+1] = ITEM;
    LOC = 1;
    while(DATA[LOC] != ITEM){
        LOC = LOC + 1;
    }

    if(LOC == N+1){
        cout << "Unsuccessful";
    } else{
        cout<<LOC;
    }
}

int main()
{
    int DATA[102];
    int N, ITEM;
    cin>>N;

    for(int i=1; i<=N; i++) cin>>DATA[i];
    cin>>ITEM;
    LINEAR(DATA, N, ITEM);

    return 0;
}
//      <-----------EnD CoDe---------------->
