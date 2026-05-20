#include <iostream>
using namespace std;

const int MAX = 100;

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

int main()
{
    int INFO[MAX], LINK[MAX];
    int START, AVAIL, n, ITEM, LOC;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter INFO values:\n";
    for(int i = 0; i < n; i++)
        cin >> INFO[i];

    cout << "Enter LINK values (-1 for NULL):\n";
    for(int i = 0; i < n; i++)
        cin >> LINK[i];

    cout << "Enter START index: ";
    cin >> START;

    cout << "Enter AVAIL index: ";
    cin >> AVAIL;

    cout << "Enter LOC index (-1 if insert at beginning): ";
    cin >> LOC;

    cout << "Enter ITEM to insert: ";
    cin >> ITEM;

    INSLOC(INFO, LINK, START, AVAIL, LOC, ITEM);

    cout << "\nLinked List after insertion:\n";
    int PTR = START;
    while(PTR != -1) {
        cout << INFO[PTR] << " ";
        PTR = LINK[PTR];
    }
    cout << endl;

    return 0;
}



/*
*Input:
TODO: Insert after any node index
    Number of nodes: 5
    INFO: 10 20 30 40 50
    LINK: 1 2 3 4 -1
    START: 0
    AVAIL: 5
    LOC: 2    (insert after node 2, INFO[2] = 30)
    ITEM: 75
*Output:
    Linked list: 10 20 30 75 40 50



*Input:
TODO: Insert at beginning
    Number of nodes: 5
    INFO: 10 20 30 40 50
    LINK: 1 2 3 4 -1
    START: 0
    AVAIL: 5
    LOC: -1    (insert at beginning)
    ITEM: 75
*Output:
    Linked list: 75 10 20 30 40 50
*/