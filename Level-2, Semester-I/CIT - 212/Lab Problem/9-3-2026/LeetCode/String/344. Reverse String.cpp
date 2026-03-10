#include<bits/stdc++.h>
using namespace std;

/*
 * @lc app=leetcode id=344 lang=cpp
 *
 * [344] Reverse String
 */

// @lc code=start
class Solution {
public:
    void reverseString(vector<char>& s) {
        int st = 0;
        int ls = s.size() - 1;
        while(st<=ls){
            swap(s[st++], s[ls--]);
        }
    }
};
// @lc code=end



int main()
{


    return 0;
}