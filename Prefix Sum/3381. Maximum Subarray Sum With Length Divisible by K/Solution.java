class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        final long INF = Long.MAX_VALUE / 4;
        long[] minPref = new long[k];
        for(int i=0;i<k;i++){
            minPref[i] = INF;
        }
        minPref[0] = 0L;
        long pref = 0L;
        long ans = Long.MIN_VALUE;

        for(int a=1;a<=n;a++){
            pref += nums[a-1];
            int rem=a%k;
            if(minPref[rem] != INF){
                long candidate = pref - minPref[rem];
                if(candidate>ans){
                    ans = candidate;
                }
            }
            if(pref < minPref[rem]){
                minPref[rem] = pref;
            }
        }
        return ans;
    }
}
