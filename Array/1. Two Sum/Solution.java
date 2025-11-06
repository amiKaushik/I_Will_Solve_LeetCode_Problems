import java.util.HashMap;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hsmp = new HashMap<>();
        int[] indices = new int[2];
        for(int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if(hsmp.containsKey(complement)){
                indices[0] = hsmp.get(complement);
                indices[1] = i;
                return indices;
            }
            hsmp.put(nums[i],i);
        }
        return indices;
    }
    public static void main(String[] args){
        int[] nums = {3,2,4};
        int target = 6;
        Solution s = new Solution();
        int[] res = s.twoSum(nums, target);
        System.out.println(res[0] + ", " + res[1]);
    }
}