#include<bits/stdc++.h>
using namespace std;

int main()
{
    vector<int> num = {2, 3, 1, 4, 5, 7, 6};
    int n = num.size();
    for(int i=0; i<n-1; i++){
        for(int j=0; j<n-i-1; j++){
            if(num[j]>num[j+1]){
                int temp = num[j];
                num[j] = num[j+1];
                num[j+1] = temp;
            }
        }
    }
    for(int i : num){
        cout<<i<<" ";
    }

    return 0;
}