#include <iostream>
using namespace std;

const int MAX = 100;

void SRCHHL(int INFO[], int LINK[], int START, int ITEM, int &LOC) {
    int PTR = LINK[START];  // first ordinary node

    if (PTR == START) { // empty list
        LOC = -1;
        return;
    }

    while (INFO[PTR] != ITEM && PTR != START) {
        PTR = LINK[PTR];
    }

    if (INFO[PTR] == ITEM)
        LOC = PTR;
    else
        LOC = -1;
}

int main() {
    int INFO[MAX], LINK[MAX];
    int START, n, ITEM, LOC;

    cout << "Enter number of nodes (including header): ";
    cin >> n;

    cout << "Enter INFO values (header first):\n";
    for(int i = 0; i < n; i++) cin >> INFO[i];

    cout << "Enter LINK values:\n";
    for(int i = 0; i < n; i++) cin >> LINK[i];

    cout << "Enter START index (header node): ";
    cin >> START;

    cout << "Enter ITEM to search: ";
    cin >> ITEM;

    SRCHHL(INFO, LINK, START, ITEM, LOC);

    if (LOC != -1)
        cout << "ITEM found at location: " << LOC << endl;
    else
        cout << "ITEM not found in circular list." << endl;

    return 0;
}

/*
*Input:
    Number of nodes: 4
    INFO: 0 10 20 30
    LINK: 1 2 3 0
    START index: 0
    ITEM to search: 20

*Output:
    ITEM found at location: 2
*/