#include <iostream>
using namespace std;

#define MAX 100

int STN[MAX];
char STBEG[MAX], STAUX[MAX], STEND[MAX];
int TOP = NULL;

void PUSH(int N, char BEG, char AUX, char END){
    TOP = TOP + 1;
    STN[TOP] = N;
    STBEG[TOP] = BEG;
    STAUX[TOP] = AUX;
    STEND[TOP] = END;
}

void POP(int &N, char &BEG, char &AUX, char &END){
    N = STN[TOP];
    BEG = STBEG[TOP];
    AUX = STAUX[TOP];
    END = STEND[TOP];
    TOP = TOP - 1;
}

void TOWER(int N, char BEG, char AUX, char END){
    PUSH(N, BEG, AUX, END);
    if(N==1){
        cout << BEG << " -> " << END << endl;
        POP(N, BEG, AUX, END);
        return;
    }

    TOWER(N - 1, BEG, END, AUX);
    cout<<BEG<<" -> "<<END<<endl;
    TOWER(N - 1, AUX, BEG, END);

    POP(N, BEG, AUX, END);
}

int main()
{
    int N;
    cin>>N;

    TOWER(N, 'A', 'B', 'C');

    return 0;
}
