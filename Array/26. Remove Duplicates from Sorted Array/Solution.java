class Solution {
    public int removeDuplicates(int[] nums) {
        int r=0,f=1;
        while(f<nums.length){
            if(nums[r]!=nums[f]){
                r++;
                nums[r]=nums[f];
            }else{
                f++;
            }
        }
        return r+1;
    }
} 
