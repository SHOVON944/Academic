#include <iostream>
using namespace std;

const int MAX = 100;

int SRCHSL(int INFO[], int LINK[], int START, int ITEM){
    int PTR = START;
    int LOC = -1;

    while(PTR != -1){
        if (ITEM > INFO[PTR]){
            PTR = LINK[PTR];
        } else if (ITEM == INFO[PTR]){
            LOC = PTR;
            return LOC;
        } else{
            return -1;
        }
    }

    return -1;
}

int main()
{
    int INFO[MAX], LINK[MAX];
    int START, ITEM;
    int n;
    cout << "Enter number of nodes: ";
    cin >> n;

    cout << "Enter INFO values (sorted):\n";
    for(int i=0; i<n; i++){
        cin>>INFO[i];
    }

    cout<<"Enter LINK values (-1 for NULL): "<<endl;
    for(int i=0; i<n; i++){
        cin>>LINK[i];
    }

    cout<<"Enter START index: ";
    cin>>START;

    cout<<"Enter ITEM to search: ";
    cin>>ITEM;

    int LOC = SRCHSL(INFO, LINK, START, ITEM);

    if(LOC != -1) cout<<"ITEM found at location: "<<LOC<<endl;
    else cout<<"ITEM not found in list"<<endl;

    return 0;
}

/*
*Input:
    Enter number of nodes: 5
    Enter INFO values:
    10 20 30 40 50
    Enter LINK values:
    1 2 3 4 -1
    Enter START index: 0
    Enter ITEM to search: 30

*Output:
    ITEM found at location: 2

*/