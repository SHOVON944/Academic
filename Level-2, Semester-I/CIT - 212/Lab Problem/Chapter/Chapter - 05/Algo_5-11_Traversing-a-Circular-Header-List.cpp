#include <iostream>
using namespace std;

const int MAX = 100;

void traverseCircularHeader(int INFO[], int LINK[], int START) {
    int PTR = LINK[START];

    if (PTR == START) {
        cout << "Circular list is empty." << endl;
        return;
    }

    cout << "Circular Header List: ";
    while (PTR != START) {
        cout << INFO[PTR] << " ";
        PTR = LINK[PTR];
    }
    cout << endl;
}

int main()
{
    int INFO[MAX], LINK[MAX];
    int START, n;

    cout << "Enter number of nodes (including header): ";
    cin >> n;

    cout << "Enter INFO values (header first):\n";
    for(int i = 0; i < n; i++) cin >> INFO[i];

    cout << "Enter LINK values (-1 for NULL, header should point somewhere):\n";
    for(int i = 0; i < n; i++) cin >> LINK[i];

    cout << "Enter START index (header node): ";
    cin >> START;

    traverseCircularHeader(INFO, LINK, START);

    return 0;
}

/*
*Input:
    Number of nodes: 4
    INFO: 0 10 20 30
    LINK: 1 2 3 0
    START index: 0

*Output:
    Circular Header List: 10 20 30
*/