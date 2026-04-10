#include<bits/stdc++.h>
using namespace std;

void merge(vector<int> &v, int st, int mid, int end){
    vector<int> temp;
    int i = st;
    int j = mid+1;
    while(i<=mid  &&  j<=end){
        if(v[i]<=v[j]){
            temp.push_back(v[i++]);
        } else{
            temp.push_back(v[j++]);
        }
    }
    while(i<=mid){
        temp.push_back(v[i++]);
    }
    while(j<=end){
        temp.push_back(v[j++]);
    }
    for(int i=0; i<temp.size(); i++) {
        v[i+st] = temp[i];
    }
}

void merge_Sort(vector<int> &v, int st, int end){
    if(st<end){
        int mid = st + (end - st)/2;
        merge_Sort(v, st, mid);
        merge_Sort(v, mid+1, end);

        merge(v, st, mid, end);
    }
}

int main()
{
    // int n;
    // cin>>n
    vector<int> num = {12, 31, 35, 8, 32, 17};

    cout<<"Original Array: ";
    for(int i=0; i<6; i++) {
        cout<<num[i]<<" ";
    }
    cout<<endl;

    merge_Sort(num, 0, 6-1);

    cout<<"Sorted Array: ";
    for(int i=0; i<6; i++){
        cout<<num[i]<<" ";
    }

    return 0;
}