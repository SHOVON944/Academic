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

void DELETE(char* T, int pos, int len){
    int n = LENGTH(T);
    int i;

    for(i=pos; i+len<n; i++){
        T[i] = T[i + len];
    }
    T[i] = '\0';
}

int main()
{
    char T[200];
    char P[100];
    cout<<"Enter text: ";
    cin.getline(T, 200);
    cout<<"Enter pattern: ";
    cin.getline(P, 100);

    int K = INDEX(T, P);

    while(K != -1){
        DELETE(T, K, LENGTH(P));
        K = INDEX(T, P);
    }
    cout<<T<<endl;

    return 0;
}
