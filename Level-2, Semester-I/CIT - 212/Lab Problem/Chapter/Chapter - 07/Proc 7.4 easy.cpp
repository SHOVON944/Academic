#include <iostream>
using namespace std;

void FINDB(int INFO[], int LEFT[], int RIGHT[], int ROOT, int ITEM, int &LOC, int &PAR) {

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

int main() {
    int INFO[100], LEFT[100], RIGHT[100];
    int n, ROOT, ITEM, LOC, PAR;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter INFO array:\n";
    for (int i = 1; i <= n; i++)
        cin >> INFO[i];

    cout << "Enter LEFT array:\n";
    for (int i = 1; i <= n; i++)
        cin >> LEFT[i];

    cout << "Enter RIGHT array:\n";
    for (int i = 1; i <= n; i++)
        cin >> RIGHT[i];

    cout << "Enter ROOT: ";
    cin >> ROOT;

    cout << "Enter ITEM to search: ";
    cin >> ITEM;

    FINDB(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if (LOC == 0)
        cout << "Item not found.\n";
    else {
        cout << "Item found at location = " << LOC << endl;
        cout << "Parent location = " << PAR << endl;
    }

    return 0;
}



/*
*Input:
    Enter number of nodes: 7
    Enter INFO array:
    50 30 70 20 40 60 80
    Enter LEFT array:
    2 4 6 0 0 0 0
    Enter RIGHT array:
    3 5 7 0 0 0 0
    Enter ROOT: 1
    Enter AVAIL: 8
    Enter ITEM to search: 60

*Output:
    Item found at location 6

*/








/*

#include <iostream>
using namespace std;

void FINDB(int INFO[], int LEFT[], int RIGHT[],
           int ROOT, int ITEM, int &LOC, int &PAR)
{
    int PTR = ROOT;
    PAR = 0;

    while (PTR != 0)
    {
        if (ITEM == INFO[PTR])
        {
            LOC = PTR;
            return;
        }

        PAR = PTR;

        if (ITEM < INFO[PTR])
            PTR = LEFT[PTR];
        else
            PTR = RIGHT[PTR];
    }

    LOC = 0;
}

int main()
{
    int INFO[20], LEFT[20], RIGHT[20];
    int ROOT, ITEM, LOC, PAR;

    INFO[1] = 50;
    INFO[2] = 30;
    INFO[3] = 70;
    INFO[4] = 20;
    INFO[5] = 40;
    INFO[6] = 60;
    INFO[7] = 80;

    LEFT[1] = 2; RIGHT[1] = 3;
    LEFT[2] = 4; RIGHT[2] = 5;
    LEFT[3] = 6; RIGHT[3] = 7;
    LEFT[4] = RIGHT[4] = 0;
    LEFT[5] = RIGHT[5] = 0;
    LEFT[6] = RIGHT[6] = 0;
    LEFT[7] = RIGHT[7] = 0;

    ROOT = 1;
    ITEM = 60;

    FINDB(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if (LOC == 0)
        cout << "Item not found.";
    else
    {
        cout << "Item found at location = " << LOC << endl;
        cout << "Parent location = " << PAR << endl;
    }

    return 0;
}

*/