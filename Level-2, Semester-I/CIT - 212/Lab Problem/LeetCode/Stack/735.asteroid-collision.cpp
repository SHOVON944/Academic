/*
 * [735] Asteroid Collision
 */
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

    vector<int> toVector(){
        vector<int> res;
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
    vector<int> asteroidCollision(vector<int>& asteroids) {
        Stack st;
        for(int a : asteroids){
            bool destroyed = false;
            while(!st.empty()   &&   (st.top()>0  &&  a<0)){
                if(st.top() < abs(a)){
                    st.pop();
                    continue;
                } else if(st.top()==abs(a)){
                    st.pop();
                }
                destroyed = true;
                break;
            }
            if(!destroyed){
                st.push(a);
            }
        }
        return st.toVector();
    }
};
// @lc code=end
