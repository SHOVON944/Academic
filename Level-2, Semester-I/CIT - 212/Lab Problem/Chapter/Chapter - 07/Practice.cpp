#include <iostream>
using namespace std;

const int MAX = 100;
const int NULL_PTR = 0;

// FIND (Algorithm 7.4)
void FIND(int INFO[], int LEFT[], int RIGHT[], int ROOT,
          int ITEM, int &LOC, int &PAR) {

    int PTR = ROOT;
    PAR = 0;

    while (PTR != 0) {
        if (ITEM == INFO[PTR]) {
            LOC = PTR;
            return;
        }

        PAR = PTR;

        if (ITEM < INFO[PTR])
            PTR = LEFT[PTR];
        else
            PTR = RIGHT[PTR];
    }

    LOC = 0; // not found
}

// INSBST (Algorithm 7.5)
void INSBST(int INFO[], int LEFT[], int RIGHT[],
            int &ROOT, int &AVAIL,
            int ITEM, int &LOC) {

    int PAR, NEW;

    FIND(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if (LOC != 0) {
        cout << "Item already exists\n";
        return;
    }

    if (AVAIL == 0) {
        cout << "OVERFLOW\n";
        return;
    }

    NEW = AVAIL;
    AVAIL = LEFT[AVAIL];

    INFO[NEW] = ITEM;
    LEFT[NEW] = 0;
    RIGHT[NEW] = 0;

    LOC = NEW;

    if (PAR == 0)
        ROOT = NEW;
    else if (ITEM < INFO[PAR])
        LEFT[PAR] = NEW;
    else
        RIGHT[PAR] = NEW;
}

void PREORDER(int INFO[], int LEFT[], int RIGHT[], int ROOT){
    if(ROOT == 0) return;

    PREORDER(INFO, LEFT, RIGHT, LEFT[ROOT]);
    cout << INFO[ROOT] << " ";
    PREORDER(INFO, LEFT, RIGHT, RIGHT[ROOT]);
}

int main() {
    int INFO[MAX], LEFT[MAX], RIGHT[MAX];
    int ROOT = 0, AVAIL = 1;

    for (int i = 1; i < MAX - 1; i++)
        LEFT[i] = i + 1;
    LEFT[MAX - 1] = 0;

    int n, item, loc;

    cout << "Enter number of elements: ";
    cin >> n;

    for (int i = 0; i < n; i++) {
        cout << "Enter item: ";
        cin >> item;

        INSBST(INFO, LEFT, RIGHT, ROOT, AVAIL, item, loc);
    }

    cout << "\nPreorder Traversal of BST: ";
    PREORDER(INFO, LEFT, RIGHT, ROOT);

    return 0;
}