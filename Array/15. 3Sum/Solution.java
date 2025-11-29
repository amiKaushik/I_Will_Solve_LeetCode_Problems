class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n<3) return result;
        Arrays.sort(nums);

        for(int i=0;i<n-2;i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            int target = -nums[i];
            int L=i+1, R=n-1;
            while(L<R){
                int sum = nums[L] + nums[R];
                if(sum==target){
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    int leftVal = nums[L];
                    int rightVal = nums[R];
                    while(L<R && nums[L]==leftVal) L++;
                    while(L<R && nums[R]==rightVal) R--;
                }else if(sum<target){
                    L++;
                }else{
                    R--;
                }
            }
        }
        return result;
    }
}
