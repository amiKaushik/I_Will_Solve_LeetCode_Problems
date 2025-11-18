class Solution {
    public int searchInsert(int[] nums, int target) {
        int L=0,R=nums.length-1;
        while(L<=R){
            int mid = L+(R-L)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                R=mid-1;
            }else{
                L=mid+1;
            }
        }
        return L;
    }
}
