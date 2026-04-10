#include<bits/stdc++.h>
using namespace std;

int gcd(int a, int b){          //! TC = O(logn) 
    if(a%b==0) return b;        //TODO if(b==0) return a;
    return gcd(b, a%b);
}

int lcm(int a, int b){
    return (a*b)/ gcd(a, b);
}


int main()
{
    cout<<gcd(12, 4)<<endl;
    cout<<lcm(12, 4)<<endl;
    cout<<__gcd(12, 18)<<endl;

    return 0;
}