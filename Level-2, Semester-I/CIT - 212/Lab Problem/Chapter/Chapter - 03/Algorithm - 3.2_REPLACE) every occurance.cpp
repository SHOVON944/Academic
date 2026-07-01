#include <iostream>
using namespace std;

int LENGTH(char* str){
    int len = 0;
    while(str[len] != '\0'){
        len++;
    }
    return len;
}

int INDEX(char* T, char* P){
    int n = LENGTH(T);
    int m = LENGTH(P);
    for(int i=0; i<=n-m; i++){
        int j;
        for(j=0; j<m; j++){
            if(T[i + j] != P[j]){
                break;
            }
        }
        if(j == m){
            return i;
        }
    }
    return -1;
}

void REPLACE(char* T, char* P, char* Q){
    int pos = INDEX(T, P);
    if(pos==-1){
        return;
    }

    int lenP = LENGTH(P);
    int lenQ = LENGTH(Q);
    int lenT = LENGTH(T);
    char temp[500];
    int k = 0;

    for(int i=0; i<pos; i++){
        temp[k++] = T[i];
    }
    for(int j=0; j<lenQ; j++){
        temp[k++] = Q[j];
    }
    for (int l=pos+lenP; l<lenT; l++){
        temp[k++] = T[l];
    }
    temp[k] = '\0';

    for(int i=0; i<=k; i++){
        T[i] = temp[i];
    }
}

int main()
{
    char T[500], P[50], Q[50];
    cout<<"Enter Text: ";
    cin.getline(T, 500);
    cout<<"Enter Pattern to replace: ";
    cin.getline(P, 50);
    cout<<"Enter Replacement: ";
    cin.getline(Q, 50);
    int K = INDEX(T, P);

    while(K != -1){
        REPLACE(T, P, Q);
        K = INDEX(T, P);
    }

    cout<<T<<endl;
    return 0;
}
