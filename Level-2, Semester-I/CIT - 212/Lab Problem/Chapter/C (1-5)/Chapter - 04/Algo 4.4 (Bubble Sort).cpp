/*
*Algorithm 4.4 — Bubble Sort
* (Bubble Sort) BUBBLE(DATA, N) Here DATA is an array with N elements. This algorithm sorts the elements inDATA.
* 1. Repeat Steps 2 and 3 for K = 1 to N - 1.
* 2.    Set PTR := 1. [Initializes pass pointer PTR.]
* 3.    Repeat while PTR ≤ N - K: [Executes pass.]
*           (a) If DATA[PTR] > DATA[PTR + 1], then:
*                   Interchange DATA[PTR] and DATA[PTR + 1].
*               [End of If structure.]
*           (b) Set PTR := PTR + 1.
*       [End of inner loop.]
*   [End of outer loop.]
* 4. Exit.
*/

//      <-----------STart CoDe---------------->
#include <bits/stdc++.h>
using namespace std;

void BUBBLE(int DATA[], int N) {
    for(int i=0; i<N-1; i++){
        for(int j=0; j<N-i-1; j++){
            if(DATA[j] > DATA[j+1]){
                int temp = DATA[j];
                DATA[j] = DATA[j+1];
                DATA[j+1] = temp;
            }
        }
    }
}

int main()
{
    int N;
    cin>>N;
    int DATA[N];
    for(int i=0; i<N; i++) cin>>DATA[i];

    BUBBLE(DATA, N);

    for(int i=0; i<N; i++){
        cout<<DATA[i]<<" ";
    }

    return 0;
}

//      <-----------EnD CoDe---------------->

/*
!  Book e eivabe Algorithm kora
#include <iostream>
using namespace std;

void BUBBLE(int DATA[], int N){
    int K, PTR, TEMP;
    for(K=1; K<=N-1; K++){
        PTR = 1;

        while(PTR <= N - K){
            if(DATA[PTR] > DATA[PTR+1]){
                TEMP = DATA[PTR];
                DATA[PTR] = DATA[PTR + 1];
                DATA[PTR + 1] = TEMP;
            }
            PTR = PTR + 1;
        }
    }
}

int main()
{
    int DATA[100];
    int N;
    cin>>N;
    for(int i=1; i<=N; i++) cin>>DATA[i];

    BUBBLE(DATA, N);
    for(int i=1; i<=N; i++){
        cout<<DATA[i]<<" ";
    }

    return 0;
}
*/