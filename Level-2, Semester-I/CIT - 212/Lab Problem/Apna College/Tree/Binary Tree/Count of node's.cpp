#include<iostream>
#include<vector>
using namespace std;

class Node{
public:
    int data;
    Node* left;
    Node* right;

    Node(int val){
        data = val;
        left = right = NULL;
    }
};

    // TC: O(n)
static int idx = -1;
Node* buildTree(vector<int> preOrder){
    idx++;
    if(preOrder[idx]==-1){
        return NULL;
    }

    Node* root = new Node(preOrder[idx]);
    root->left = buildTree(preOrder);   // left
    root->right = buildTree(preOrder);  // right

    return root;
}

    // TC: O(n)
int countOfNodes(Node* root){
    if(root == NULL){
        return 0;
    }
    int leftCN = countOfNodes(root->left);
    int rightCN = countOfNodes(root->right);

    return leftCN + rightCN + 1;
}


int main()
{
    vector<int> preOrder = {1, 2, -1, -1, 3, 4, -1, -1, 5, -1, -1};

    Node* root = buildTree(preOrder);
    cout<<"Count of Nodes: "<<countOfNodes(root)<<endl;

    return 0;
}