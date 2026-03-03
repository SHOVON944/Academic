#include <iostream>
using namespace std;

int main()
{
    int N;
    cout<<"Enter number of elements: ";
    cin>>N;

    int DATA[N];
    cout<<"Enter the elements: ";
    for(int i=1; i<=N; i++){
        cin>>DATA[i];
    }

    int K = 1;
    int LOC = 1;
    int MAX = DATA[1];

    while(true){
        K = K + 1;
        if(K>N){
            cout<<"Largest element Location: "<<LOC<<endl;
            cout<<"Largest element: "<<MAX<<endl;
            break;
        }
        if(MAX<DATA[K]){
            MAX = DATA[K];
            LOC = K;
        }
    }

    return 0;
}
