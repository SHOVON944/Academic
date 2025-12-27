#include <bits/stdc++.h>
using namespace std;

bool is_bad(vector<int>& q) {
    int n = q.size();
    for (int i = 0; i <= n - 5; i++) {
        bool inc = true, dec = true;
        for (int j = 1; j < 5; j++) {
            if (q[i + j] <= q[i + j - 1]) inc = false;
            if (q[i + j] >= q[i + j - 1]) dec = false;
        }
        if (inc || dec) return true;
    }
    return false;
}

void solve() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        deque<int> dq(n);
        for (int i = 0; i < n; i++) cin >> dq[i];

        string result = "";
        vector<int> q;
        int l = 0, r = n - 1;
        while (!dq.empty()) {
            int left = dq.front();
            int right = dq.back();

            q.push_back(left);
            dq.pop_front();
            if (is_bad(q)) {
                q.pop_back(); 
                dq.push_front(left);

                q.push_back(right);
                dq.pop_back();
                result += 'R';
            } else {
                result += 'L';
            }
        }
        cout << result << '\n';
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}
