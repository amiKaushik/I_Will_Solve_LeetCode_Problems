class Solution {
    public int smallestRepunitDivByK(int K) {
        int rem = 0;
        for (int N = 1; N <= K; N++) {
            rem = (rem * 10 + 1) % K;
            if (rem == 0) {
                return N;
            }
        }
        return -1;
    }
}
