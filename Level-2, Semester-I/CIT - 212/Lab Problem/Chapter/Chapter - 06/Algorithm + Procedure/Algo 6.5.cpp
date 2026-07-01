#include <iostream>
using namespace std;

void QUICK(int A[], int BEG, int END, int &LOC) {
    int LEFT = BEG;
    int RIGHT = END;
    LOC = BEG;

    while (true) {
        while (A[LOC] <= A[RIGHT] && LOC != RIGHT) RIGHT--;
        if (LOC == RIGHT) return;
        if (A[LOC] > A[RIGHT]) { swap(A[LOC], A[RIGHT]); LOC = RIGHT; }

        while (A[LEFT] <= A[LOC] && LEFT != LOC) LEFT++;
        if (LOC == LEFT) return;
        if (A[LEFT] > A[LOC]) { swap(A[LEFT], A[LOC]); LOC = LEFT; }
    }
}

void quickSort(int A[], int BEG, int END) {
    if (BEG < END) {
        int LOC;
        QUICK(A, BEG, END, LOC);
        quickSort(A, BEG, LOC - 1);
        quickSort(A, LOC + 1, END);
    }
}

int main() {
    int N;

    cout << "Enter the number of elements: ";
    cin >> N;

    int A[N];
    cout << "Enter " << N << " elements of the array: ";
    for(int i = 0; i < N; i++) cin >> A[i];

    cout << endl << "The original array is: ";
    for (int i = 0; i < N; i++) cout << A[i] << " ";
    cout << endl;

    quickSort(A, 0, N - 1);

    cout << "The sorted array is: ";
    for (int i = 0; i < N; i++) cout << A[i] << " ";
    cout << endl;

    return 0;
}