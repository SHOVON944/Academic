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

    int size(){
        return count;
    }

    bool empty(){
        return head==NULL;
    }
};

class Solution {
public:
    int timeRequiredToBuy(vector<int>& tickets, int k) {
        QueUe q;
        int n = tickets.size();
        int check_position = k;
        long long time = 0;
        for(int i=0; i<n; i++){
            q.push(tickets[i]);
        }
        while(1){
            int val = q.front();
            q.pop();
            val--;
            time++;
            if(val>0){
                q.push(val);
            }
            if(check_position==0){
                if(val==0) break;
                else check_position = q.size() - 1;
            } else{
                check_position--;
            }
        }
        return time;
    }
};


int main()
{


    return 0;
}