#include <iostream>
using namespace std;

void FIND(int INFO[], int LEFT[], int RIGHT[], int ROOT, int ITEM, int &LOC, int &PAR)
{
    int PTR, SAVE;

    if (ROOT == 0)
    {
        LOC = 0;
        PAR = 0;
        return;
    }

    if (ITEM == INFO[ROOT])
    {
        LOC = ROOT;
        PAR = 0;
        return;
    }

    if (ITEM < INFO[ROOT])
    {
        PTR = LEFT[ROOT];
        SAVE = ROOT;
    }
    else
    {
        PTR = RIGHT[ROOT];
        SAVE = ROOT;
    }

    while (PTR != 0)
    {
        if (ITEM == INFO[PTR])
        {
            LOC = PTR;
            PAR = SAVE;
            return;
        }

        SAVE = PTR;

        if (ITEM < INFO[PTR])
            PTR = LEFT[PTR];
        else
            PTR = RIGHT[PTR];
    }

    LOC = 0;
    PAR = SAVE;
}

void INSBST(int INFO[], int LEFT[], int RIGHT[],
            int &ROOT, int &AVAIL, int ITEM, int &LOC)
{
    int PAR, NEW;

    FIND(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if (LOC != 0)
        return;

    if (AVAIL == 0)
    {
        cout << "OVERFLOW";
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

void INORD(int INFO[], int LEFT[], int RIGHT[], int ROOT)
{
    if (ROOT == 0)
        return;

    INORD(INFO, LEFT, RIGHT, LEFT[ROOT]);
    cout << INFO[ROOT] << " ";
    INORD(INFO, LEFT, RIGHT, RIGHT[ROOT]);
}

int main()
{
    int INFO[100], LEFT[100], RIGHT[100];
    int n, ROOT, AVAIL, ITEM, LOC;

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

    cout << "Enter AVAIL: ";
    cin >> AVAIL;

    cout << "Enter ITEM to insert: ";
    cin >> ITEM;

    INSBST(INFO, LEFT, RIGHT, ROOT, AVAIL, ITEM, LOC);

    cout << "Inorder Traversal of BST: ";
    INORD(INFO, LEFT, RIGHT, ROOT);

    return 0;
}



/*
*Input:

    Enter number of nodes: 6

    Enter INFO array:
    40 33 60 11 50 55

    Enter LEFT array:
    2 4 5 0 0 0

    Enter RIGHT array:
    3 0 6 0 0 0

    Enter ROOT: 1

    Enter AVAIL: 7

    Enter ITEM to insert: 70

*Output:

    Inorder Traversal of BST: 11 33 40 50 55 60 70

*/