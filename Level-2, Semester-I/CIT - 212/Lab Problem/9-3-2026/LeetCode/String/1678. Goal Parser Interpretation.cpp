#include<bits/stdc++.h>
using namespace std;


class Solution {
public:
    string interpret(string command) {
        string result;
        for(int i=0; i<command.size(); i++){
            if(command[i]=='G'){
                result += 'G';
            } else if(command[i]=='('   &&  command[i+1]==')'){
                result += 'o';
                i++;
            } else{
                result += "al";
                i += 3;
            }
        }
        return result;
    }
};

int main()
{


    return 0;
}