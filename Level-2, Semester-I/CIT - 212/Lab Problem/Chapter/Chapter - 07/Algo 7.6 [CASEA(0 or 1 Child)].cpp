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

#define MAX 100
#define NULLPTR -1


void CASEA(int INFO[], int LEFT[], int RIGHT[], int &ROOT, int LOC, int PAR){
    int CHILD;
    if(LEFT[LOC] == NULLPTR && RIGHT[LOC] == NULLPTR){
        CHILD = NULLPTR;
    } else if(LEFT[LOC] != NULLPTR){
        CHILD = LEFT[LOC];
    } else{
        CHILD = RIGHT[LOC];
    }

    if(PAR != NULLPTR){
        if(LEFT[PAR] == LOC){
            LEFT[PAR] = CHILD;
        } else{
            RIGHT[PAR] = CHILD;
        }
    } else{
        ROOT = CHILD;
    }

    cout<<"Node deleted successfully"<<endl<<endl;
}


int main()
{
    int INFO[MAX], LEFT[MAX], RIGHT[MAX];
    int ROOT = NULLPTR;

    // Example Tree:
    // Parent (10) → Child (20)
    ROOT = 0;

    INFO[0] = 10;
    LEFT[0] = NULLPTR;
    RIGHT[0] = 1;

    INFO[1] = 20;
    LEFT[1] = RIGHT[1] = NULLPTR;

    cout<<endl<<"Before Deletion: ROOT = "<<INFO[ROOT]<<endl;

    // Case: Parent has ONE child
    // Delete child (20)
    // Result: Parent (10) → NULL
    CASEA(INFO, LEFT, RIGHT, ROOT, 1, 0);

    if (RIGHT[0] == NULLPTR)
        cout << "Child deleted, now parent show NULLPTR"<<endl;
    // Case: ONLY parent (no child)
    // Delete parent (ROOT)
    // Result: ROOT = NULL

    CASEA(INFO, LEFT, RIGHT, ROOT, 0, NULLPTR);

    if(ROOT == NULLPTR){
        cout<< "Parent (root) delete → Tree is now empty"<<endl;
    }

    return 0;
}


/*
https://chatgpt.com/share/69dd1fe4-6e48-8324-b649-ab32c867df34
*/