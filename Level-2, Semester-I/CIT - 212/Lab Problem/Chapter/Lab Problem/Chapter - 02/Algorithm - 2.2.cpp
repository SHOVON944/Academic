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


/*
*<-----------------Full Condition Code--------------->
#include <iostream>
#include <cmath>
using namespace std;

int main(){
    float A, B, C;
    cout << "Enter A: ";
    cin >> A;
    cout << "Enter B: ";
    cin >> B;
    cout << "Enter C: ";
    cin >> C;

    if(A == 0){ // Not a quadratic equation
        if(B != 0){
            float X = -C / B;
            cout << "Linear equation solution: X = " << X << endl;
        } else{
            if(C == 0){
                cout << "Infinite solutions (0 = 0)" << endl;
            } else{
                cout << "No solution" << endl;
            }
        }
        return 0;
    }

    float D = B*B - 4*A*C;

    if(D > 0){
        float X1 = (-B + sqrt(D)) / (2*A);
        float X2 = (-B - sqrt(D)) / (2*A);
        cout << "Two distinct real solutions:" << endl;
        cout << "X1 = " << X1 << endl;
        cout << "X2 = " << X2 << endl;
    } else if(D == 0){
        float X = -B / (2*A);
        cout << "One repeated real solution:" << endl;
        cout << "X = " << X << endl;
    } else{ // D < 0, complex roots
        float realPart = -B / (2*A);
        float imagPart = sqrt(-D) / (2*A);
        cout << "Two complex solutions:" << endl;
        cout << "X1 = " << realPart << " + " << imagPart << "i" << endl;
        cout << "X2 = " << realPart << " - " << imagPart << "i" << endl;
    }

    return 0;
}
*/