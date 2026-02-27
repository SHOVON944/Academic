#include <iostream>
using namespace std;

int LENGTH(char* str){
    int len = 0;
    while(str[len] != '\0'){
        len++;
    }
    return len;
}

int main()
{
    char T[100], P[100];
    int S, R, INDEX = 0;

    cout<<"Enter length of Text (S):";
    cin.getline(T+1, 100);
    cout<<"Enter length of Pattern (R):";
    cin.getline(P+1, 100);
    S = LENGTH(T+1);
    R = LENGTH(P+1);

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


// <------------- 0-based index --------------->
/*
#include <iostream>
using namespace std;

int LENGTH(char* str){
    int len = 0;
    while(str[len] != '\0'){
        len++;
    }
    return len;
}

int main()
{
    char T[100], P[100];
    int S, R, INDEX = 0;

    cout<<"Enter length of Text (S):";
    cin.getline(T, 100);
    cout<<"Enter length of Pattern (R):";
    cin.getline(P, 100);
    S = LENGTH(T);
    R = LENGTH(P);

    int K = 0;
    int MAX = S - R;
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

*/