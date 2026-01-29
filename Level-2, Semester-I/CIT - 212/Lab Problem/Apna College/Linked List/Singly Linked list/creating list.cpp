#include<bits/stdc++.h>
using namespace std;

class Node{
public:
    int data;
    Node* next;

    Node(int val){
        data = val;
        next = NULL;
    }
};

class List{     // not access modifier declire. Means, it is private class
    Node* head;
    Node* tail;

public:
    List(){
        head = tail = NULL;
    }
};


int main()
{
    List ll;


    return 0;
}