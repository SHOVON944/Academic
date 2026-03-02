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

    int K = 1;
    int LOC = 1;
    int MAX = DATA[1];
    do{
        if(MAX<DATA[K]){
            MAX = DATA[K];
            LOC = K;
        }
        K = K + 1;
    } while(K <= N);

    cout<<"Largest element location= "<<LOC<<endl;
    cout<<"Largest element = "<<MAX<<endl;

    return 0;
}
