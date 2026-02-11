#include<bits/stdc++.h>
using namespace std;


class Solution {
public:
    int lengthOfLastWord(string s) {
        int length = 0;
        bool word_found = false;
        for(int i=s.size()-1; i>=0; i--){
            if(s[i]==' '   &&   word_found){
                break;
            } else if(s[i]!=' '){
                length++;
                word_found = true;
            }
        }
        return length;
    }
};

int main()
{


    return 0;
}