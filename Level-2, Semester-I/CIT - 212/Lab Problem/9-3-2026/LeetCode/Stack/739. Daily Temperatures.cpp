#include <bits/stdc++.h>
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

class Stack{
    Node* topNode;

public:
    Stack(){
        topNode = NULL;
    }

    void push(int val){
        Node* newNode = new Node(val);
        newNode->next = topNode;
        topNode = newNode;
    }

    void pop(){
        if(empty()) return;
        Node* temp = topNode;
        topNode = topNode->next;
        delete temp;
    }
    int top(){
        if(empty()) return -1;
        return topNode->data;
    }
    bool empty(){
        return topNode == NULL;
    }
};

// Solution class for LeetCode
class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        int n = temperatures.size();
        vector<int> ans(n, 0);
        Stack st;
        for(int i=0; i<n; i++){
            while(!st.empty()   &&   temperatures[i]>temperatures[st.top()]){
                int prevIdx = st.top();
                st.pop();
                ans[prevIdx] = i - prevIdx;
            }
            st.push(i);
        }
        return ans;
    }
};

