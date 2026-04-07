#include <iostream>
using namespace std;

const int MAX = 100;

// Function to insert ITEM at beginning of list
void INSFIRST(int INFO[], int LINK[], int &START, int &AVAIL, int ITEM) {
    if (AVAIL == -1) { // Overflow check
        cout << "OVERFLOW" << endl;
        return;
    }

    int NEW = AVAIL;         // Step 2: Remove first node from AVAIL
    AVAIL = LINK[AVAIL];     // Update AVAIL

    INFO[NEW] = ITEM;        // Step 3: Insert data
    LINK[NEW] = START;       // Step 4: Link new node to first node
    START = NEW;             // Step 5: Update START
}

int main() {
    int INFO[MAX], LINK[MAX];
    int START, AVAIL, n, ITEM;

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

    cout << "Enter ITEM to insert at first: ";
    cin >> ITEM;

    INSFIRST(INFO, LINK, START, AVAIL, ITEM);

    cout << "\nLinked List after insertion:\n";
    int PTR = START;
    while(PTR != -1) {
        cout << INFO[PTR] << " ";
        PTR = LINK[PTR];
    }
    cout << endl;

    return 0;
}