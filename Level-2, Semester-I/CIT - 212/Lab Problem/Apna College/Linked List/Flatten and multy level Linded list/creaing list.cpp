#include<bits/stdc++.h>
using namespace std;

class Node{
public:
    int data;
    Node* next;
    Node* prev;

    Node(int val){
        data = val;
        next = NULL;
        prev = NULL;
    }
};

class DoublyList{
    Node* head;
    Node* tail;

public:
    DoublyList(){
        head = tail = NULL;
    }
Node* flatten(Node* head){
        if(head==NULL){
            return head;
        }

        Node* curr = head;
        while(curr!=NULL){
            // flatten the child nodes
            Node* next = curr->next;
            curr->next = flatten(curr->child);

            curr->next->prev = curr;
            curr->child = NULL;
            // find tail
            while(curr->next!=NULL){
                curr = curr->next;
            }

            // attach tail with next ptr
            if(next!=NULL){
                curr->next = next;
                next->prev = curr;
            }
        }
        curr = curr->next;
    }
};

class Solution{
public:
    Node* flatten(Node* head){
        if(head==NULL){
            return head;
        }

        Node* curr = head;
        while(curr!=NULL){
            // flatten the child nodes
            Node* next = curr->next;
            curr->next = flatten(curr->child);

            curr->next->prev = curr;
            curr->child = NULL;
            // find tail
            while(curr->next!=NULL){
                curr = curr->next;
            }

            // attach tail with next ptr
            if(next!=NULL){
                curr->next = next;
                next->prev = curr;
            }
        }
        curr = curr->next;
    }
    return head;
};


int main()
{
    DoublyList dll;

    return 0;
}