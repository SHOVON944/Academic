#include <iostream>
using namespace std;

// Recursive function to calculate nth Fibonacci number
long long fibonacci(int n) {
    if (n == 0) return 0;      // Base case 0th term
    if (n == 1) return 1;      // Base case 1st term
    return fibonacci(n - 1) + fibonacci(n - 2); // Recursion
}

int main() {
    int n;
    cout << "Enter number of terms: ";
    cin >> n;

    cout << "Fibonacci Series: ";
    for (int i = 0; i < n; i++) {
        cout << fibonacci(i) << " ";
    }
    cout << endl;

    return 0;
}
