class Solution {
    public int climbStairs(int n) {
        if(n<=2) return n;
        int oneStepBefore=2; // dp[i-1]
        int twoStepBefore=1; // dp[i-1]
        int curr=0;
        for(int i=3;i<=n;i++){
            curr = oneStepBefore + twoStepBefore;
            twoStepBefore = oneStepBefore;
            oneStepBefore = curr;
        }
        return curr;
    }
}
