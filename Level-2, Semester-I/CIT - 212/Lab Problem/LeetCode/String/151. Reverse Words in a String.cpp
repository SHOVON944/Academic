#include<iostream>
using namespace std;

class Solution {
public:

    void swap(char &x, char &y){
        char temp = x;
        x = y;
        y = temp;
    }

    void reverse(string &s, int i, int j){
        while(i<j){
            swap(s[i++], s[j--]);
        }
    }

    string reverseWords(string s) {
        int n = s.size();
        int i = 0;
        int j = 0;
        while(i<n){
            while(i<n   &&   s[i]==' '){
                i++;
            }
            while(i<n   &&   s[i]!=' '){
                s[j++] = s[i++];
            }
            while(i<n   &&   s[i]==' '){
                i++;
            }
            if(i<n){
                s[j++] = ' ';
            }
        }

        n = j;
        reverse(s, 0, n-1);
            int st_l;
            bool start_length = false;
        for(int k=0; k<n; k++){
            if(s[k]!=' '){
                if(!start_length){
                    st_l = k;
                    start_length = true;
                }
            } else{
                reverse(s, st_l, k-1);
                start_length = false;
            }
        }
        if(start_length){
            reverse(s, st_l, n-1);
        }
        s.resize(n);
        return s;
    }
};


int main()
{
    string ab = "the sky is blue";
    Solution obj;
    cout << obj.reverseWords(ab) << endl;
    return 0;
}