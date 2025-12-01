class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sumPower=0;
        for(int power : batteries){
            sumPower += power;
        }
        long left=0L, right = sumPower/n, ans=0L;
        while(left<=right){
            long mid=left+(right-left)/2;
            long total=0L;
            for(int power : batteries){
                total+=Math.min((long)power, mid);
                if(total>=(long)n * mid){
                    break;
                }
            }
            if(total>=(long)n * mid){
                ans=mid;
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return ans;
    }
}
