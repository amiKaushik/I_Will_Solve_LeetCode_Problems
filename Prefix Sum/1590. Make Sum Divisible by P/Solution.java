class Solution {
    public int minSubarray(int[] nums, int p) {
        int n=nums.length;
        long total=0L;
        for(int num:nums) total += num;
        int target = (int)(total%p);
        if(target==0) return 0;

        Map<Integer, Integer> position = new HashMap<>();
        position.put(0,-1);
        int ans=n;
        long currSum=0L;

        for(int i=0;i<n;i++){
            currSum += nums[i];
            int curr=(int)(currSum%p);
            int need = curr-target;
            if(need<0) need +=p;

            Integer prevIndex = position.get(need);
            if(prevIndex != null){
                ans=Math.min(ans, i-prevIndex);
            }
            position.put(curr, i);
        }
        if(ans==n){
            return -1;
        }else{
            return ans;
        }
    }
}
