class Solution {
    public int maxSumDivThree(int[] nums) {
        int NEG=Integer.MIN_VALUE/4;
        int[] dp = new int[]{0,NEG,NEG};

        for(int num:nums){
            int[] next=dp.clone();
            int r=num%3;
            for(int j=0;j<3;j++){
                if(dp[j] != NEG){
                    int newR = (j+r) % 3;
                    next[newR] = Math.max(next[newR], dp[j]+num);
                }
            }
            dp=next;
        }
        return Math.max(0, dp[0]);
    }
}
