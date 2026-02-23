/*
Algorithm 4.6:
(Binary Search)BINARY(DATA, LB, UB, ITEM, LOC)
Here DATA is a sorted array with lower bound LB and upper bound UB, and ITEM is a given item of information. The variables BEG, END and MID denote, respectively, the beginning, end and middle locations of a segment of elements of DATA. This algorithm finds the location LOC of ITEM in DATA or sets LOC=NULLLOC=NULL.
1.	[Initialize segment variables.]
Set BEG:=LBBEG:=LB, END:=UBEND:=UB and MID:=INT((BEG+END)/2)MID:=INT((BEG+END)/2).
2.	Repeat Steps 3 and 4 while BEG≤ENDBEG≤END and DATA[MID]≠ITEMDATA[MID]=ITEM.
3.	      If ITEM<DATA[MID]ITEM<DATA[MID], then:
        Set END:=MID−1END:=MID−1.
   Else:
        Set BEG:=MID+1BEG:=MID+1.
 [End of If structure.]
4.	Set MID:=INT((BEG+END)/2)MID:=INT((BEG+END)/2).
 [End of Step 2 loop.]
5.	If DATA[MID]=ITEMDATA[MID]=ITEM, then:
 Set LOC:=MIDLOC:=MID.
 Else:
 Set LOC:=NULLLOC:=NULL.
 [End of If structure.]
6.	Exit.

*/

#include <iostream>
using namespace std;

void BINARY(int DATA[], int LB, int UB, int ITEM, int &LOC){
    int BEG, END, MID;
    BEG = LB;
    END = UB;
    MID = (BEG + END) / 2;
    while(BEG<=END   &&   DATA[MID]!=ITEM){
        if(ITEM < DATA[MID]){
            END = MID - 1;
        } else{
            BEG = MID + 1;
        }
        MID = (BEG + END) / 2;
    }
    if(BEG<=END   &&   DATA[MID]==ITEM){
        LOC = MID;
    } else{
        LOC = -1;
    }
}

int main()
{
    int DATA[100];
    int LB, UB, ITEM, LOC;

    cin>>LB;
    cin>>UB;

    for(int i=LB; i<=UB; i++){
        cin>>DATA[i];
    }

    cin>>ITEM;

    BINARY(DATA, LB, UB, ITEM, LOC);

    if(LOC != -1){
        cout<<LOC;
    } else{
        cout<<"NULL";
    }

    return 0;
}