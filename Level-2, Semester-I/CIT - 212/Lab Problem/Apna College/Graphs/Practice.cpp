#include <bits/stdc++.h>
using namespace std;

// 🔥 JSON বানানোর function (Visualizer এর জন্য)
string to_json(vector<int> &arr) {
    string s = "{ \"kind\": { \"grid\": true }, \"rows\": [";
    for (int i = 0; i < arr.size(); i++) {
        s += "{ \"value\": " + to_string(arr[i]) + " }";
        if (i != arr.size() - 1) s += ",";
    }
    s += "] }";
    return s;
}

// (optional) pivot দেখানোর জন্য
string pivot_json(int pivot) {
    return "{ \"kind\": { \"text\": true }, \"text\": \"Pivot: " + to_string(pivot) + "\" }";
}

int partition(vector<int> &arr, int st, int end) {
    int pivot = arr[end];
    int idx = st - 1;

    for (int j = st; j < end; j++) {
        if (arr[j] <= pivot) {
            idx++;
            swap(arr[j], arr[idx]);  // 🔴 breakpoint দিস এখানে
        }
    }

    idx++;
    swap(arr[idx], arr[end]); // 🔴 breakpoint দিস এখানে

    return idx;
}

void quickSort(vector<int> &arr, int st, int end) {
    if (st < end) {
        int pivIdx = partition(arr, st, end);
        quickSort(arr, st, pivIdx - 1);
        quickSort(arr, pivIdx + 1, end);
    }
}

int main() {
    vector<int> arr = {2, 31, 35, 8, 32, 17};

    quickSort(arr, 0, arr.size() - 1);

    cout << "Sorted: ";
    for (int x : arr) cout << x << " ";
    cout << endl;

    return 0;
}