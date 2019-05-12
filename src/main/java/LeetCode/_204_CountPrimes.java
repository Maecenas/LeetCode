package LeetCode;

/*
https://leetcode.com/problems/count-primes/
Easy. Hash Table, Math.

Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

class _204_CountPrimes {

    /**
     * Sieve of Eratosthenes, O(n log log n), O(n)
     * How to optimize with caching? With a private static final TreeSet, or HashSet + int LAST_PRIME?
     */
    public static int countPrimes(int n) {
        if (n < 0) return -1;
        else if (n <= 2) return 0;

        // Start with boolean[] isNotPrime than isPrime
        // with value set to false by default
        boolean[] isNotPrime = new boolean[n];

        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            // i is prime
            if (!isNotPrime[i]) {
                // filter out all multiples of i
                // start from i * i because i * k (k < i) is already marked at k round
                for (int j = i * i; j < n; j += 2 * i) {
                    if (!isNotPrime[j]) {
                        count--;
                        isNotPrime[j] = true;
                    }
                }
            }
        }
        return count;
    }
}
