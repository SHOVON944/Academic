/*
███████╗██╗  ██╗ ██████╗ ██╗   ██╗ ██████╗ ███╗   ██╗
██╔════╝██║  ██║██╔═══██╗██║   ██║██╔═══██╗████╗  ██║
███████╗███████║██║   ██║██║   ██║██║   ██║██╔██╗ ██║
╚════██║██╔══██║██║   ██║╚██╗ ██╔╝██║   ██║██║╚██╗██║
███████║██║  ██║╚██████╔╝ ╚████╔╝ ╚██████╔╝██║ ╚████║
╚══════╝╚═╝  ╚═╝ ╚═════╝   ╚═══╝   ╚═════╝ ╚═╝  ╚═══╝
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


void INSBST(int INFO[], int LEFT[], int RIGHT[],int &ROOT, int &AVAIL, int ITEM, int &LOC){
    int PAR, NEW;

    FIND(INFO, LEFT, RIGHT, ROOT, ITEM, LOC, PAR);

    if(LOC != NULL_PTR){
        return;
    }

    if(AVAIL == NULL_PTR){
        cout << "OVERFLOW";
        return;
    }

    NEW = AVAIL;
    AVAIL = LEFT[AVAIL];
    INFO[NEW] = ITEM;

    LOC = NEW;
    LEFT[NEW] = NULL_PTR;
    RIGHT[NEW] = NULL_PTR;

    if(PAR == NULL_PTR){
        ROOT = NEW;
    }
    else if(ITEM < INFO[PAR]){
        LEFT[PAR] = NEW;
    } else{
        RIGHT[PAR] = NEW;
    }
}

void INORD(int INFO[], int LEFT[], int RIGHT[], int ROOT){
    if(ROOT == 0) return;
    INORD(INFO, LEFT, RIGHT, LEFT[ROOT]);
    cout << INFO[ROOT] << " ";
    INORD(INFO, LEFT, RIGHT, RIGHT[ROOT]);
}

int main()
{
    int INFO[MAX];
    int LEFT[MAX];
    int RIGHT[MAX];

    int ROOT = 0, AVAIL = 1, LOC;
    int n, ITEM;

    for(int i = 1; i < MAX - 1; i++){
        LEFT[i] = i + 1;
    }
    LEFT[MAX - 1] = 0;

    cout << "Enter number of items to insert: ";
    cin >> n;

    for(int i = 1; i <= n; i++){
        cout << "Enter ITEM " << i << ": ";
        cin >> ITEM;
        INSBST(INFO, LEFT, RIGHT, ROOT, AVAIL, ITEM, LOC);
    }

    cout << "Inorder Traversal of BST: ";
    INORD(INFO, LEFT, RIGHT, ROOT);

    return 0;
}

/*
*Input:
    Enter number of items to insert: 6
    Enter ITEM 1: 40
    Enter ITEM 2: 60
    Enter ITEM 3: 50
    Enter ITEM 4: 33
    Enter ITEM 5: 55
    Enter ITEM 6: 11

*Output:
    Inorder Traversal of BST: 11 33 40 50 55 60
*/