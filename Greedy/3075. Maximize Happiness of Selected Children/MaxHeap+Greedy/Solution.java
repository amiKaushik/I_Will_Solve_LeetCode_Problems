class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n=happiness.length;
        PriorityQueue<Integer> hp = new PriorityQueue<>(Collections.reverseOrder());
        for(int x:happiness){
            hp.add(x);
        }
        long maxHappiness=0;
        int turn=0;
        while(k>0 && !hp.isEmpty()){
            maxHappiness+=Math.max(0,hp.poll()-turn);
            turn++;
            k--;
        }
        return maxHappiness;
    }
}
