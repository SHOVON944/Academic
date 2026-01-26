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

    for(int L=LB; L<=UB; L++){
        cout<<LA[L]<<endl;
    }

    return 0;
}
