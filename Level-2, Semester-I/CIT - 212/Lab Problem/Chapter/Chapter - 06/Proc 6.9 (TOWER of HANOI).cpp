#include<iostream>
using namespace std;

void TOWER_of_HANOI(int N, char BEG, char AUX, char END){
    if(N==1){
        cout<<BEG<<" -> "<<END<<endl;
        return;
    }

    TOWER_of_HANOI(N-1, BEG, END, AUX);
    cout<<BEG<<" -> "<<END<<endl;
    TOWER_of_HANOI(N-1, AUX, BEG, END);
}

int main()
{
    cout<<"Enter number of disks: ";
    int N;
    cin>>N;
    
    TOWER_of_HANOI(N, 'A', 'B', 'C');

    return 0;
}

/*
    jodi koita step e print hocce emn korte chai thle golbal ekta variable nia seta if condition i increament
    and trpr nicer cout er por incrament
*/