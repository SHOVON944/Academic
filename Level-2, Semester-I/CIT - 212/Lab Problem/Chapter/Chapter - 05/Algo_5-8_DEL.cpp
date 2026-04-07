#include <iostream>
using namespace std;

const int MAX = 100;

void DEL(int INFO[], int LINK[], int &START, int &AVAIL, int LOC, int LOCP){

    if (LOCP == -1) {
        START = LINK[START];
    } else {
        LINK[LOCP] = LINK[LOC];
    }

    LINK[LOC] = AVAIL;
    AVAIL = LOC;
}

int main()
{
    int INFO[MAX], LINK[MAX];
    int START, AVAIL, n, LOC, LOCP;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter INFO values:\n";
    for(int i = 0; i < n; i++) cin >> INFO[i];

    cout << "Enter LINK values (-1 for NULL):\n";
    for(int i = 0; i < n; i++) cin >> LINK[i];

    cout << "Enter START index: ";
    cin >> START;

    cout << "Enter AVAIL index: ";
    cin >> AVAIL;

    cout << "Enter LOC of node to delete: ";
    cin >> LOC;

    cout << "Enter LOCP (preceding node index, -1 if first node): ";
    cin >> LOCP;

    DEL(INFO, LINK, START, AVAIL, LOC, LOCP);

    cout << "\nLinked List after deletion:\n";
    int PTR = START;
    while(PTR != -1) {
        cout << INFO[PTR] << " ";
        PTR = LINK[PTR];
    }
    cout << "\nAVAIL list:\n" << AVAIL << endl;

    return 0;
}

/*
*Input:
    Enter number of nodes: 5
    Enter INFO values:
    10 20 30 40 50
    Enter LINK values (-1 for NULL):
    1 2 3 4 -1
    Enter START index: 0
    Enter AVAIL index: 5
    Enter LOC of node to delete: 0
    Enter LOCP (preceding node index, -1 if first node): -1

*Output:
    Linked List after deletion:
    20 30 40 50
    AVAIL list:
    0
*/