#include <iostream>
using namespace std;

int main()
{
    int LA[100];
    int LB, UB;
    cin>>LB;
    cin>>UB;
    for(int i=LB; i<=UB; i++){
        cin>>LA[i];
    }

    int K = LB;
    while(K<=UB){
        cout<<LA[K]<<" ";
        K = K + 1;
    }

    return 0;
}
