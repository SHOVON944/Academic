class Node{
public:
    int data;
    Node* next;

    Node(int val){
        data = val;
        next = NULL;
    }
};

class MinStack {
    Node* topNode;
    Node* minTopNode;

public:
    MinStack() {
        topNode = NULL;    
        minTopNode = NULL;    
    }
    
    void push(int val) {
        Node* newNode = new Node(val);
        newNode->next = topNode;
        topNode = newNode;

        if(minTopNode==NULL   ||  val<=minTopNode->data){
            Node* minNode = new Node(val);
            minNode->next = minTopNode;
            minTopNode = minNode;
        }
    }
    
    void pop() {
        if(empty()) return;

        int removed = topNode->data;

        Node* temp = topNode;
        topNode = topNode->next;
        delete temp;

        if(minTopNode!=NULL &&  removed==minTopNode->data){
            Node* minTemp = minTopNode;
            minTopNode = minTopNode->next;
            delete minTemp;
        }
    }
    
    int top() {
        if(empty()) return -1;

        return topNode->data;
    }
    
    int getMin() {
        return minTopNode==NULL ? -1 : minTopNode->data;
    }
    bool empty(){
        return topNode==NULL;
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */