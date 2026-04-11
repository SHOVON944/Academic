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


void POSTORD(char INFO[], int LEFT[], int RIGHT[], int ROOT){
    int STACK[MAX];
    int TOP, PTR;

    TOP = 1;
    STACK[1] = NULL_PTR;
    PTR = ROOT;

    while(true){
        while(PTR != NULL_PTR){
            TOP = TOP + 1;
            STACK[TOP] = PTR;

            if(RIGHT[PTR] != NULL_PTR){
                TOP = TOP + 1;
                STACK[TOP] = -RIGHT[PTR];
            }

            PTR = LEFT[PTR];
        }

        PTR = STACK[TOP];
        TOP = TOP - 1;

        while(PTR > 0){
            cout<<INFO[PTR]<<" ";
            PTR = STACK[TOP];
            TOP = TOP - 1;
        }

        if(PTR < 0){
            PTR = -PTR;
        } else{
            break;
        }
    }
}


int main()
{
    char INFO[MAX];
    int LEFT[MAX];
    int RIGHT[MAX];
    int ROOT;

    //       A
    //      / \
    //     B   C
    //    / \
    //   D   E

    // Preorder Inorder: D E B C A

    ROOT = 1;

    INFO[1] = 'A'; LEFT[1] = 2; RIGHT[1] = 3;
    INFO[2] = 'B'; LEFT[2] = 4; RIGHT[2] = 5;
    INFO[3] = 'C'; LEFT[3] = 0; RIGHT[3] = 0;
    INFO[4] = 'D'; LEFT[4] = 0; RIGHT[4] = 0;
    INFO[5] = 'E'; LEFT[5] = 0; RIGHT[5] = 0;

    cout << "Postorder Traversal: ";
    POSTORD(INFO, LEFT, RIGHT, ROOT);

    return 0;
}



/*
*Input:
    Enter number of nodes: 5
    Enter ROOT index: 1

    Enter INFO, LEFT, RIGHT for each node:
    Node 1 -> A 2 3
    Node 2 -> B 4 5
    Node 3 -> C 0 0
    Node 4 -> D 0 0
    Node 5 -> E 0 0

*Output:
    Preorder Postorder: D E B C A


int main()
{
    char INFO[MAX];
    int LEFT[MAX];
    int RIGHT[MAX];
    int ROOT, n;

    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter ROOT index: ";
    cin >> ROOT;

    cout << "Enter INFO, LEFT, RIGHT for each node:";
    for (int i = 1; i <= n; i++) {
        cout << "Node " << i << " -> ";
        cin >> INFO[i] >> LEFT[i] >> RIGHT[i];
    }

    cout << "Postorder Traversal: ";
    POSTORD(INFO, LEFT, RIGHT, ROOT);

    return 0;
}
*/