/*
 * @lc app=leetcode id=94 lang=cpp
 *
 * [94] Binary Tree Inorder Traversal
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
class Solution {
public:
    vector<int> inorderTraversal(TreeNode* root) {      //! TC: O(n)
        vector<int> ans;
        TreeNode* curr = root;
        
        while(curr!=NULL){
            if(curr->left==NULL){
                ans.push_back(curr->val);
                curr = curr->right;
            } else{
                //* find IP
                TreeNode* IP = curr->left;
                while(IP->right!=NULL   &&  IP->right!=curr){
                    IP = IP->right;
                }
                if(IP->right == NULL){
                    IP->right = curr;   //! creat
                    curr = curr->left;
                } else{
                    IP->right = NULL;   //! destroy
                    ans.push_back(curr->val);
                    curr = curr->right;
                }
            }
        }

        return ans;
    }
};
// @lc code=end
