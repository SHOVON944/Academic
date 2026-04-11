/*
███████╗██╗  ██╗ ██████╗ ██╗   ██╗ ██████╗ ███╗   ██╗
██╔════╝██║  ██║██╔═══██╗██║   ██║██╔═══██╗████╗  ██║
███████╗███████║██║   ██║██║   ██║██║   ██║██╔██╗ ██║
╚════██║██╔══██║██║   ██║╚██╗ ██╔╝██║   ██║██║╚██╗██║
███████║██║  ██║╚██████╔╝ ╚████╔╝ ╚██████╔╝██║ ╚████║
╚══════╝╚═╝  ╚═╝ ╚═════╝   ╚═══╝   ╚═════╝ ╚═╝  ╚═══╝
*/

/*
void FIND(int INFO[], int LEFT[], int RIGHT[], int ROOT, int ITEM, int &LOC, int &PAR) {

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
*/

#include <iostream>
using namespace std;

const int NULL_PTR = 0;
const int MAX = 100;

void FIND(int INFO[], int LEFT[], int RIGHT[], int ROOT, int ITEM, int &LOC, int &PAR){
    int PTR, SAVE;

    if(ROOT == NULL_PTR){
        LOC = NULL_PTR;
        PAR = NULL_PTR;
        return;
    }

    if(ITEM == INFO[ROOT]){
        LOC = ROOT;
        PAR = NULL_PTR;
        return;
    }

    if(ITEM < INFO[ROOT]){
        PTR = LEFT[ROOT];
        SAVE = ROOT;
    } else{
        PTR = RIGHT[ROOT];
        SAVE = ROOT;
    }

    while(PTR != NULL_PTR){
        if(ITEM == INFO[PTR]){
            LOC = PTR;
            PAR = SAVE;
            return;
        }

        if(ITEM < INFO[PTR]){
            SAVE = PTR;
            PTR = LEFT[PTR];
        } else{
            SAVE = PTR;
            PTR = RIGHT[PTR];
        }
    }

    LOC = NULL_PTR;
    PAR = SAVE;
}

int main()
{
    int INFO[MAX];
    int LEFT[MAX] = {0};
    int RIGHT[MAX] = {0};

    int ROOT, LOC, PAR, ITEM;

    // BST Example
    //         38
    //       /    \
    //     14      45
    //    /  \       \
    //   8   23      56

    ROOT = 1;

    INFO[1] = 38; LEFT[1] = 2; RIGHT[1] = 3;
    INFO[2] = 14; LEFT[2] = 4; RIGHT[2] = 5;
    INFO[3] = 45; LEFT[3] = 0; RIGHT[3] = 6;
    INFO[4] = 8;  LEFT[4] = 0; RIGHT[4] = 0;
    INFO[5] = 23; LEFT[5] = 0; RIGHT[5] = 0;
    INFO[6] = 56; LEFT[6] = 0; RIGHT[6] = 0;

    cout << "Enter ITEM to search: ";
    cin >> ITEM;

    FIND(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if(LOC != 0){
        cout << "ITEM found at location: " << LOC << endl;
        cout << "Parent location: " << PAR << endl;
    } else{
        cout << "ITEM not found.";
        cout << "Can be inserted under parent location: " << PAR << endl;
    }

    return 0;
}


/*
*Input:
    Enter number of nodes: 6
    Enter ROOT index: 1

    Enter INFO, LEFT, RIGHT for each node:
    Node 1 -> 38 2 3
    Node 2 -> 14 4 5
    Node 3 -> 45 0 6
    Node 4 -> 8 0 0
    Node 5 -> 23 0 0
    Node 6 -> 56 0 0

    Enter ITEM to search: 23
    Enter ITEM to search: 20 (2nd input)

*Output:
    ITEM found at location: 5
    Parent location: 2

    (2nd input -> output)
    ITEM not found.
    Can be inserted under parent location: 5


int main()
{
    int INFO[MAX];
    int LEFT[MAX];
    int RIGHT[MAX];

    int ROOT, LOC, PAR, ITEM, n;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter ROOT index: ";
    cin >> ROOT;

    cout << "Enter INFO, LEFT, RIGHT for each node:";
    for(int i = 1; i <= n; i++){
        cout << "Node " << i << " -> ";
        cin >> INFO[i] >> LEFT[i] >> RIGHT[i];
    }

    cout << "Enter ITEM to search: ";
    cin >> ITEM;

    FIND(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if(LOC != 0){
        cout << "ITEM found at location: " << LOC << endl;
        cout << "Parent location: " << PAR << endl;
    } else{
        cout << "ITEM not found." << endl;
        cout << "Can be inserted under parent location: " << PAR << endl;
    }

    return 0;
}
*/
