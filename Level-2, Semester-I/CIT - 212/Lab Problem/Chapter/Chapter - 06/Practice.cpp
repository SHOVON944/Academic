#include <iostream>
using namespace std;

int main() {
    int INFO[10], LINK[10];
    int START, ITEM, LOC, PTR, n;

    // Input
    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter INFO values:\n";
    for(int i = 0; i < n; i++) {
        cin >> INFO[i];
    }

    cout << "Enter LINK values (-1 for NULL):\n";
    for(int i = 0; i < n; i++) {
        cin >> LINK[i];
    }

    cout << "Enter START index: ";
    cin >> START;

    cout << "Enter ITEM to search: ";
    cin >> ITEM;

    // Algorithm 5.2 শুরু
    PTR = START;

    while (PTR != -1) {
        if (INFO[PTR] == ITEM) {
            LOC = PTR;
            break;
        }
        else {
            PTR = LINK[PTR];
        }
    }

    if (PTR == -1)
        LOC = -1;

    // Output
    if (LOC != -1)
        cout << "Item found at index: " << LOC << endl;
    else
        cout << "Item not found\n";

    return 0;
}