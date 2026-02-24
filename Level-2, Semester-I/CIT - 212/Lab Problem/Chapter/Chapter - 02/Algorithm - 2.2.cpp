#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    float A, B, C, D, X1, X2;
    cout<<" Enter A: ";
    cin>>A;
    cout<<" Enter B: ";
    cin>>B;
    cout<<" Enter C: ";
    cin>>C;

    D = B*B - 4*A*C;

    if(D>0){
        X1 = (-B+sqrt(D)) / (2*A);
        X2 = (-B-sqrt(D)) / (2*A);
        cout<<"Two real solutions:"<<endl;
        cout<<"X1 = "<<X1<<endl;
        cout<<"X2 = "<<X2<<endl;
    } else{
        cout<<"NO REAL SOLUTIONS"<<endl;
    }

    return 0;
}
