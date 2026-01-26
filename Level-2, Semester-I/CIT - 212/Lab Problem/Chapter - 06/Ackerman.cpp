#include <iostream>
using namespace std;

unsigned long long ackermann(unsigned int m, unsigned int n){
    if(m==0) return n + 1;
    else if(n==0) return ackermann(m - 1, 1);
    else return ackermann(m - 1, ackermann(m, n - 1));
}

int main()
{
    int m, n;
    cout<<"Enter m and n: ";
    cin>>m>>n;

    cout<<"Ackermann("<<m<<", "<<n<<") = " 
        <<ackermann(m, n)<<endl;

    return 0;
}
