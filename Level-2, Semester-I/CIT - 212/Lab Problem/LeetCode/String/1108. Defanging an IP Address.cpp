/*
class Solution {
public:
    string defangIPaddr(string address) {
       string store;
       for(auto ch : address){
        if(ch=='.') store += "[.]";
        else store += ch;
       } 
       return store;
    }
};
*/
#include<bits/stdc++.h>
using namespace std;

// more efficient
class Solution {
public:
    string defangIPaddr(string address) {
       string store;
       store.reserve(address.size() + 6);
       for(auto ch : address){
        if(ch=='.') store += "[.]";
        else store += ch;
       } 
       return store;
    }
};

int main()
{


    return 0;
}
