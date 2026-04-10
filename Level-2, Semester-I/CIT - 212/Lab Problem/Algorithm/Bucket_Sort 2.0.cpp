#include<bits/stdc++.h>
using namespace std;

// Insertion Sort for a single bucket
void insertionSort(vector<float>& bucket) {
    int n = bucket.size();
    for (int i = 1; i < n; i++) {
        float key = bucket[i];
        int j = i - 1;

        while (j >= 0 && bucket[j] > key) {
            bucket[j + 1] = bucket[j];
            j--;
        }
        bucket[j + 1] = key;
    }
}

void bucketSort(float arr[], int n){
    vector<vector<float>> bucket(n, vector<float>());

    float max_ele = arr[0];
    float min_ele = arr[0];
    for(int i=1; i<n; i++){
        max_ele = max(max_ele, arr[i]);
        min_ele = min(min_ele, arr[i]);
    }
    float range = (max_ele - min_ele) / n;

    //step 2: inserting elements into bucket
    for(int i=0; i<n; i++){
        int index = (arr[i] - min_ele) / range;
        // bounday element
        float diff = (arr[i] - min_ele) / range - index;
        if(diff==0  && arr[i]!=min_ele){    //arr[i]!=min_ele use na korle jdi min element bucket element hoi thle index 0-1=-1 e jave..invalid
            bucket[index-1].push_back(arr[i]);
        } else{
            bucket[index].push_back(arr[i]);
        }
    }
    //step 3: sorting individual buckets
    for(int i=0; i<n; i++){
        // if(!bucket[i].empty()){
        //     sort(bucket[i].begin(), bucket[i].end());
        // }
        insertionSort(bucket[i]);
    }
    // step 4: combining elements from bucket
    int k = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<bucket[i].size(); j++){
            arr[k++] = bucket[i][j];
        }
    }
}


int main()
{
    float arr[] = {0.50, 0.12, 0.85, 0.39, 0.89, 0.25, 0.63, 0.40875, 0.75};
    int n = sizeof(arr) / sizeof(arr[0]);
    bucketSort(arr, n);

    for(int i=0; i<n; i++) {
        cout << arr[i] << " ";
    }

    return 0;
}