#include <bits/stdc++.h>
using namespace std;

// ---------- STACK FUNCTIONS (তোমার দেওয়া) ----------
void PUSH(int STACK[], int &TOP, int MAXSTK, int ITEM){
    if (TOP == MAXSTK - 1){
        cout << "OVERFLOW" << endl;
        return;
    }
    TOP = TOP + 1;
    STACK[TOP] = ITEM;
}

void POP(int STACK[], int &TOP, int &ITEM){
    if (TOP == -1){
        cout << "UNDERFLOW" << endl;
        return;
    }
    ITEM = STACK[TOP];
    TOP = TOP - 1;
}

// ---------- PARTITION (same as তোমার) ----------
int partition(vector<int> &arr, int st, int end) {
    int idx = st - 1;
    int pivot = arr[end];

    for(int j = st; j < end; j++) {
        if(arr[j] <= pivot) {
            idx++;
            swap(arr[j], arr[idx]);
        }
    }
    idx++;
    swap(arr[end], arr[idx]);
    return idx;
}

// ---------- ITERATIVE QUICK SORT USING STACK ----------
void quickSort(vector<int> &arr) {
    int n = arr.size();
    int STACK[100];      // stack array
    int TOP = -1;

    // প্রথম range push
    PUSH(STACK, TOP, 100, 0);
    PUSH(STACK, TOP, 100, n - 1);

    while(TOP != -1) {
        int end, st;

        POP(STACK, TOP, end);
        POP(STACK, TOP, st);

        if(st < end) {
            int p = partition(arr, st, end);

            // left part
            if(p - 1 > st) {
                PUSH(STACK, TOP, 100, st);
                PUSH(STACK, TOP, 100, p - 1);
            }

            // right part
            if(p + 1 < end) {
                PUSH(STACK, TOP, 100, p + 1);
                PUSH(STACK, TOP, 100, end);
            }
        }
    }
}

// ---------- MAIN ----------
int main() {
    vector<int> arr = {2, 31, 35, 8, 32, 17};

    quickSort(arr);

    for(int val : arr) {
        cout << val << " ";
    }
    cout << endl;

    return 0;
}
