class Solution {
    public int countPartitions(int[] nums) {
        int n=nums.length;
        int total=0;
        for(int num:nums) {
            total += num;
        }
        if(total%2==0){
            return (n-1);
        }else{
            return 0;
        }
    }
}
