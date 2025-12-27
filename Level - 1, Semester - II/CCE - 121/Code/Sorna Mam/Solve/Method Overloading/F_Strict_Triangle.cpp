#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int t;  // number of test cases
    cin >> t;
    while (t--) {
        int n;
        long long c;
        cin >> n >> c;
        vector<long long> a(n);
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }

        sort(a.begin(), a.end()); // sort by weight

        int coins = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > c) {
                coins++; // must pay coin
            }
            // after removing one bag, remaining bags double in weight
            for (int j = i + 1; j < n; j++) {
                a[j] *= 2;
            }
        }

        cout << coins << endl;
    }
    return 0;
}
