class Solution {
public:
    vector<int> runningSum(vector<int>& nums){

        for(int i=1; i<nums.size(); i++){
            nums[i] += nums[i - 1];
        }

        return nums;
    }
};


/*
class Solution {
public:
    vector<int> runningSum(vector<int>& nums){
        long long sum = 0;
        vector<int> store(nums.size());
        for(int i=0; i<nums.size(); i++){
            sum += nums[i];
            store[i] = sum;
        }

        return store;
    }
};
*/