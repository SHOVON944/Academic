#include <iostream>
using namespace std;

const int MAX = 100;

// ---------- Algo 5.6 : FINDA ----------
void FINDA(int INFO[], int LINK[], int START, int ITEM, int &LOC){
    if (START == -1) {
        LOC = -1;
        return;
    }

    if (ITEM < INFO[START]) {
        LOC = -1;
        return;
    }

    int SAVE = START;
    int PTR = LINK[START];

    while (PTR != -1) {
        if (ITEM < INFO[PTR]) {
            LOC = SAVE;
            return;
        }
        SAVE = PTR;
        PTR = LINK[PTR];
    }

    LOC = SAVE;
}

// ---------- Algo 5.5 : INSLOC ----------
void INSLOC(int INFO[], int LINK[], int &START, int &AVAIL, int LOC, int ITEM) {
    if (AVAIL == -1) {
        cout << "OVERFLOW" << endl;
        return;
    }

    int NEW = AVAIL;
    AVAIL = LINK[AVAIL];
    INFO[NEW] = ITEM;

    if (LOC == -1) {
        LINK[NEW] = START;
        START = NEW;
    } else {
        LINK[NEW] = LINK[LOC];
        LINK[LOC] = NEW;
    }
}

// ---------- Algo 5.7 : INSSRT ----------
void INSSRT(int INFO[], int LINK[], int &START, int &AVAIL, int ITEM){
    int LOC;

    // Step 1: Use Procedure 5.6 (FINDA) to find the location of the
    //         node preceding ITEM
    FINDA(INFO, LINK, START, ITEM, LOC);

    // Step 2: Use Algorithm 5.5 (INSLOC) to insert ITEM after node LOC
    INSLOC(INFO, LINK, START, AVAIL, LOC, ITEM);

    // Step 3: Exit
}

int main()
{
    int INFO[MAX], LINK[MAX];
    int START, AVAIL, n, ITEM;

    cout << "Enter the number of nodes: ";
    cin >> n;

    cout << "Enter the INFO values (sorted): ";
    for(int i = 0; i < n; i++)
        cin >> INFO[i];

    cout << "Enter the LINK values (-1 for NULL): ";
    for(int i = 0; i < n; i++)
        cin >> LINK[i];

    cout << "Enter the START index: ";
    cin >> START;

    cout << "Enter the AVAIL index: ";
    cin >> AVAIL;

    cout << "Enter the ITEM to insert: ";
    cin >> ITEM;

    INSSRT(INFO, LINK, START, AVAIL, ITEM);

    cout << "The Linked List after insertion is: ";
    int PTR = START;
    while(PTR != -1) {
        cout << INFO[PTR] << " ";
        PTR = LINK[PTR];
    }
    cout << endl;

    return 0;
}