class Node{
public:
    string data;
    Node* next;

    Node(string val){
        data = val;
        next = NULL;
    }
};

class Stk{
    Node* topNode;

public:
    Stk(){
        topNode = NULL;
    }
    void push(string val){
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

    string top(){
        if(empty()) return "";
        
        return topNode->data;
    }
    bool empty(){
        return topNode == NULL;
    }
    vector<string> toVector(){
        vector<string> res;
        while(!empty()){
            res.push_back(top());
            pop();
        }
        reverse(res.begin(), res.end());
        return res;
    }
};

class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        Stk st;
        int j = 0;
        for(int i=1; i<=n  &&  j<target.size(); i++){
            if(target[j]==i){
                st.push("Push");
                j++;
            } else{
                st.push("Push");
                st.push("Pop");
            }
        }

        return st.toVector();
    }
};