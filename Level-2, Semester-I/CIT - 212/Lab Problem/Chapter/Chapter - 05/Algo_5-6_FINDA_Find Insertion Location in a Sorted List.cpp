#include <iostream>
using namespace std;

const int MAX = 100;

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

int main()
{
    int INFO[MAX], LINK[MAX];
    int START, ITEM, LOC, n;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter INFO values (sorted):"<<endl;
    for (int i = 0; i < n; i++) cin >> INFO[i];

    cout << "Enter LINK values (-1 for NULL):"<<endl;
    for (int i = 0; i < n; i++) cin >> LINK[i];

    cout << "Enter START index: ";
    cin >> START;

    cout << "Enter ITEM to find location for insertion: ";
    cin >> ITEM;

    FINDA(INFO, LINK, START, ITEM, LOC);

    if (LOC == -1)
        cout << "Insert ITEM at beginning of list (LOC = NULL)" << endl;
    else
        cout << "Insert ITEM after node at index LOC = " << LOC << endl;

    return 0;
}


/*
*Input:
    Enter number of nodes: 5
    Enter INFO values (sorted): 10 20 30 40 50
    Enter LINK values (-1 for NULL): 1 2 3 4 -1
    Enter START index: 0
    Enter ITEM to find location for insertion: 25

*Output:
    Insert ITEM after node at index LOC = 1
*/