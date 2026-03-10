class Node{
public:
    char data;
    Node* next;

    Node(char val){
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
    void push(char val){
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

    char top(){
        if(empty()) return '\0';
        
        return topNode->data;
    }
    bool empty(){
        return topNode == NULL;
    }

    string strReverse(string s){
        int i=0;
        int j = s.size() - 1;
        while(i<j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }
        return s;
    }

    string stackToString(){
        string res = "";
        while(!empty()){
            res += top();
            pop();
        }
        return strReverse(res);
    }
};


class Solution {
public:
    string removeStars(string s) {
       Stack st;
       for(int i=0; i<s.size(); i++) {
        if(s[i]=='*'){
            st.pop();
        } else{
            st.push(s[i]);
        }
       }

       return st.stackToString();
    }
};