#include<bits/stdc++.h>
using namespace std;








// class Solution {
// public:
//     bool isPalindrome(string s) {
//         string s_store;
//         for(char ch : s){
//             if(isalnum(ch)){
//                 s_store += tolower(ch);
//             }
//         }
//         string rev = s_store;
//         int l = 0;
//         int r = s_store.size() - 1;
//         while(l<r) swap(rev[l++], rev[r--]);
        
//         return rev == s_store;
//     }
// };


// Efficient code
class Solution {
public:
    bool isPalindrome(string s) {
        int l = 0;
        int r = s.size() - 1;
        while(l<r){
            while(l<r   &&   !isalnum(s[l])){
                l++;
            }
            while(l<r   &&   !isalnum(s[r])){
                r--;
            }
            if(tolower(s[l])  !=  tolower(s[r])){
                return false;
            }

            l++;
            r--;
        }
        
        return true;
    }
};


int main()
{


    return 0;
}