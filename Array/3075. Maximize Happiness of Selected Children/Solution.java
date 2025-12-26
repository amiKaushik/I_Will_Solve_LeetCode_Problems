class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n=happiness.length;
        Arrays.sort(happiness);
        long maxHappiness=0;
        int turn=0;
        for(int i=n-1;i>=0 && k>0;i--){
            maxHappiness+=Math.max(0,happiness[i]-turn);
            turn++;
            k--;
        }
        return maxHappiness;
    }
}
