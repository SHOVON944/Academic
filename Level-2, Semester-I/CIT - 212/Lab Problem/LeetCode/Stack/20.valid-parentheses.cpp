/*
 * @lc app=leetcode id=20 lang=cpp
 *
 * [20] Valid Parentheses
 */

// @lc code=start
class Solution {
public:
    bool isValid(string s) {
        stack<char> st;
        for(int ch : s){
            if(ch=='('  ||  ch=='{'  || ch=='['){
                st.push(ch);
            }
            else{
                if(st.empty()) return false;
                else if( (ch == ')' && st.top() == '(') ||
                    (ch == '}' && st.top() == '{') ||
                    (ch == ']' && st.top() == '[') ){
                    st.pop();
                } else{
                    return false;
                }
            }
        }
        return st.empty();
    }
};
// @lc code=end
