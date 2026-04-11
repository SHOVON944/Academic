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


Node* insert(Node* root, int val){
    if(root == NULL){
        return new Node(val);
    }
    if(val < root->data){
        root->left = insert(root->left, val);
    } else{
        root->right = insert(root->right, val);
    }

    return root;
}

Node* buildBST(vector<int> arr){
    Node* root = NULL;
    for(int val : arr){
        root = insert(root, val);
    }

    return root;
}

void inOrder(Node* root){
    if(root == NULL){
        return;
    }

    inOrder(root->left);
    cout<<root->data<<" ";
    inOrder(root->right);
}

// find predecessor & successor in BSTs
//!     TC: O(H) -> O(logn).... SC: (1) -> constant SC
Node* rightMostValueInLeftSubTree(Node* root){
    Node* ans;

    while(root != NULL){
        ans = root;
        root = root->right;
    }
    return ans;
}

Node* leftMostValueInRighttSubTree(Node* root){
    Node* ans;

    while(root != NULL){
        ans = root;
        root = root->left;
    }
    return ans;
}


vector<int> getPredSuc(Node* root, int key){
    Node* curr = root;
    Node* pred = NULL;
    Node* succ = NULL; 

    while(curr != NULL){
        if(key < curr->data){
            succ = curr;
            curr = curr->left;
        } else if(key > curr->data){
            pred = curr;
            curr = curr->right;
        } else{
            //* find inordered predecessor
            if(curr->left != NULL){
                pred = rightMostValueInLeftSubTree(curr->left);
            }
            if(curr->right != NULL){
                succ = leftMostValueInRighttSubTree(curr->right);
            }
            break;
        }
    }

    return {pred->data, succ->data};
}

int main()
{
    Node* root = new Node(6);
    root->left = new Node(4);
    root->right = new Node(8);
    root->left->left = new Node(1);
    root->left->right = new Node(5);
    root->right->left = new Node(7);
    root->right->right = new Node(9);

    int key = 3;
    vector<int> ans = getPredSuc(root, key);
    cout<<"Preordered: "<<ans[0]<<endl;
    cout<<"Successor: "<<ans[1]<<endl;

    return 0;
}