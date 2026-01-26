#include <iostream>
using namespace std;

void QUICK(int A[], int BEG, int END, int &LOC) {
    int LEFT = BEG;
    int RIGHT = END;
    LOC = BEG;

    while (true) {

        // Step 2: Scan Right to Left
        while (A[LOC] <= A[RIGHT] && LOC != RIGHT)
            RIGHT--;

        if (LOC == RIGHT)
            return;

        if (A[LOC] > A[RIGHT]) {
            swap(A[LOC], A[RIGHT]);
            LOC = RIGHT;
        }

        // Step 3: Scan Left to Right
        while (A[LEFT] <= A[LOC] && LEFT != LOC)
            LEFT++;

        if (LOC == LEFT)
            return;

        if (A[LEFT] > A[LOC]) {
            swap(A[LEFT], A[LOC]);
            LOC = LEFT;
        }
    }
}

// Full Quick Sort using Procedure 6.5
void quickSort(int A[], int BEG, int END) {
    if (BEG < END) {
        int LOC;
        QUICK(A, BEG, END, LOC);
        quickSort(A, BEG, LOC - 1);
        quickSort(A, LOC + 1, END);
    }
}

int main() {
    int A[] = {44, 33, 11, 55, 77, 90, 40, 60, 99, 22};
    int N = 10;

    cout << "Original Array:\n";
    for (int i = 0; i < N; i++) cout << A[i] << " ";

    quickSort(A, 0, N - 1);

    cout << "\n\nSorted Array:\n";
    for (int i = 0; i < N; i++) cout << A[i] << " ";

    return 0;
}
