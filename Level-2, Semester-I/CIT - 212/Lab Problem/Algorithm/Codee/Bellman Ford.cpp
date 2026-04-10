#include <bits/stdc++.h>
using namespace std;

void selectionSort(vector<int>& arr){
    int n = arr.size();
    for(int i = 0 ; i < n-1 ; i++) {
    bool flag = false;
    for(int j = 0 ; j < n-i-1; j++) {
        if(arr[j] > arr[j+1]) {
            flag=true;
            swap(arr[j],arr[j+1]);
        }
    }
    if(!flag) break;
    }
}

int main()
{
    vector<int> arr = {5, 2, 4, 6, 1, 3};
    selectionSort(arr);
    cout << "Selection Sort: ";
    for (int num : arr)
        cout << num << " ";
    return 0;
}
