#include <bits/stdc++.h>
using namespace std;

int binary_Search_Rec(const vector<int> &a, int tar, int st, int end) {
    while(st<=end){
        int mid = st + (end - st) / 2; // Overflow safe
        if (a[mid] == tar){
            return mid;
        } else if (tar < a[mid]){
            return binary_Search_Rec(a, tar, st, mid-1);
        } else{
            return binary_Search_Rec(a, tar, mid+1, end);
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
    int idx = binary_Search_Rec(a, target, 0, n - 1);
    cout << idx << endl; // 0-based index
}
