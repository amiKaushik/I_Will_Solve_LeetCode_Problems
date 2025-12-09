class Solution {
    public int specialTriplets(int[] nums) {
        final int MAX=200_000;
        final long MOD = 1_000_000_007L;
        int n=nums.length;
        long[] left = new long[MAX+1];
        long[] right = new long[MAX+1];

        for(int num:nums){
            right[num]++;
        }
        long ans=0L;
        for(int j=0;j<n;j++){
            int mid=nums[j];
            right[mid]--;
            int target=mid*2;
            if(target<=MAX){
                ans=(ans+(left[target] * right[target]) % MOD) % MOD;
            }
            left[mid]++;
        }
        return (int)ans;
    }
}
