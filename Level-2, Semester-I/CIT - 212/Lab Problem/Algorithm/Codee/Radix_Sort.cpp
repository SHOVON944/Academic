#include<bits/stdc++.h>
using namespace std;

void countSort(vector<int> &arr, int pos){
    int n = arr.size();
    // creat frequency array
    vector<int> freq(10, 0);
    for(int i=0; i<n; i++){
        freq[(arr[i]/pos)%10]++;
    }
    // calculating cumulative freq
    for(int i=1; i<10; i++){
        freq[i] += freq[i-1];
    }
    // ans array
    vector<int> ans(n);
    for(int i=n-1; i>=0; i--){
        ans[--freq[(arr[i]/pos)%10]] = arr[i];
    }
    for(int i=0; i<n; i++){
        arr[i] = ans[i];
    }
}

void radixSort(vector<int> &v){
    int max_ele = INT_MIN;
    for(auto x : v){
        max_ele = max(max_ele, x);
    }
    for(int pos=1; max_ele/pos>0; pos*=10){
        countSort(v, pos);
    }
}

int main()
{
    vector<int> arr = {4, 123, 24, 2, 43, 134, 131, 71, 1};
    radixSort(arr);

    for (int i=0; i<arr.size(); i++) {
        cout << arr[i] << " ";
    }

    return 0;
}