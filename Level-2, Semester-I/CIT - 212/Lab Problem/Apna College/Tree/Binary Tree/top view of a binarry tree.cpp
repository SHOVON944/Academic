#include<iostream>
#include<vector>
#include<queue>
#include<map>
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

//!  TC: O(nlogn), because map e find use hocce..jetar time complexity O(logn). But unordered_map TC: O(1)
void topView(Node* root){
    queue<pair<Node*, int>> q;
    map<int, int> m;    //* store Horizontal Distance, node value
    q.push({root, 0});

    while(q.size()>0){
        Node* curr = q.front().first;
        int currHD = q.front().second;
        q.pop();
//fix: done with a code without
        if(m.find(currHD) == m.end()){      //TODO: if condition ta bad dia m[currHD] = curr->data; likhle seta bottomView te convert hobe...
            m[currHD] = curr->data;
        }

        if(curr->left!=NULL){
            q.push({curr->left, currHD-1});
        }
        if(curr->right!=NULL){
            q.push({curr->right, currHD+1});
        }
    }
    for(auto it : m){
        cout<<it.second<<" ";
    }
    cout<<endl;
}


int main()
{
    vector<int> preOrder = {1, 2, -1, -1, 3, 4, -1, -1, 5, -1, -1};

    Node* root = buildTree(preOrder);
    topView(root);

    return 0;
}