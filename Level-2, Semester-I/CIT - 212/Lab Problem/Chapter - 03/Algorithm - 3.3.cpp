#include <iostream>
using namespace std;

int main()
{
    char T[100], P[100];
    int S, R, INDEX = 0;

    cout<<"Enter length of Text (S): ";
    cin>>S;
    cout<<"Enter Text T (1-based): ";
    for(int i=1; i<=S; i++){
        cin>>T[i];
    }
    cout<<"Enter length of Pattern (R): ";
    cin>>R;
    cout<<"Enter Pattern P (1-based): ";
    for(int i=1; i<=R; i++){
        cin>>P[i];
    }
    int K = 1;
    int MAX = S - R + 1;
    while(K<=MAX){
        int L;
        for(L=1; L<=R; L++){
            if (P[L]   !=   T[K+L-1]){
                break;
            }
        }
        if(L>R){
            INDEX = K;
            cout<<"Pattern found at position: "<<INDEX<<endl;
            return 0;
        }
        K = K + 1;
    }

    INDEX = 0;
    cout<<"Pattern not found. INDEX = "<<INDEX<<endl;
    return 0;
}
