class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans=0;
        int count=1;
        int n=prices.length;
        for(int i=1;i<n;i++){
            if(prices[i-1]-prices[i] == 1){
                count++;
            }else{
                ans += (1L * count * (count+1))/2;
                count=1;
            }
        }
        ans += (1L * count * (count+1))/2;
        return ans;
    }
}
