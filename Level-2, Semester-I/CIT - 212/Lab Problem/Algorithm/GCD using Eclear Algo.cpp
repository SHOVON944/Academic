#include<bits/stdc++.h>
using namespace std;

int gcd(int a, int b){          //! TC = O(logn) 
    if(b == 0) return a;        //TODO if(a%b==0) return b;, but eita b=0 hole crash korbe...
    return gcd(b, a%b);
}

int lcm(int a, int b){
    return (a*b)/ gcd(a, b);
}


int main()
{
    cout<<gcd(12, 4)<<endl;
    cout<<lcm(12, 4)<<endl;

    return 0;
}