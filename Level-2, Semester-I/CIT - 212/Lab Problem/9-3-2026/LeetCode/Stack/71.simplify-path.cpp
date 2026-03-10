class Node{
public:
    string data;
    Node* next;

    Node(string val){
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
};

class Solution {
public:
    string simplifyPath(string path) {
        Stack st;
        string res = "";
        int n = path.size();

        for(int i=0; i<=n; i++){
            if(i==n   ||   path[i]=='/'){
                if(res==""  ||  res=="."){
                    
                } else if(res==".."){
                    st.pop();
                } else{
                    st.push(res);
                }
                res.clear();
            } else{
                res += path[i];
            }
        }
        vector<string> temp;
        while(!st.empty()){
            temp.push_back(st.top());
            st.pop();
        }

        string result = "/";
        for(int i=temp.size()-1; i>=0; i--){
            result += temp[i];
            if(i != 0){
                result += "/";
            }
        }
        return result;
    }
};