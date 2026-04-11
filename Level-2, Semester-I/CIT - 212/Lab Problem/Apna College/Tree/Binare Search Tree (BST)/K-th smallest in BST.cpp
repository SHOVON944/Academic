/*
 * @lc app=leetcode id=230 lang=cpp
 *
 * [230] Kth Smallest Element in a BST
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

 //!  TC:O(n)
class Solution {
public:

    int prevOrder = 0;
    int kthSmallest(TreeNode* root, int k) {
        if(root==NULL){
            return -1;
        }

        if(root->left != NULL){
            int leftAns = kthSmallest(root->left, k);
            if(leftAns!=-1){
                return leftAns;
            }
        }

        if(prevOrder+1 == k){
            return root->val;
        }
        prevOrder += 1;

                if(root->right != NULL){
            int rightAns = kthSmallest(root->right, k);
            if(rightAns!=-1){
                return rightAns;
            }
        }
        return -1;
    }
};
// @lc code=end
