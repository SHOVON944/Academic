#include <bits/stdc++.h>
using namespace std;

int binary_Search(vector<int> &a, int tar){
    int st = 0;
    int end = a.size() - 1;
    while(st<=end){
        int mid = st + (end - st) / 2;     // avoid overflow...(st+end) / 2 korle overflow hoi jdi st, end big value newa hoi thle
        if(tar==a[mid]) {
            return mid;
        } else if(tar<a[mid]){
            end = mid - 1;
        } else{
            st = mid + 1;
        }
    }
    return -1;
}

int main()
{
    int n;
    cin >> n;
    vector<int> a(n);
    for(int i=0; i<n; i++)cin >> a[i];

    int target;
    cin >> target;
    int idx = binary_Search(a, target);
    cout << idx << endl;

    return 0;
}
