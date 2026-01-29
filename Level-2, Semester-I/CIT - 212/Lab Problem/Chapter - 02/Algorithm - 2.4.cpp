#include <iostream>
using namespace std;

int main()
{
    int N;
    cout<<"Enter number of elements: ";
    cin>>N;
    int DATA[N];
    cout << "Enter array elements: ";
    for(int i=1; i<=N; i++){
        cin>>DATA[i];
    }

    int ITEM;
    cin>>ITEM;

    int K = 1;
    int LOC = 0;
    while(LOC==0   &&   K<=N){
        if(ITEM == DATA[K]){
            LOC = K;
        }
        K = K + 1;
    }
    if(LOC==0){
        cout<<ITEM<<" is not in DATA"<<endl;
    } else{
        cout<<LOC<<"is the location of"<<ITEM<<endl;
    }

    return 0;
}
