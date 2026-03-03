#include <bits/stdc++.h>
using namespace std;

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

int partition(int arr[], int st, int end){
    int idx = st - 1;
    int pivot = arr[end];

    for(int j = st; j < end; j++){
        if(arr[j] <= pivot){
            idx++;
            swap(arr[idx], arr[j]);
        }
    }

    idx++;
    swap(arr[idx], arr[end]);
    return idx;
}

void quickSort(int arr[], int n){

    int STACK[100];     // manual stack
    int TOP = -1;
    int MAXSTK = 100;

    // initial range push (start, end)
    PUSH(STACK, TOP, MAXSTK, 0);
    PUSH(STACK, TOP, MAXSTK, n-1);

    while(TOP != -1){

        int end, start;

        POP(STACK, TOP, end);
        POP(STACK, TOP, start);

        int pivIdx = partition(arr, start, end);

        // Left part
        if(start < pivIdx - 1){
            PUSH(STACK, TOP, MAXSTK, start);
            PUSH(STACK, TOP, MAXSTK, pivIdx - 1);
        }

        // Right part
        if(pivIdx + 1 < end){
            PUSH(STACK, TOP, MAXSTK, pivIdx + 1);
            PUSH(STACK, TOP, MAXSTK, end);
        }
    }
}

int main()
{
    int arr[] = {2, 31, 35, 8, 32, 17};
    int n = sizeof(arr)/ sizeof(arr[0]);

    quickSort(arr, n);

    cout << "Sorted Array: ";
    for(int val : arr){
        cout << val << " ";
    }

    return 0;
}