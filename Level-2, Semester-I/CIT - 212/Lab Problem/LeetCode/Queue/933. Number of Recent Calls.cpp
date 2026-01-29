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

class QueUe{
    Node* head;
    Node* tail;
    int count;

public:
    QueUe(){
        head = tail = NULL;
        count = 0;
    }

    void push(int val){
        Node* newNode = new Node(val);

        if(empty()){
            head = tail = newNode;
        } else{
            tail->next = newNode;
            tail = newNode;
        }
        count++;
    }

    void pop(){
        if(empty()){
            return;
        } else{
            Node* temp = head;
            head = head->next;
            delete temp;
        }
        count--;
    }

    int front(){
        if(empty()){
            return -1;
        }

        return head->data;
    }

    int size_q(){
        return count;
    }

    bool empty(){
        return head==NULL;
    }
};

class RecentCounter {
    QueUe q;
public:
    RecentCounter() {
    }
    
    int ping(int t) {
        q.push(t);
        while(q.front() < t-3000) q.pop(); 

        return q.size_q();
    }
};

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter* obj = new RecentCounter();
 * int param_1 = obj->ping(t);
 */


int main()
{


    return 0;
}